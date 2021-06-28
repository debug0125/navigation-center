package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;

import java.util.Date;

@Data
public class NavCategoryDO extends BaseDO  {
    private String categoryId;
    private String navId;
}