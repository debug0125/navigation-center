package com.pzc.navigationweb.service;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;

/**
 * @author ryf
 * @date 5/11/21 3:12 PM
 */
public interface FavoriteInService {

    /**
     * 收藏/取消收藏
     * @param userId
     * @param navId
     * @return
     */
    Result<NavigationResourcesRespDTO> toFavorite(String userId, String navId, Boolean isLiked);

    /**
     * 查询资源
     * @param query
     * @return
     */
    Result<Page<NavigationResourcesRespDTO>> pageNavigation(FavoriteQuery query);
}
