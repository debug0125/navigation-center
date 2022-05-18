package com.pzc.navigationweb.esJob;

import cn.hutool.http.HttpUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.dto.reqdto.DictionaryReqDTO;
import com.pzc.navigationweb.service.DictionaryInService;
import com.pzc.navigationweb.service.LotteryInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ryf
 * @date 5/17/22 8:01 PM
 */
@Component
public class TestTaskJob {


    private Logger logger = LoggerFactory.getLogger("task");

//    @Scheduled(fixedRate = 5000)
    public void execute() {

        logger.info("=======>>>> 进入测试定时器...");
    }
}
