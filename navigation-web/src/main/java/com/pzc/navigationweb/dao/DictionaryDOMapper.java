package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.DictionaryDO;

public interface DictionaryDOMapper {

    int deleteByPrimaryKey(String dicKey);

    int insert(DictionaryDO record);

    int insertSelective(DictionaryDO record);

    DictionaryDO selectByPrimaryKey(String dicKey);

    int updateByPrimaryKeySelective(DictionaryDO record);

    int updateByPrimaryKey(DictionaryDO record);
}