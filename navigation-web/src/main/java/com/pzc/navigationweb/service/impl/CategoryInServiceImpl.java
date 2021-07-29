package com.pzc.navigationweb.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.pzc.navigationweb.common.util.InitDOUtil;
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
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class CategoryInServiceImpl implements CategoryInService {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @Override
    public List<CategoryRespDTO> getCategoryList() {
        List<CategoryDO> categoryDOS = categoryDOMapper.getCategoryList("");
        List<CategoryRespDTO> respDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(categoryDOS)) {
            respDTOList = CategoryMapStruct.INSTANCE.doListToRespList(categoryDOS);
            respDTOList.stream().forEach(x -> {
                List<CategoryRespDTO> respDTOListSon =
                        CategoryMapStruct.INSTANCE.doListToRespList(Optional.ofNullable(categoryDOMapper.getCategoryList(x.getId()))
                                .orElse(new ArrayList<>()));
                x.setChildren(respDTOListSon);
            });
        }
        return respDTOList;
    }

    @Override
    public CategoryRespDTO addCategory(CategoryReqDTO categoryReqDTO) {
        CategoryDO categoryDO = CategoryMapStruct.INSTANCE.reqToDo(categoryReqDTO);
        InitDOUtil.initField(categoryDO);
        categoryDOMapper.insertSelective(categoryDO);
        return CategoryMapStruct.INSTANCE.doListToRespList(Arrays.asList(categoryDO)).get(0);
    }

    @Override
    public Boolean removeCategory(CategoryReqDTO categoryReqDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        CategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(categoryReqDTO.getId());
        categoryDO.setIsDel(true);
        result.set(categoryDOMapper.updateByPrimaryKey(categoryDO) > 0);
        // 查询子类
        List<CategoryDO> doList = categoryDOMapper.getCategoryList(categoryReqDTO.getId());
        if (CollectionUtils.isNotEmpty(doList)) {
            doList.stream().forEach(x -> {
                x.setIsDel(true);
                result.set(categoryDOMapper.updateByPrimaryKey(x) > 0);
            });
        }
        return result.get();
    }
}
