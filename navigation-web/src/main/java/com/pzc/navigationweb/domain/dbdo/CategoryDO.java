package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;

/**
 * @author ryf
 * @date 6/19/21 6:37 PM
 */

@Data
public class CategoryDO extends BaseDO {

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 上级ID
     */
    private String fatherId;

    
}