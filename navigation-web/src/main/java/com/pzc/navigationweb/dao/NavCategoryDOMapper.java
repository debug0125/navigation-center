package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.NavCategoryDO;

import java.util.List;

public interface NavCategoryDOMapper {

    List<NavCategoryDO> queryListByCategoryId(NavCategoryDO navCategoryDO);


    int deleteByPrimaryKey(String id);
    int insert(NavCategoryDO record);
    int insertSelective(NavCategoryDO record);
    NavCategoryDO selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(NavCategoryDO record);
    int updateByPrimaryKey(NavCategoryDO record);
}