package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.dto.query.CountThemeQuery;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.respdto.CountThemeRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 2/11/22 3:07 PM
 */
public class PageCountThemeProcess extends DefaultProcess<CountThemeQuery, Page<CountThemeRespDTO>> {
    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @Override
    public void process(Context<CountThemeQuery, Page<CountThemeRespDTO>> context) {
        CountThemeQuery query = context.getParam();
        context.setResultModule(null);
    }
}
