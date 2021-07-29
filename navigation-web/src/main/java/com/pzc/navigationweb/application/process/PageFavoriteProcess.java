package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.FavoriteInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class PageFavoriteProcess extends DefaultProcess<FavoriteQuery, Page<NavigationResourcesRespDTO>> {

    @Autowired
    private FavoriteInService favoriteInService;

    @Override
    public void process(Context<FavoriteQuery, Page<NavigationResourcesRespDTO>> context) {
        FavoriteQuery query = context.getParam();
        context.setResultModule(favoriteInService.pageNavigation(query));
    }
}
