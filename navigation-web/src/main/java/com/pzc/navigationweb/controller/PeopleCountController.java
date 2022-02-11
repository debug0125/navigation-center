package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.respdto.CountThemeRespDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ryf
 * @date 2/11/22 10:09 AM
 */
@RestController
@RequestMapping(PeopleCountController.PAGE_NAME)
public class PeopleCountController extends BaseController {
    public static final String PAGE_NAME = "/pCount";

    @RequestMapping("/pageCountTheme")
    public Result<Page<CountThemeRespDTO>> pageCountTheme(NavigationQuery query) {
        return null;
    }


}
