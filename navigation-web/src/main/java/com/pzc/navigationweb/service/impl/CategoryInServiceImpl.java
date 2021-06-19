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
import com.pzc.navigationweb.dao.NavigationResourcesDOMapper;
import com.pzc.navigationweb.domain.dbdo.CategoryDO;
import com.pzc.navigationweb.domain.dbdo.FavoritesDO;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.domain.mapstruct.CategoryMapStruct;
import com.pzc.navigationweb.domain.mapstruct.NavigationReqToDo;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.CategoryInService;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class CategoryInServiceImpl implements CategoryInService {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @Override
    public List<CategoryRespDTO> getCategoryList() {
        List<CategoryDO> categoryDOS = categoryDOMapper.getCategoryList("");
        if (CollectionUtils.isNotEmpty(categoryDOS)) {
            List<CategoryRespDTO> respDTOList = CategoryMapStruct.INSTANCE.reqToDo(categoryDOS);
            respDTOList.stream().forEach(x -> {
                List<CategoryRespDTO> respDTOListSon =
                        CategoryMapStruct.INSTANCE.reqToDo(Optional.ofNullable(categoryDOMapper.getCategoryList(x.getId()))
                                .orElse(new ArrayList<>()));
                x.setCategoryList(respDTOListSon);
            });
            return respDTOList;
        }
        return new ArrayList<>();
    }
}
