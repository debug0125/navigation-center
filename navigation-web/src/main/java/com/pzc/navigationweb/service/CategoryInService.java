package com.pzc.navigationweb.service;

import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 3:12 PM
 */
public interface CategoryInService {

    List<CategoryRespDTO> getCategoryList();

    CategoryRespDTO addCategory(CategoryReqDTO categoryReqDTO);

    Boolean removeCategory(CategoryReqDTO categoryReqDTO);
}
