package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.FavoriteInService;
import com.pzc.navigationweb.service.LotteryInService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class PageLotteryProcess extends DefaultProcess<LotteryQuery, Page<LotteryRespDTO>> {

    @Autowired
    private LotteryInService lotteryInService;

    @Override
    public void process(Context<LotteryQuery, Page<LotteryRespDTO>> context) {
        LotteryQuery query = context.getParam();
        context.setResultModule(lotteryInService.pageLottery(query));
    }
}
