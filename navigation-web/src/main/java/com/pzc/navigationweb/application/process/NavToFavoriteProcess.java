package com.pzc.navigationweb.application.process;

import com.pzc.navigationweb.application.Context;
import com.pzc.navigationweb.application.DefaultProcess;
import com.pzc.navigationweb.dto.reqdto.NavToFavoriteReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.FavoriteInService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 7/29/21 11:28 AM
 */
public class NavToFavoriteProcess extends DefaultProcess<NavToFavoriteReqDTO, NavigationResourcesRespDTO> {

    @Autowired
    private FavoriteInService favoriteInService;

    @Override
    public void process(Context<NavToFavoriteReqDTO, NavigationResourcesRespDTO> context) {
        NavToFavoriteReqDTO reqDTO = context.getParam();
        context.setResultModule(favoriteInService.toFavorite(reqDTO));
    }
}
