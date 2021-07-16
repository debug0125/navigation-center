package com.pzc.navigationweb.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dao.FavoritesDOMapper;
import com.pzc.navigationweb.dao.NavigationResourcesDOMapper;
import com.pzc.navigationweb.domain.dbdo.FavoritesDO;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.domain.mapstruct.NavigationReqToDo;
import com.pzc.navigationweb.dto.query.FavoriteQuery;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.FavoriteInService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class FavoriteInServiceImpl implements FavoriteInService {

    @Autowired
    private NavigationResourcesDOMapper navigationResourcesDOMapper;
    @Autowired
    private FavoritesDOMapper favoritesDOMapper;

    @Override
    public Result<NavigationResourcesRespDTO> toFavorite(String userId, String navId, Boolean isLiked) {
        Result<NavigationResourcesRespDTO> result = new Result<>();
        NavigationResourcesDO resourcesDO = navigationResourcesDOMapper.selectByPrimaryKey(navId);


        FavoritesDO favoritesDO = new FavoritesDO();
        if (isLiked) {
            // 已收藏 --> 取消收藏
            favoritesDO = favoritesDOMapper.selectByUserNavId(userId,navId);
            favoritesDOMapper.removeByPrimaryKey(favoritesDO.getId());
            resourcesDO.setLikeCount(resourcesDO.getLikeCount()-1);
        } else {
            InitDOUtil.initField(favoritesDO);
            favoritesDO.setUserId(userId);
            favoritesDO.setNavId(navId);
            favoritesDOMapper.insert(favoritesDO);
            resourcesDO.setLikeCount(resourcesDO.getLikeCount()+1);
        }
        navigationResourcesDOMapper.updateByPrimaryKey(resourcesDO);

        NavigationResourcesRespDTO respDTO = NavigationReqToDo.INSTANCE.doToResp(resourcesDO);
        respDTO.setIsLiked(!isLiked);
        result.setSuccess(true);
        result.setModule(respDTO);
        return result;
    }

    @Override
    public Result<Page<NavigationResourcesRespDTO>> pageNavigation(FavoriteQuery query) {
        Result<Page<NavigationResourcesRespDTO>> result = new Result<>();

        List<FavoritesDO> favoritesDOList = new ArrayList<>();
        Integer count = favoritesDOMapper.countByUserAndCategory(query);
        if (count > 0) {
            favoritesDOList = favoritesDOMapper.listByUserAndCategory(query);
        }

        Page<NavigationResourcesRespDTO> page = new Page<>(query.getPageNo(), query.getPageSize(), count);
        List<NavigationResourcesRespDTO> navList = new ArrayList<>();
        favoritesDOList.stream().forEach(x -> {
            navList.add(NavigationReqToDo.INSTANCE.doToResp(navigationResourcesDOMapper.selectByPrimaryKey(x.getNavId())));
        });
        page.setResult(navList);
        result.setModule(page);
        return result;
    }
}
