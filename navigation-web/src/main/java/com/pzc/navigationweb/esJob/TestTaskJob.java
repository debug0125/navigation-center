package com.pzc.navigationweb.esJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ryf
 * @date 5/17/22 8:01 PM
 */
@Component
public class TestTaskJob {


    private Logger logger = LoggerFactory.getLogger("task");

//    @Scheduled(cron = "0 55 9 * * ? ")
    public void execute() {

        System.out.println("=======>>>> 进入测试定时器...");

//        logger.info("=======>>>> 进入测试定时器...");
    }
}
