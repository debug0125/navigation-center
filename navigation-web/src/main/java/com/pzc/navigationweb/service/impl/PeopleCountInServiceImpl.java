package com.pzc.navigationweb.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageHelper;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.dao.CountThemeDOMapper;
import com.pzc.navigationweb.domain.dbdo.CountThemeDO;
import com.pzc.navigationweb.domain.mapstruct.CountThemeMapStruct;
import com.pzc.navigationweb.dto.query.CountThemeQuery;
import com.pzc.navigationweb.dto.respdto.CountThemeRespDTO;
import com.pzc.navigationweb.service.PeopleCountInService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class PeopleCountInServiceImpl implements PeopleCountInService {

    @Autowired
    private CountThemeDOMapper countThemeDOMapper;

    @Override
    public Page<CountThemeRespDTO> pageCountTheme(CountThemeQuery query) {
        IPage<CountThemeDO> userPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getPageNo(),query.getPageSize());
        IPage<CountThemeDO> countThemeDOIPage = countThemeDOMapper.selectPage(userPage, null);

        Page<CountThemeRespDTO> countThemeRespDTOPage = new Page<>(query.getPageNo(), query.getPageSize(),
                (int)countThemeDOIPage.getTotal());

        if (CollectionUtil.isNotEmpty(countThemeDOIPage.getRecords())) {
            List<CountThemeRespDTO> countThemeRespDTOList =
                    CountThemeMapStruct.INSTANCE.doListToRespList(countThemeDOIPage.getRecords());
            countThemeRespDTOPage.setResult(countThemeRespDTOList);
        }


        return countThemeRespDTOPage;
    }
}
