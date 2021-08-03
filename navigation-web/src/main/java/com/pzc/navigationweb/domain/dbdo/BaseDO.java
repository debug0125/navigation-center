package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;

import java.util.Date;

/**
 * @author ryf
 * @date 5/11/21 2:47 PM
 */
@Data
public class BaseDO implements BaseDOInt {
    private String id;
    private Date createDate;
    private String createId;
    private String createAccount;
    private String createName;
    private Date modifyDate;
    private String modifyId;
    private String modifyAccount;
    private String modifyName;
    private Integer version;
    private Boolean isDel;
}
