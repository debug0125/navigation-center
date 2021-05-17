package com.pzc.navigationweb.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.pzc.navigationweb.common.util.*;
import com.pzc.navigationweb.constant.ImgConstants;
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
        String saltPwd = PasswordUtil.encrypt(password,userDO.getSalt().getBytes());
        if (!userDO.getPassword().equals(saltPwd)) {
            respDTOResult.setSuccess(false);
            respDTOResult.setErrMsg("密码错误，请重新输入");
            return respDTOResult;
        }

        respDTOResult.setSuccess(true);
        respDTOResult.setModule(userDO);
        return respDTOResult;
    }

    @Override
    public Result<UserDO> register(LoginUser loginUser) {
        Result<UserDO> result = new Result<>();
        if (StringUtils.isNotEmpty(loginUser.getAccount()) && StringUtils.isNotEmpty(loginUser.getPassword())) {
            if (loginUser.getPassword().equals(loginUser.getPasswordTwo())) {
                String salt = ObjectId.get().toHexString().substring(0,8);
                String saltPwd = PasswordUtil.encrypt(loginUser.getPassword(), salt.getBytes());
                UserDO userDO = new UserDO();
                InitDOUtil.initField(userDO);
                userDO.setAccount(loginUser.getAccount());
                userDO.setPassword(saltPwd);
                userDO.setName(StringUtils.isNotEmpty(loginUser.getName())?loginUser.getName():"nav_"+ObjectId.get().toHexString().substring(10));
                userDO.setAvatarUrl(StringUtils.isNotEmpty(loginUser.getAvatarUrl())?loginUser.getAvatarUrl(): ImgConstants.DEFAULT_PICTURE);
                userDO.setSalt(salt);
                userDOMapper.insert(userDO);
                result.setSuccess(true);
                result.setErrMsg("注册成功！");
                return result;
            } else {
                result.setSuccess(false);
                result.setErrMsg("两次密码输入请保持一致");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setErrMsg("账号和密码都不能为空");
            return result;
        }
    }
}
