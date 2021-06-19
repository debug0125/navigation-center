package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.CategoryDO;

import java.util.List;

public interface CategoryDOMapper {

    List<CategoryDO> getCategoryList(String fatherId);


    int deleteByPrimaryKey(String id);
    int insert(CategoryDO record);
    int insertSelective(CategoryDO record);
    CategoryDO selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(CategoryDO record);
    int updateByPrimaryKey(CategoryDO record);
}