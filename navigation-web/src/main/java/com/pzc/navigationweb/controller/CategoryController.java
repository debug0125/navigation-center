package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.application.process.AddCategoryProcess;
import com.pzc.navigationweb.application.process.QueryCategoryListProcess;
import com.pzc.navigationweb.application.process.RemoveCategoryProcess;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
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
    private QueryCategoryListProcess queryCategoryListProcess;
    @Autowired
    private AddCategoryProcess addCategoryProcess;
    @Autowired
    private RemoveCategoryProcess removeCategoryProcess;

    @RequestMapping("/getCategoryList")
    public Result<List<CategoryRespDTO>> getCategoryList(CategoryReqDTO categoryReqDTO) {
        return queryCategoryListProcess.start(categoryReqDTO);
    }

    @RequestMapping("/addCategory")
    public Result<CategoryRespDTO> addCategory(CategoryReqDTO categoryReqDTO) {
        return addCategoryProcess.start(categoryReqDTO);
    }

    @RequestMapping("/removeCategory")
    public Result<Boolean> removeCategory(CategoryReqDTO categoryReqDTO) {
        return removeCategoryProcess.start(categoryReqDTO);
    }
}
