package com.pzc.navigationweb.esJob;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.constant.enumtype.LotteryType;
import com.pzc.navigationweb.dto.reqdto.DictionaryReqDTO;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.service.DictionaryInService;
import com.pzc.navigationweb.service.LotteryInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ryf
 * @date 5/17/22 8:01 PM
 */
@Component
public class GetLotteryNumberTaskJob {


    private Logger logger = LoggerFactory.getLogger("task");
    private Logger loggerP = LoggerFactory.getLogger("process");

    @Value("lottery.path")
    private String lotteryPath;

    @Autowired
    private LotteryInService lotteryInService;
    @Autowired
    private DictionaryInService dictionaryInService;

    @Scheduled(cron = "0 0 0 20 * ?")
    public void execute() {

        logger.info("=======>>>> 进入定时器...");
        // 当前大乐透期号
        AtomicReference<String> currentMaxEventDate = new AtomicReference<>(lotteryInService.getMaxEventDate());
        String currentYear = String.valueOf(DateUtil.year(new Date())).substring(2);

        // 下一年则从头开始计算
        if (!currentMaxEventDate.get().substring(0,2).equals(currentYear)) {
            currentMaxEventDate.set(currentYear + "000");
        }

        String path = "/Users/orange_r/Desktop/DLT.txt";
//        String path = lotteryPath;
        logger.info("=======>>>> 文件地址: {}", path);

        // 今日大乐透期号
        Integer todayEventDate = Integer.valueOf(currentMaxEventDate.get()) + 1;

        try {
            while (true) {
                String url = "http://kaijiang.500.com/shtml/dlt/" + todayEventDate + ".shtml?0_ala_baidu";
//                String url = "http://kaijiang.500.com/shtml/dlt/22055.shtml?0_ala_baidu";
                String content = HttpUtil.get(url);
                if (content.contains("<title>404 Not Found</title>")) {
                    return;
                }
                File file = new File(path);
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                osw.write(content);
                osw.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

                String regexDate = "<strong>\\d*</strong>";
                String regexNum = "<td>\\d{1,2}(\\s\\d+)*.\\d{1,2}\\s\\d+</td>";
                List<String> regexList = new ArrayList<>();
                regexList.add(regexDate);
                regexList.add(regexNum);
                String buf;
                while ((buf = br.readLine()) != null) {

                    String finalBuf = buf;
                    Integer finalTodayEventDate = todayEventDate;
                    regexList.stream().forEach(x -> {
                        Pattern proInfo = Pattern.compile(x, Pattern.DOTALL);
                        Matcher buf_m = proInfo.matcher(finalBuf);
                        if (buf_m.find()) {
                            String bugGroup = buf_m.group();

                            String bugGroupInfo = bugGroup.substring(bugGroup.indexOf('>') + 1,
                                    bugGroup.lastIndexOf('<'));
                            logger.info(bugGroupInfo);

                            if (x.equals(regexNum) && StrUtil.isNotBlank(bugGroup)) {
                                currentMaxEventDate.set(String.valueOf(finalTodayEventDate - 1));
                                loggerP.info("todayEventDate: {}, currentMaxEventDate: {}",finalTodayEventDate,currentMaxEventDate.get());
                                String[] aArr = bugGroupInfo.split(Pattern.quote("|"));
                                LotteryReqDTO lotteryReqDTO = new LotteryReqDTO();
                                lotteryReqDTO.setEventDate(finalTodayEventDate.toString());
                                lotteryReqDTO.setNormalNum(Arrays.asList(aArr[0].split(" ")).stream().sorted().collect(Collectors.joining(" ")));
                                lotteryReqDTO.setSpecialNum(Arrays.asList(aArr[1].split(" ")).stream().sorted().collect(Collectors.joining(" ")));
                                lotteryReqDTO.setType(LotteryType.SYSTEM_NUM.getType());
                                lotteryInService.addCustomLotteryNumber(lotteryReqDTO);
                            }

                        }
                    });
                }
                todayEventDate++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // 维护最大期号
            DictionaryReqDTO reqDTO = new DictionaryReqDTO();
            reqDTO.setDicKey(RedisKeyConstant.DLT_DATE_KEY);
            reqDTO.setDicValue(currentMaxEventDate.get());
            dictionaryInService.editDictionaryByKey(reqDTO);
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(DateUtil.year(new Date())).substring(2));
    }
}
