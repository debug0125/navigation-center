package com.pzc.navigationweb.dto.reqdto;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 5/11/21 4:58 PM
 */
@Data
public class LoginUser extends ReqDTO {

    private String token;

    private String name;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 第二次密码
     */
    private String passwordTwo;


    public void validation() {
        if (StringUtils.isBlank(account)) {
            throw new IllegalArgumentException("账号不能为空！");
        }

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("密码不能为空！");
        }

    }
}
