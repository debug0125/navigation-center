package com.pzc.navigationweb.controller;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.application.process.NavAddOpenCountProcess;
import com.pzc.navigationweb.application.process.PageNavigationProcess;
import com.pzc.navigationweb.application.process.SubmitNavigationProcess;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.AddNavOpenCountReqDTO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(NavigationController.PAGE_NAME)
public class NavigationController extends BaseController {
    public static final String PAGE_NAME = "/navigation";

    @Autowired
    private SubmitNavigationProcess submitNavigationProcess;
    @Autowired
    private PageNavigationProcess pageNavigationProcess;
    @Autowired
    private NavAddOpenCountProcess navAddOpenCountProcess;

    @RequestMapping("/submit")
    public Result<Boolean> submit(NavigationResourcesReqDTO navigationResourcesReqDTO) {
        return submitNavigationProcess.start(navigationResourcesReqDTO);
    }

    @RequestMapping("/pageNavigation")
    public Result<PageInfo<NavigationResourcesRespDTO>> pageNavigation(NavigationQuery query) {
        return pageNavigationProcess.start(query);
    }

    // todo 前端改造入参
    @RequestMapping("/addOpenCount")
    public Result<NavigationResourcesRespDTO> addOpenCount(AddNavOpenCountReqDTO reqDTO) {
        return navAddOpenCountProcess.start(reqDTO);
    }
}
