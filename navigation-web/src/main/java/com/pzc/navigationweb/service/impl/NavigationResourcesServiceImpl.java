package com.pzc.navigationweb.service.impl;

import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.dao.NavigationResourcesDOMapper;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.domain.mapstruct.NavigationReqToDo;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class NavigationResourcesServiceImpl implements NavigationResourcesService {

    @Autowired
    private NavigationResourcesDOMapper navigationResourcesDOMapper;

    @Override
    public Boolean submit(NavigationResourcesReqDTO navigationResourcesReqDTO) {
        NavigationResourcesDO navigationResourcesDO = NavigationReqToDo.INSTANCE.reqToDo(navigationResourcesReqDTO);
        InitDOUtil.initField(navigationResourcesDO);
        return navigationResourcesDOMapper.insertSelective(navigationResourcesDO) > 0;
    }
}
