package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrganizerFriendsDO extends BaseDO {

    private String friendName;

    private String nexusId;

    private String nexusName;

    private Integer peopleNumber;

    private BigDecimal receiveAmount;

    private BigDecimal sendAmount;

    private String organizerId;

    private String organizerName;

}