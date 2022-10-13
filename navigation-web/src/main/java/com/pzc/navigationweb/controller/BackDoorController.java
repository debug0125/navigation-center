package com.pzc.navigationweb.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pzc.navigationweb.application.process.AddCustomLotteryNumProcess;
import com.pzc.navigationweb.application.process.PageLotteryProcess;
import com.pzc.navigationweb.aspect.annotation.RedisLockAnnotation;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.constant.enumtype.RedisLockTypeEnum;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;
import com.pzc.navigationweb.esJob.GetLotteryNumberTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(BackDoorController.PAGE_NAME)
public class BackDoorController extends BaseController {
    private Logger logger = LoggerFactory.getLogger("task");

    public static final String PAGE_NAME = "/backDooor";

    @Autowired
    private GetLotteryNumberTaskJob getLotteryNumberTaskJob;

    @RequestMapping("/getLotteryNumberTaskJob")
    @ResponseBody
    public String getLotteryNumberTaskJob() {
        getLotteryNumberTaskJob.execute();
        return "拉取大乐透数据成功，请到页面查看...";
    }

    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 5)
    @RequestMapping("/testRedisLock")
    @ResponseBody
    public String testRedisLock(Long userId) {
        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep(1000);

                System.out.println("打印>>>> " + i + ",时间：" + DateUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_PATTERN));
            }
            logger.info("逻辑执行完成");
        } catch (InterruptedException e) {
            logger.error("方法出现异常");
        }
        return "testRedisLock...";
    }
}
