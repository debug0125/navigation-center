package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.application.process.NavToFavoriteProcess;
import com.pzc.navigationweb.application.process.PageFavoriteProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.common.util.UserSessionUtil;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import com.pzc.navigationweb.dto.reqdto.NavToFavoriteReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
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
    private PageFavoriteProcess pageFavoriteProcess;

    @Autowired
    private NavToFavoriteProcess navToFavoriteProcess;

    @RequestMapping("/pageFavorite")
    public Result<Page<NavigationResourcesRespDTO>> pageFavorite(FavoriteQuery query) {
        UserDO userDO = UserSessionUtil.getCurreentUserByKey();
        query.setUserId(userDO.getId());
        return pageFavoriteProcess.start(query);
    }

    @RequestMapping("/toFavorite")
    public Result<NavigationResourcesRespDTO> toFavorite(NavToFavoriteReqDTO reqDTO) {
        UserDO userDO = UserSessionUtil.getCurreentUserByKey();
        reqDTO.setUserId(userDO.getId());
        return navToFavoriteProcess.start(reqDTO);
    }
}
