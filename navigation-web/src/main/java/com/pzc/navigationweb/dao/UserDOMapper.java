package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.UserDO;

public interface UserDOMapper {

    UserDO getByAccount(String account);

    int deleteByPrimaryKey(String id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}