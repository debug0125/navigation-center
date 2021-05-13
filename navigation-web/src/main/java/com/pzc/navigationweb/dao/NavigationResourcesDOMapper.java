package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;

import java.util.List;

public interface NavigationResourcesDOMapper {

    List<NavigationResourcesRespDTO> listNavigation(NavigationQuery query);

    int deleteByPrimaryKey(String id);

    int insert(NavigationResourcesDO record);

    int insertSelective(NavigationResourcesDO record);

    NavigationResourcesDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NavigationResourcesDO record);

    int updateByPrimaryKey(NavigationResourcesDO record);
}