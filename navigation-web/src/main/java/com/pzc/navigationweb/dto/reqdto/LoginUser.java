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

    private String account;
    private String name;
}
