package com.pzc.navigationweb.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.dao.DictionaryDOMapper;
import com.pzc.navigationweb.domain.dbdo.DictionaryDO;
import com.pzc.navigationweb.dto.reqdto.DictionaryReqDTO;
import com.pzc.navigationweb.service.DictionaryInService;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class DictionaryInServiceImpl implements DictionaryInService {

    private Logger logger = LoggerFactory.getLogger("process");

    @Autowired
    private DictionaryDOMapper dictionaryDOMapper;

    @Override
    public String editDictionaryByKey(DictionaryReqDTO dictionaryReqDTO) {
        String value = "";
        if (dictionaryReqDTO.getDicKey().equalsIgnoreCase(RedisKeyConstant.DLT_DATE_KEY)) {
            DictionaryDO dictionaryDO = dictionaryDOMapper.selectByPrimaryKey(dictionaryReqDTO.getDicKey());
            String currentEventDate = dictionaryReqDTO.getDicValue();
            if (StrUtil.isBlank(currentEventDate)) {
                currentEventDate = getDLTListInfo().get(0);
            }

            value = String.valueOf(Integer.valueOf(currentEventDate) + 1);
            if (dictionaryDO != null) {
                dictionaryDO.setDicValue(value);
                dictionaryDOMapper.updateByPrimaryKey(dictionaryDO);
            } else {
                dictionaryDO = new DictionaryDO();

                if (StrUtil.isNotBlank(dictionaryReqDTO.getId())) {
                    InitDOUtil.req2DO(dictionaryReqDTO,dictionaryDO);
                } else {
                    InitDOUtil.initField(dictionaryDO);
                }

                dictionaryDO.setDicValue(value);
                dictionaryDO.setDicKey(dictionaryReqDTO.getDicKey());
                dictionaryDOMapper.insertSelective(dictionaryDO);
            }
        }
        if (StrUtil.isNotBlank(value)) {
            RedisUtil.op().setV(dictionaryReqDTO.getDicKey(),value);
        }
        return value;
    }

    private List<String> getDLTListInfo() {
        List<String> resultList = new ArrayList<>();
        String path = "/Users/orange_r/Desktop/DLT.txt";
        String url = "http://kaijiang.500.com/shtml/dlt/22001.shtml?0_ala_baidu";
        List<String> regexList = null;
        try {
            String content = HttpUtil.get(url);
            File file = new File(path);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            osw.write(content);
            osw.close();


            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            String regexDate = "<strong>\\d*</strong>";
            String regexNum = "<td>\\d{1,2}(\\s\\d+)*.\\d{1,2}\\s\\d+</td>";
            regexList = new ArrayList<>();
            regexList.add(regexDate);
            regexList.add(regexNum);
            String buf;
            while ((buf = br.readLine()) != null) {

                String finalBuf = buf;
                regexList.stream().forEach(x -> {
                    Pattern proInfo = Pattern.compile(x, Pattern.DOTALL);
                    Matcher buf_m = proInfo.matcher(finalBuf);
                    if (buf_m.find()) {
                        String bugGroup = buf_m.group();
                        resultList.add(bugGroup.substring(bugGroup.indexOf('>') + 1,
                                bugGroup.lastIndexOf('<')));
                    }
                });
            }
        } catch (IOException e) {
            logger.error("爬虫失败>>>>{}", e.getMessage());
        } finally {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
        return resultList;
    }
}
