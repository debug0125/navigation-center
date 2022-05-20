package com.pzc.navigationweb.esJob;

import com.pzc.navigationweb.service.LotteryInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ryf
 * @date 5/17/22 8:01 PM
 */
@Component
public class TestTaskJob {

    @Autowired
    private LotteryInService lotteryInService;

    private Logger logger = LoggerFactory.getLogger("task");

//    @Scheduled(cron = "0 28 23 * * ?")
    @Scheduled(cron = "0 45 09 ? * MON,WED,FRI")
    public void execute() {

        System.out.println("=======>>>> 进入测试定时器...");

        System.out.println(lotteryInService.getMaxEventDate());

//        logger.info("=======>>>> 进入测试定时器...");
    }
}
