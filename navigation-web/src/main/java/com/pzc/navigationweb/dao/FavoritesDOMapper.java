package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.FavoritesDO;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesDOMapper {

    Integer countByUserAndCategory(FavoriteQuery query);
    List<FavoritesDO> listByUserAndCategory(FavoriteQuery query);

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