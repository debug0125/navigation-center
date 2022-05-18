package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.constant.enumtype.LotteryType;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.service.LotteryInService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class AddCustomLotteryNumProcess extends DefaultProcess<LotteryReqDTO, Boolean> {


    @Autowired
    private LotteryInService lotteryInService;

    @Override
    public void process(Context<LotteryReqDTO, Boolean> context) {
        LotteryReqDTO reqDTO = context.getParam();
        reqDTO.setType(LotteryType.CUSTOM_NUM.getType());
        context.setResultModule(lotteryInService.addCustomLotteryNumber(reqDTO));
    }
}
