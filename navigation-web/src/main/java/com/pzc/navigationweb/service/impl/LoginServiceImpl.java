package com.pzc.navigationweb.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.pzc.navigationweb.common.util.CookieUtil;
import com.pzc.navigationweb.common.util.PasswordUtil;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dao.UserDOMapper;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.dto.respdto.UserInfoRespDTO;
import com.pzc.navigationweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ryf
 * @date 5/11/21 3:14 PM
 */
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public Result<UserDO> loginUser(LoginUser loginUser) {
        Result<UserDO> respDTOResult = new Result<>();
        String account = loginUser.getAccount();
        UserDO userDO = userDOMapper.getByAccount(account);

        // 查询用户名是否正确
        if (userDO == null) {
            respDTOResult.setSuccess(false);
            respDTOResult.setErrMsg("用户名不存在");
            return respDTOResult;
        }

        String password = loginUser.getPassword();
        //验证密码
        String saltPwd = PasswordUtil.decrypt(password,userDO.getSalt().getBytes());
        if (!userDO.getPassword().equals(saltPwd)) {
            respDTOResult.setSuccess(false);
            respDTOResult.setErrMsg("密码错误，请重新输入");
            return respDTOResult;
        }

        respDTOResult.setSuccess(true);
        respDTOResult.setModule(userDO);
        return respDTOResult;
    }
}
