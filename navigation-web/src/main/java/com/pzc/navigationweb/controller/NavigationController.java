package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(NavigationController.PAGE_NAME)
public class NavigationController {
    public static final String PAGE_NAME = "/navigation";

    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @RequestMapping("/submit")
    public Result<Boolean> submit(NavigationResourcesReqDTO navigationResourcesReqDTO) {
        return navigationResourcesService.submit(navigationResourcesReqDTO);
    }
}
