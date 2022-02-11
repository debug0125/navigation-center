package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrganizerFriendsRespDTO extends ReqDTO {

    private String friendName;

    private String nexusId;

    private String nexusName;

    private Integer peopleNumber;

    private BigDecimal receiveAmount;

    private BigDecimal sendAmount;

    private String organizerId;

    private String organizerName;

}