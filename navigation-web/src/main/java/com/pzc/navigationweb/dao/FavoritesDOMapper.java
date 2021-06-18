package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.FavoritesDO;
import org.apache.ibatis.annotations.Param;

public interface FavoritesDOMapper {

    FavoritesDO selectByUserNavId(@Param("userId") String userId, @Param("navId") String navId);

    Integer countByNavId(String navId);

    int deleteByPrimaryKey(String id);
    int removeByPrimaryKey(String id);
    int insert(FavoritesDO record);
    int insertSelective(FavoritesDO record);
    FavoritesDO selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(FavoritesDO record);
    int updateByPrimaryKey(FavoritesDO record);
}