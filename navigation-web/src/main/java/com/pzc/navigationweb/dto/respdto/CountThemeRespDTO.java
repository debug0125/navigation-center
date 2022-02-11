package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

@Data
public class CountThemeRespDTO extends ReqDTO {

    private String createDateStr;

    private String themeName;

    private String themeDateStr;


}