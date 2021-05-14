package com.pzc.navigationweb.domain.mapstruct;

import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author ryf
 * @date 2020-11-24 13:55
 */
@Mapper
public interface NavigationReqToDo {

    NavigationReqToDo INSTANCE =
            Mappers.getMapper(NavigationReqToDo.class);


    NavigationResourcesDO reqToDo(NavigationResourcesReqDTO reqDTO);

    NavigationResourcesRespDTO doToResp(NavigationResourcesDO reqDTO);
}
