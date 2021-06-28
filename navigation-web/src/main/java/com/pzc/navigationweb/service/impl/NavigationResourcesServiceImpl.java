package com.pzc.navigationweb.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.InitDOUtil;
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
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Boolean> submit(NavigationResourcesReqDTO navigationResourcesReqDTO) {
        Result<Boolean> result = new Result<>();

        if(CollectionUtils.isEmpty(navigationResourcesReqDTO.getCategoryIds())) {
            result.setSuccess(false);
            result.setErrMsg("标签不能为空");
            return result;
        }
        // 设置默认值
        if (StringUtils.isEmpty(navigationResourcesReqDTO.getLogoUrl())) {
            navigationResourcesReqDTO.setLogoUrl(ImgConstants.DEFAULT_PICTURE);
        }
        NavigationResourcesDO navigationResourcesDO = NavigationReqToDo.INSTANCE.reqToDo(navigationResourcesReqDTO);
        InitDOUtil.initField(navigationResourcesDO);
        result.setModule(navigationResourcesDOMapper.insertSelective(navigationResourcesDO) > 0);

        // 新增标签
        navigationResourcesReqDTO.getCategoryIds().stream().forEach(x -> {
            NavCategoryDO navCategoryDO = new NavCategoryDO();
            InitDOUtil.initField(navCategoryDO);
            navCategoryDO.setNavId(navigationResourcesDO.getId());
            navCategoryDO.setCategoryId(x);
            navCategoryDOMapper.insertSelective(navCategoryDO);
        });
        if (result.getModule()) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setErrMsg("推荐失败");
        }
        return result;
    }

    @Override
    public Result<PageInfo<NavigationResourcesRespDTO>> pageNavigation(NavigationQuery query) {
        Result<PageInfo<NavigationResourcesRespDTO>> result = new Result<>();
        Page<NavigationResourcesRespDTO> page = PageHelper.startPage(query.getPageNo(), query.getPageSize(), true);
        navigationResourcesDOMapper.listNavigation(query);
        PageInfo<NavigationResourcesRespDTO> pageInfo = new PageInfo<>(page);
        pageInfo.getList().stream().forEach(x -> {
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
        result.setModule(pageInfo);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<NavigationResourcesRespDTO> addOpenCount(String id) {
        Result<NavigationResourcesRespDTO> result = new Result<>();
        NavigationResourcesDO resourcesDO = navigationResourcesDOMapper.selectByPrimaryKey(id);
        resourcesDO.setOpenCount(resourcesDO.getOpenCount()+1);
        result.setSuccess(navigationResourcesDOMapper.updateByPrimaryKey(resourcesDO) > 0);


        NavigationResourcesRespDTO respDTO = NavigationReqToDo.INSTANCE.doToResp(resourcesDO);
        respDTO.setIsLiked(favoritesDOMapper.selectByUserNavId(UserSessionUtil.getCurreentUserByKey().getId(),id) != null);
        respDTO.setCreateDateStr(DateUtil.formatDateTime(respDTO.getCreateDate()));
        respDTO.setLikeCount(favoritesDOMapper.countByNavId(id));
        result.setModule(respDTO);
        return result;
    }

    @Override
    public Result<NavigationResourcesRespDTO> toFavorite(String userId, String navId, Boolean isLiked) {
        Result<NavigationResourcesRespDTO> result = new Result<>();
        NavigationResourcesDO resourcesDO = navigationResourcesDOMapper.selectByPrimaryKey(navId);


        FavoritesDO favoritesDO = new FavoritesDO();
        if (isLiked) {
            // 已收藏 --> 取消收藏
            favoritesDO = favoritesDOMapper.selectByUserNavId(userId,navId);
            favoritesDOMapper.removeByPrimaryKey(favoritesDO.getId());
            resourcesDO.setLikeCount(resourcesDO.getLikeCount()-1);
        } else {
            InitDOUtil.initField(favoritesDO);
            favoritesDO.setUserId(userId);
            favoritesDO.setNavId(navId);
            favoritesDOMapper.insert(favoritesDO);
            resourcesDO.setLikeCount(resourcesDO.getLikeCount()+1);
        }
        navigationResourcesDOMapper.updateByPrimaryKey(resourcesDO);

        NavigationResourcesRespDTO respDTO = NavigationReqToDo.INSTANCE.doToResp(resourcesDO);
        respDTO.setIsLiked(!isLiked);
        result.setSuccess(true);
        result.setModule(respDTO);
        return result;
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia);
        getLocalMac(ia);
    }

    private static void getLocalMac(InetAddress ia) throws SocketException {
        // TODO Auto-generated method stub
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
