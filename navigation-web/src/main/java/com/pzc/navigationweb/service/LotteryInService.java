package com.pzc.navigationweb.service;

import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 3:12 PM
 */
public interface LotteryInService {

    Page<LotteryRespDTO> pageLottery(LotteryQuery lotteryQuery);

    /**
     * 新增自定义彩票号码
     * @param lotteryReqDTO
     * @return
     */
    boolean addCustomLotteryNumber(LotteryReqDTO lotteryReqDTO);
}
