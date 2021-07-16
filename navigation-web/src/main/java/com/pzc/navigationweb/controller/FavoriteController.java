package com.pzc.navigationweb.controller;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.common.util.UserSessionUtil;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.FavoriteInService;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryf
 * @date 7/16/21 3:13 PM
 */
@RestController
@RequestMapping(FavoriteController.PAGE_NAME)
public class FavoriteController extends BaseController {
    public static final String PAGE_NAME = "/favorite";

    @Autowired
    private FavoriteInService favoriteInService;

    @RequestMapping("/pageFavorite")
    public Result<Page<NavigationResourcesRespDTO>> pageNavigation(FavoriteQuery query) {
        UserDO userDO = UserSessionUtil.getCurreentUserByKey();
        query.setUserId(userDO.getId());
        return favoriteInService.pageNavigation(query);
    }

    @RequestMapping("/toFavorite")
    public Result<NavigationResourcesRespDTO> toFavorite(String navId, Boolean isLiked) {
        UserDO userDO = UserSessionUtil.getCurreentUserByKey();
        Result<NavigationResourcesRespDTO> result =
                favoriteInService.toFavorite(userDO.getId(),navId,isLiked);
        return result;
    }
}
