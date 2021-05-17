package com.pzc.navigationweb.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import com.pzc.navigationweb.aspect.annotation.Login;
import com.pzc.navigationweb.common.util.CookieUtil;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.common.util.TokenProccessor;
import com.pzc.navigationweb.common.util.UserSessionUtil;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.dto.respdto.UserInfoRespDTO;
import com.pzc.navigationweb.service.LoginService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ryf
 * @date 5/17/21 1:53 PM
 */
@Aspect
@Component
public class UserLoginAspect {

    @Autowired
    private LoginService loginService;


    @Pointcut("@annotation(function)")
    public void Pointcut(Login function) {
    }

    @Before(value = "Pointcut(function)", argNames = "joinPoint,function")
    public void deBefore(JoinPoint joinPoint, Login function) {
        System.out.println("进入切面");
        LoginUser loginUser = (LoginUser) joinPoint.getArgs()[0];

        Result<UserDO> result = loginService.loginUser(loginUser);
        if (result.isSuccess()) {
            HttpServletResponse httpResponse = (HttpServletResponse) joinPoint.getArgs()[1];
            String value = function.value();
            // 生成token
            String token = TokenProccessor.getInstance().makeToken();
            UserDO userDO = result.getModule();
            userDO.setToken(token);
            UserSessionUtil.putCurrebtUser(userDO);
            // 存入cookie
            CookieUtil.saveCookie(userDO, httpResponse, value);
        }
    }
}
