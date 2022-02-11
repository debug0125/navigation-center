package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

@Data
public class ThemeOrganizerRespDTO extends ReqDTO {

    private String organizerName;

    private String themeId;

}