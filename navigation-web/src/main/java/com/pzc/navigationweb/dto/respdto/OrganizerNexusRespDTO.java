package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;


@Data
public class OrganizerNexusRespDTO extends ReqDTO {

    private String nexusName;

    private String organizerId;

}