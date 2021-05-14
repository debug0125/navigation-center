package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;

/**
 * @author ryf
 * @date 5/11/21 3:18 PM
 */
@Data
public class NavigationResourcesDO extends BaseDO {

    private String name;

    private String description;

    private String navigationUrl;

    private String logoUrl;

    private String detail;

    private String type;

    private Integer openCount;
}
