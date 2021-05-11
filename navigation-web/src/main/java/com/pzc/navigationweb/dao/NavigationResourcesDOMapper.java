package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;

public interface NavigationResourcesDOMapper {

    int deleteByPrimaryKey(String id);

    int insert(NavigationResourcesDO record);

    int insertSelective(NavigationResourcesDO record);

    NavigationResourcesDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NavigationResourcesDO record);

    int updateByPrimaryKey(NavigationResourcesDO record);
}