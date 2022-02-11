package com.pzc.navigationweb.domain.mapstruct;

import com.pzc.navigationweb.domain.dbdo.CountThemeDO;
import com.pzc.navigationweb.dto.respdto.CountThemeRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author ryf
 * @date 2/11/22 5:44 PM
 */
@Mapper(uses = DateMapper.class)
public interface CountThemeMapStruct {

    CountThemeMapStruct INSTANCE =
            Mappers.getMapper(CountThemeMapStruct.class);

    List<CountThemeRespDTO> doListToRespList(List<CountThemeDO> doList);

    @Mappings({
            @Mapping(source = "createDate", target = "createDateStr", qualifiedByName = "asStringTime"),
            @Mapping(source = "themeDate", target = "themeDateStr", qualifiedByName = "asString")
    })
    CountThemeRespDTO doToResp(CountThemeDO countThemeDO);
}
