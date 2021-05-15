package com.pzc.navigationweb.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.InitDOUtil;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.constant.ImgConstants;
import com.pzc.navigationweb.dao.NavigationResourcesDOMapper;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.domain.mapstruct.NavigationReqToDo;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class NavigationResourcesServiceImpl implements NavigationResourcesService {

    @Autowired
    private NavigationResourcesDOMapper navigationResourcesDOMapper;

    @Override
    public Result<Boolean> submit(NavigationResourcesReqDTO navigationResourcesReqDTO) {
        Result<Boolean> result = new Result<>();
        // 设置默认值
        if (StringUtils.isNotEmpty(navigationResourcesReqDTO.getLogoUrl())) {
            navigationResourcesReqDTO.setLogoUrl(ImgConstants.DEFAULT_PICTURE);
        }
        NavigationResourcesDO navigationResourcesDO = NavigationReqToDo.INSTANCE.reqToDo(navigationResourcesReqDTO);
        InitDOUtil.initField(navigationResourcesDO);
        result.setModule(navigationResourcesDOMapper.insertSelective(navigationResourcesDO) > 0);

        if (result.getModule()) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setErrMsg("推荐失败");
        }
        return result;
    }

    @Override
    public Result<PageInfo<NavigationResourcesRespDTO>> pageNavigation(NavigationQuery query) {
        Result<PageInfo<NavigationResourcesRespDTO>> result = new Result<>();
        Page<NavigationResourcesRespDTO> page = PageHelper.startPage(query.getPageNo(), query.getPageSize(), true);
        navigationResourcesDOMapper.listNavigation(query);
        PageInfo<NavigationResourcesRespDTO> pageInfo = new PageInfo<>(page);
        pageInfo.getList().stream().forEach(x -> {
            x.setCreateDateStr(DateUtil.formatDateTime(x.getCreateDate()));
        });
        result.setModule(pageInfo);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<NavigationResourcesRespDTO> addOpenCount(String id) {
        Result<NavigationResourcesRespDTO> result = new Result<>();
        NavigationResourcesDO resourcesDO = navigationResourcesDOMapper.selectByPrimaryKey(id);
        resourcesDO.setOpenCount(resourcesDO.getOpenCount()+1);
        result.setSuccess(navigationResourcesDOMapper.updateByPrimaryKey(resourcesDO) > 0);

        NavigationResourcesRespDTO respDTO = NavigationReqToDo.INSTANCE.doToResp(resourcesDO);
        respDTO.setCreateDateStr(DateUtil.formatDateTime(respDTO.getCreateDate()));
        result.setModule(respDTO);
        return result;
    }
}
