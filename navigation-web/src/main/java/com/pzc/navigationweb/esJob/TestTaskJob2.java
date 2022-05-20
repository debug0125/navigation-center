package com.pzc.navigationweb.esJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ryf
 * @date 5/17/22 8:01 PM
 */
@Component
public class TestTaskJob2 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Logger logger = LoggerFactory.getLogger("task");

    @Scheduled(cron = "0 45 06 20 5 ?")
    public void execute() {

        System.out.println("=======>>>> 进入测试定时器...");

        logger.info("=======>>>> 6点20分 进入测试定时器 TestTaskJob... {}",dateFormat.format(new Date()));
    }
}
