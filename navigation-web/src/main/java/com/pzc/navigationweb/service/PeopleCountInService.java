package com.pzc.navigationweb.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.domain.dbdo.CountThemeDO;
import com.pzc.navigationweb.dto.query.CountThemeQuery;
import com.pzc.navigationweb.dto.respdto.CountThemeRespDTO;

/**
 * @author ryf
 * @date 2/11/22 3:09 PM
 */
public interface PeopleCountInService {

    Page<CountThemeRespDTO> pageCountTheme(CountThemeQuery countThemeQuery);


}
