package com.pzc.navigationweb.service;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;

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
    Result<Boolean> submit(NavigationResourcesReqDTO navigationResourcesReqDTO);

    /**
     * 查询资源
     * @param query
     * @return
     */
    Result<PageInfo<NavigationResourcesRespDTO>> pageNavigation(NavigationQuery query);

    /**
     * 增加打开次数
     * @param id
     * @return
     */
    Result<NavigationResourcesRespDTO> addOpenCount(String id);

    /**
     * 收藏/取消收藏
     * @param userId
     * @param navId
     * @return
     */
    Result<NavigationResourcesRespDTO> toFavorite(String userId, String navId, Boolean isLiked);
}
