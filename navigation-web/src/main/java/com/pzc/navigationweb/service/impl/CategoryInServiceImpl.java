package com.pzc.navigationweb.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dao.CategoryDOMapper;
import com.pzc.navigationweb.domain.dbdo.CategoryDO;
import com.pzc.navigationweb.domain.mapstruct.CategoryMapStruct;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.service.CategoryInService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
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
            List<CategoryRespDTO> respDTOList = CategoryMapStruct.INSTANCE.doListToRespList(categoryDOS);
            respDTOList.stream().forEach(x -> {
                List<CategoryRespDTO> respDTOListSon =
                        CategoryMapStruct.INSTANCE.doListToRespList(Optional.ofNullable(categoryDOMapper.getCategoryList(x.getId()))
                                .orElse(new ArrayList<>()));
                x.setChildren(respDTOListSon);
            });
            result.setModule(respDTOList);
            result.setSuccess(true);
        }
        return result;
    }

    @Override
    public Result<CategoryRespDTO> addCategory(CategoryReqDTO categoryReqDTO) {
        Result<CategoryRespDTO> result = new Result<>();
        CategoryDO categoryDO = CategoryMapStruct.INSTANCE.reqToDo(categoryReqDTO);
        InitDOUtil.initField(categoryDO);
        result.setSuccess(categoryDOMapper.insertSelective(categoryDO)>0);
        result.setModule(CategoryMapStruct.INSTANCE.doListToRespList(Arrays.asList(categoryDO)).get(0));
        return result;
    }

    @Override
    public Result<Boolean> removeCategory(CategoryReqDTO categoryReqDTO) {
        Result<Boolean> result = new Result<>();
        if (StringUtils.isBlank(categoryReqDTO.getId())) {
            result.setSuccess(false);
            result.setModule(false);
            result.setErrMsg("删除类目时，主id不能为空！");
            return result;
        }
        CategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(categoryReqDTO.getId());
        categoryDO.setIsDel(true);
        result.setSuccess(categoryDOMapper.updateByPrimaryKey(categoryDO)>0);
        // 查询子类
        List<CategoryDO> doList = categoryDOMapper.getCategoryList(categoryReqDTO.getId());
        if (CollectionUtils.isNotEmpty(doList)) {
            doList.stream().forEach(x -> {
                x.setIsDel(true);
                categoryDOMapper.updateByPrimaryKey(x);
            });
        }
        result.setModule(result.isSuccess());
        return result;
    }
}
