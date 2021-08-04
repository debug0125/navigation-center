package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class PageNavigationProcess extends DefaultProcess<NavigationQuery, Page<NavigationResourcesRespDTO>> {
    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @Override
    public void process(Context<NavigationQuery, Page<NavigationResourcesRespDTO>> context) {
        NavigationQuery query = context.getParam();
        context.setResultModule(navigationResourcesService.pageNavigation(query));
    }
}
