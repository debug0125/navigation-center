package com.pzc.navigationweb.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.common.util.UserSessionUtil;
import com.pzc.navigationweb.constant.ImgConstants;
import com.pzc.navigationweb.dao.CategoryDOMapper;
import com.pzc.navigationweb.dao.FavoritesDOMapper;
import com.pzc.navigationweb.dao.NavCategoryDOMapper;
import com.pzc.navigationweb.dao.NavigationResourcesDOMapper;
import com.pzc.navigationweb.domain.dbdo.CategoryDO;
import com.pzc.navigationweb.domain.dbdo.FavoritesDO;
import com.pzc.navigationweb.domain.dbdo.NavCategoryDO;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.domain.mapstruct.CategoryMapStruct;
import com.pzc.navigationweb.domain.mapstruct.NavigationReqToDo;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.AddNavOpenCountReqDTO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class NavigationResourcesServiceImpl implements NavigationResourcesService {

    @Autowired
    private NavigationResourcesDOMapper navigationResourcesDOMapper;
    @Autowired
    private FavoritesDOMapper favoritesDOMapper;
    @Autowired
    private NavCategoryDOMapper navCategoryDOMapper;
    @Autowired
    private CategoryDOMapper categoryDOMapper;


    @Override
    @Transactional
    public Boolean submit(NavigationResourcesReqDTO navigationResourcesReqDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        // 设置默认值
        if (StringUtils.isEmpty(navigationResourcesReqDTO.getLogoUrl())) {
            navigationResourcesReqDTO.setLogoUrl(ImgConstants.DEFAULT_PICTURE);
        }
        NavigationResourcesDO navigationResourcesDO = NavigationReqToDo.INSTANCE.reqToDo(navigationResourcesReqDTO);
        InitDOUtil.initField(navigationResourcesDO);
        result.set(navigationResourcesDOMapper.insertSelective(navigationResourcesDO) > 0);

        // 新增标签
        navigationResourcesReqDTO.getCategoryIds().stream().forEach(x -> {
            NavCategoryDO navCategoryDO = new NavCategoryDO();
            InitDOUtil.initField(navCategoryDO);
            navCategoryDO.setNavId(navigationResourcesDO.getId());
            navCategoryDO.setCategoryId(x);
            result.set(navCategoryDOMapper.insertSelective(navCategoryDO) > 0);
        });
        return result.get();
    }

    @Override
    public Page<NavigationResourcesRespDTO> pageNavigation(NavigationQuery query) {
        Integer count = navigationResourcesDOMapper.countNavigation(query);
        Page<NavigationResourcesRespDTO> page = new Page<>(query.getPageNo(), query.getPageSize(), count);
        List<NavigationResourcesRespDTO> dtoList = new ArrayList<>();
        if (count > 0) {
            dtoList = navigationResourcesDOMapper.pageNavigation(query);
            dtoList.stream().forEach(x -> {
                x.setCreateDateStr(DateUtil.formatDateTime(x.getCreateDate()));
                FavoritesDO favoritesDO = favoritesDOMapper.selectByUserNavId(UserSessionUtil.getCurreentUserByKey().getId(),x.getId());
                x.setIsLiked( favoritesDO != null);
                x.setLikeCount(favoritesDOMapper.countByNavId(x.getId()));

                List<CategoryDO> categoryDOArrayList = new ArrayList<>();
                // 获取标签
                NavCategoryDO navCategoryDO = new NavCategoryDO();
                navCategoryDO.setNavId(x.getId());
                List<NavCategoryDO> navCategoryDOList = navCategoryDOMapper.queryListByCategoryId(navCategoryDO);
                if (CollectionUtils.isNotEmpty(navCategoryDOList)) {
                    navCategoryDOList.stream().forEach(navC -> {
                        CategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(navC.getCategoryId());
                        categoryDOArrayList.add(categoryDO);
                    });
                    x.setCategoryRespDTOList(CategoryMapStruct.INSTANCE.doListToRespList(categoryDOArrayList));
                }
            });

        }
        page.setResult(dtoList);
        return page;









//        Page<NavigationResourcesRespDTO> page = PageHelper.startPage(query.getPageNo(), query.getPageSize(), true);
//        navigationResourcesDOMapper.listNavigation(query);
//        PageInfo<NavigationResourcesRespDTO> pageInfo = new PageInfo<>(page);
//        pageInfo.getList().stream().forEach(x -> {
//            x.setCreateDateStr(DateUtil.formatDateTime(x.getCreateDate()));
//            FavoritesDO favoritesDO = favoritesDOMapper.selectByUserNavId(UserSessionUtil.getCurreentUserByKey().getId(),x.getId());
//            x.setIsLiked( favoritesDO != null);
//            x.setLikeCount(favoritesDOMapper.countByNavId(x.getId()));
//
//            List<CategoryDO> categoryDOArrayList = new ArrayList<>();
//            // 获取标签
//            NavCategoryDO navCategoryDO = new NavCategoryDO();
//            navCategoryDO.setNavId(x.getId());
//            List<NavCategoryDO> navCategoryDOList = navCategoryDOMapper.queryListByCategoryId(navCategoryDO);
//            if (CollectionUtils.isNotEmpty(navCategoryDOList)) {
//                navCategoryDOList.stream().forEach(navC -> {
//                    CategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(navC.getCategoryId());
//                    categoryDOArrayList.add(categoryDO);
//                });
//                x.setCategoryRespDTOList(CategoryMapStruct.INSTANCE.doListToRespList(categoryDOArrayList));
//            }
//
//        });
//        return pageInfo;
    }

    @Override
    public NavigationResourcesRespDTO addOpenCount(AddNavOpenCountReqDTO reqDTO) {
        String id = reqDTO.getId();
        NavigationResourcesDO resourcesDO = navigationResourcesDOMapper.selectByPrimaryKey(id);
        resourcesDO.setOpenCount(resourcesDO.getOpenCount()+1);
        navigationResourcesDOMapper.updateByPrimaryKey(resourcesDO);


        NavigationResourcesRespDTO respDTO = NavigationReqToDo.INSTANCE.doToResp(resourcesDO);
        respDTO.setIsLiked(favoritesDOMapper.selectByUserNavId(UserSessionUtil.getCurreentUserByKey().getId(),id) != null);
        respDTO.setCreateDateStr(DateUtil.formatDateTime(respDTO.getCreateDate()));
        respDTO.setLikeCount(favoritesDOMapper.countByNavId(id));
        return respDTO;
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        Integer a = 1;
        Integer b = 2;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a ->>" + a);
        System.out.println("b ->>" + b);
//        InetAddress ia = InetAddress.getLocalHost();
//        System.out.println(ia);
//        getLocalMac(ia);
    }

    private static void getLocalMac(InetAddress ia) throws SocketException {
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        System.out.println("mac数组长度："+mac.length);
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append(":");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            System.out.println("每8位:"+str);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {

                sb.append(str);
            }
        }
        System.out.println("本机MAC地址:"+sb.toString().toUpperCase());


    }
}
