package com.pzc.navigationweb.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.constant.enumtype.LotteryType;
import com.pzc.navigationweb.dao.LotteryDOMapper;
import com.pzc.navigationweb.domain.dbdo.LotteryDO;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.DictionaryReqDTO;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;
import com.pzc.navigationweb.service.DictionaryInService;
import com.pzc.navigationweb.service.LotteryInService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class LotteryInServiceImpl implements LotteryInService {

    @Autowired
    private LotteryDOMapper lotteryDOMapper;
    @Autowired
    private DictionaryInService dictionaryInService;

    @Override
    public Page<LotteryRespDTO> pageLottery(LotteryQuery lotteryQuery) {
        Integer count = lotteryDOMapper.countGroupEventDate(lotteryQuery);
        Page<LotteryRespDTO> lotteryRespDTOPage =
                new Page<>(lotteryQuery.getPageNo(),lotteryQuery.getPageSize(),count);
        if (count > 0) {
            List<String> groupEventDateList = lotteryDOMapper.groupEventDate(lotteryQuery);
            List<LotteryRespDTO> respDTOList = new ArrayList<>();
            groupEventDateList.stream().forEach(x -> {
                lotteryQuery.setEventDate(x);
                // 查询开奖号码
                lotteryQuery.setType(LotteryType.SYSTEM_NUM.getType());
                List<LotteryDO> systemLotteryList = lotteryDOMapper.queryBySelective(lotteryQuery);
                // 查询自定义号码
                lotteryQuery.setType(LotteryType.CUSTOM_NUM.getType());
                List<LotteryDO> customLotteryList = lotteryDOMapper.queryBySelective(lotteryQuery);
                if (CollectionUtil.isNotEmpty(customLotteryList)) {
                    customLotteryList.stream().forEach(customLottery -> {
                        LotteryRespDTO lotteryRespDTO = new LotteryRespDTO();
                        lotteryRespDTO.setEventDate(customLottery.getEventDate());
                        lotteryRespDTO.setCustomNormalNum(customLottery.getNormalNum());
                        lotteryRespDTO.setCustomSpecialNum(customLottery.getSpecialNum());

                        // 赋值开奖号码
                        if (CollectionUtil.isNotEmpty(systemLotteryList)) {
                            lotteryRespDTO.setNormalNum(systemLotteryList.get(0).getNormalNum());
                            lotteryRespDTO.setSpecialNum(systemLotteryList.get(0).getSpecialNum());
                        }
                        respDTOList.add(lotteryRespDTO);

                    });
                } else {
                    LotteryRespDTO lotteryRespDTO = new LotteryRespDTO();
                    // 无自定义号码
                    lotteryRespDTO.setEventDate(systemLotteryList.get(0).getEventDate());
                    lotteryRespDTO.setNormalNum(systemLotteryList.get(0).getNormalNum());
                    lotteryRespDTO.setSpecialNum(systemLotteryList.get(0).getSpecialNum());
                    respDTOList.add(lotteryRespDTO);
                }
            });
            lotteryRespDTOPage.setResult(respDTOList);

        }
        return lotteryRespDTOPage;
    }

    @Override
    public boolean addCustomLotteryNumber(LotteryReqDTO lotteryReqDTO) {
        String maxEventDate = StrUtil.isNotBlank(lotteryReqDTO.getEventDate()) ? lotteryReqDTO.getEventDate() : this.getMaxEventDate();
        if (lotteryDOMapper.existEventDate(maxEventDate) != null) {
            return false;
        }
        LotteryDO lotteryDO = new LotteryDO();
        InitDOUtil.initField(lotteryDO);
        lotteryDO.setEventDate(maxEventDate);

        lotteryDO.setNormalNum(getFormatNum(lotteryReqDTO.getNormalNum()));

        lotteryDO.setSpecialNum(getFormatNum(lotteryReqDTO.getSpecialNum()));

        lotteryDO.setType(lotteryReqDTO.getType());
        return lotteryDOMapper.insertSelective(lotteryDO) > 0;
    }

    @Override
    public String getMaxEventDate() {
        String maxEventDate = RedisUtil.op().getV(RedisKeyConstant.DLT_DATE_KEY);
        if (StrUtil.isBlank(maxEventDate)) {
            // 查询字典表，并推送至redis
            DictionaryReqDTO reqDTO = new DictionaryReqDTO();
            reqDTO.setDicKey(RedisKeyConstant.DLT_DATE_KEY);
            // 获取当前最大期号
            maxEventDate = lotteryDOMapper.getMaxEventDate();
        }
        return maxEventDate;
    }

    private static String getFormatNum(String s){

        List<String> stringList = Arrays.asList(s.split(" "));
        stringList = stringList.stream().map(num -> {
            return String.format("%02d",Integer.valueOf(num));
        }).collect(Collectors.toList());
        return stringList.stream().collect(Collectors.joining(" "));
    }

}
