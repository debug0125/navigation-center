package com.pzc.navigationweb.service;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 3:12 PM
 */
public interface CategoryInService {

    Result<List<CategoryRespDTO>> getCategoryList();

    Result<CategoryRespDTO> addCategory(CategoryReqDTO categoryReqDTO);

    Result<Boolean> removeCategory(CategoryReqDTO categoryReqDTO);
}
