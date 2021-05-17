package com.pzc.navigationweb.service;

import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.dto.respdto.UserInfoRespDTO;

/**
 * @author ryf
 * @date 5/11/21 3:12 PM
 */
public interface LoginService {

    /**
     * 登录
     * @param loginUser
     * @return
     */
    Result<UserDO> loginUser(LoginUser loginUser);
}
