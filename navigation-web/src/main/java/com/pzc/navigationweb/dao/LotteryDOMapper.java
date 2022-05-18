package com.pzc.navigationweb.dao;

import com.pzc.navigationweb.domain.dbdo.LotteryDO;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;

import java.util.List;

public interface LotteryDOMapper {



    Integer countGroupEventDate(LotteryQuery lotteryQuery);
    List<String> groupEventDate(LotteryQuery lotteryQuery);

    List<LotteryDO> queryBySelective(LotteryQuery lotteryQuery);

    int deleteByPrimaryKey(String id);

    int insert(LotteryDO record);

    int insertSelective(LotteryDO record);

    LotteryDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LotteryDO record);

    int updateByPrimaryKey(LotteryDO record);

    String getMaxEventDate();

    String existEventDate(String eventDate);
}