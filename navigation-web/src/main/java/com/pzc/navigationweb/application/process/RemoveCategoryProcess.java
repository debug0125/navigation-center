package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.service.CategoryInService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class RemoveCategoryProcess extends DefaultProcess<CategoryReqDTO, Boolean> {


    @Autowired
    private CategoryInService categoryInService;

    @Override
    public void process(Context<CategoryReqDTO, Boolean> context) {
        CategoryReqDTO reqDTO = context.getParam();
        context.setResultModule(categoryInService.removeCategory(reqDTO));
    }
}
