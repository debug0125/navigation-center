package com.pzc.navigationweb.application.process;

import cn.hutool.core.collection.CollectionUtil;
import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.service.CategoryInService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class QueryCategoryListProcess extends DefaultProcess<CategoryReqDTO, List<CategoryRespDTO>> {


    @Autowired
    private CategoryInService categoryInService;

    @Override
    public void process(Context<CategoryReqDTO, List<CategoryRespDTO>> context) {
        List<CategoryRespDTO> categoryRespDTOS = RedisUtil.op().getV(RedisKeyConstant.CATEGORY_REDIS_KEY);
        if (CollectionUtil.isNotEmpty(categoryRespDTOS)) {
            context.setResultModule(categoryRespDTOS);
        } else {
            context.setResultModule(categoryInService.getCategoryList());
        }
    }
}
