package com.pzc.navigationweb.dto.reqdto;

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
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
