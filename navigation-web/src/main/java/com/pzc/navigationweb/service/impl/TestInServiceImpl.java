package com.pzc.navigationweb.service.impl;
import java.util.Date;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.constant.enumtype.LotteryType;
import com.pzc.navigationweb.dao.FavoritesDOMapper;
import com.pzc.navigationweb.dao.LotteryDOMapper;
import com.pzc.navigationweb.domain.dbdo.FavoritesDO;
import com.pzc.navigationweb.domain.dbdo.LotteryDO;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.DictionaryReqDTO;
import com.pzc.navigationweb.dto.reqdto.LotteryReqDTO;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;
import com.pzc.navigationweb.service.DictionaryInService;
import com.pzc.navigationweb.service.LotteryInService;
import com.pzc.navigationweb.service.TestInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
@Service
public class TestInServiceImpl implements TestInService {

    @Autowired
    private FavoritesDOMapper favoritesDOMapper;

    @Autowired
    private TransactionTemplate navigationTransactionTemplate;

    @Override
    public void testRollBack() {
        try {
            navigationTransactionTemplate.execute(status -> {
                insertTest("1");
                int i = 1/0;

                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            navigationTransactionTemplate.execute(status -> {
               insertTest("2");
                return null;
            });
        }

    }

    public void insertTest(String index) {
        FavoritesDO favoritesDO = new FavoritesDO();
        InitDOUtil.initField(favoritesDO);
        favoritesDO.setUserId(index);
        favoritesDO.setNavId(index);
        favoritesDOMapper.insert(favoritesDO);
    }
}
