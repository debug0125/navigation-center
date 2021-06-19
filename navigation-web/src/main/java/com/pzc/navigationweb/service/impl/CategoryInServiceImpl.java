package com.pzc.navigationweb.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dao.CategoryDOMapper;
import com.pzc.navigationweb.domain.dbdo.CategoryDO;
import com.pzc.navigationweb.domain.mapstruct.CategoryMapStruct;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.service.CategoryInService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class CategoryInServiceImpl implements CategoryInService {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @Override
    public Result<List<CategoryRespDTO>> getCategoryList() {
        Result<List<CategoryRespDTO>> result = new Result<>();
        result.setModule(new ArrayList<>());
        result.setSuccess(false);

        List<CategoryDO> categoryDOS = categoryDOMapper.getCategoryList("");
        if (CollectionUtils.isNotEmpty(categoryDOS)) {
            List<CategoryRespDTO> respDTOList = CategoryMapStruct.INSTANCE.reqToDo(categoryDOS);
            respDTOList.stream().forEach(x -> {
                List<CategoryRespDTO> respDTOListSon =
                        CategoryMapStruct.INSTANCE.reqToDo(Optional.ofNullable(categoryDOMapper.getCategoryList(x.getId()))
                                .orElse(new ArrayList<>()));
                x.setChildren(respDTOListSon);
            });
            result.setModule(respDTOList);
            result.setSuccess(true);
        }
        return result;
    }
}
