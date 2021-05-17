package com.pzc.navigationweb.domain.dbdo;

import lombok.Data;

/**
 * @author ryf
 * @date 5/17/21 3:19 PM
 */
@Data
public class UserDO extends BaseDO {

    private String account;

    private String name;

    private String password;

    private String token;

    private String salt;

    /**
     * 头像
     */
    private String avatarUrl;
}