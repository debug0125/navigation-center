package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class SubmitNavigationProcess extends DefaultProcess<NavigationResourcesReqDTO, Boolean> {

    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @Override
    public void process(Context<NavigationResourcesReqDTO, Boolean> context) {
        NavigationResourcesReqDTO reqDTO = context.getParam();
        context.setResultModule(navigationResourcesService.submit(reqDTO));
    }
}
