package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.aspect.annotation.Login;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.constant.LoginTokenConstant;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
public class LoginController extends BaseController {

    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @Login(LoginTokenConstant.TOKEN_KEY)
    @RequestMapping("/login")
    public Result<UserDO> login(LoginUser loginUser, HttpServletResponse httpResponse) {
        return new Result<>();
    }
}
