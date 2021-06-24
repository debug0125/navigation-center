package com.pzc.navigationweb.controller;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.common.util.UserSessionUtil;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.CategoryInService;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(CategoryController.PAGE_NAME)
public class CategoryController extends BaseController {
    public static final String PAGE_NAME = "/category";

    @Autowired
    private CategoryInService categoryInService;

    @RequestMapping("/getCategoryList")
    public Result<List<CategoryRespDTO>> getCategoryList() {
        return categoryInService.getCategoryList();
    }

    @RequestMapping("/addCategory")
    public Result<CategoryRespDTO> addCategory(CategoryReqDTO categoryReqDTO) {
        return categoryInService.addCategory(categoryReqDTO);
    }

    @RequestMapping("/removeCategory")
    public Result<Boolean> removeCategory(CategoryReqDTO categoryReqDTO) {
        return categoryInService.removeCategory(categoryReqDTO);
    }
}
