package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;


@Data
public class LotteryDO extends BaseDO  {

    private String eventDate;

    private String normalNum;

    private String specialNum;

    private Integer type;

}