package com.pzc.navigationweb.service;

import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import org.springframework.stereotype.Service;

/**
 * @author ryf
 * @date 5/11/21 3:12 PM
 */
public interface NavigationResourcesService {

    /**
     * 提交请求
     * @param navigationResourcesReqDTO
     * @return
     */
    Boolean submit(NavigationResourcesReqDTO navigationResourcesReqDTO);
}
