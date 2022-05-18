package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.application.process.AddCustomLotteryNumProcess;
import com.pzc.navigationweb.application.process.PageLotteryProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;
import com.pzc.navigationweb.esJob.GetLotteryNumberTaskJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(BackDoorController.PAGE_NAME)
public class BackDoorController extends BaseController {
    public static final String PAGE_NAME = "/backDooor";

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());

    @Autowired
    private GetLotteryNumberTaskJob getLotteryNumberTaskJob;

    @RequestMapping("/getLotteryNumberTaskJob")
    @ResponseBody
    public String getLotteryNumberTaskJob() {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                getLotteryNumberTaskJob.execute();
            }
        });
        return "正在拉取大乐透数据，请稍等查看...";
    }
}
