package com.pzc.navigationweb.domain.mapstruct;

import com.pzc.navigationweb.domain.dbdo.CategoryDO;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author ryf
 * @date 2020-11-24 13:55
 */
@Mapper
public interface CategoryMapStruct {

    CategoryMapStruct INSTANCE =
            Mappers.getMapper(CategoryMapStruct.class);


    List<CategoryRespDTO> reqToDo(List<CategoryDO> doList);
}
