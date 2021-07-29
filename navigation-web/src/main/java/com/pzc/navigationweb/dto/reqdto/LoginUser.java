package com.pzc.navigationweb.dto.reqdto;

import cn.hutool.core.lang.Assert;
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


    @Override
    public void validation() {
        Assert.notBlank(getAccount(),"账号不能为空！");
        Assert.notBlank(getPassword(),"密码不能为空！");
    }
}
