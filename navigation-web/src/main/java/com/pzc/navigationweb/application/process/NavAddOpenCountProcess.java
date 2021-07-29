package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.dto.reqdto.AddNavOpenCountReqDTO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class NavAddOpenCountProcess extends DefaultProcess<AddNavOpenCountReqDTO, NavigationResourcesRespDTO> {

    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @Override
    public void process(Context<AddNavOpenCountReqDTO, NavigationResourcesRespDTO> context) {
        AddNavOpenCountReqDTO reqDTO = context.getParam();
        context.setResultModule(navigationResourcesService.addOpenCount(reqDTO));
    }
}
