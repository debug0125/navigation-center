package com.pzc.navigationweb.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import com.pzc.navigationweb.aspect.annotation.Login;
import com.pzc.navigationweb.common.util.*;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.dto.respdto.UserInfoRespDTO;
import com.pzc.navigationweb.service.LoginService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
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

    @Around(value = "Pointcut(function)", argNames = "joinPoint,function")
    public Object deBefore(ProceedingJoinPoint joinPoint, Login function) throws Throwable {
        LoginUser loginUser = (LoginUser) joinPoint.getArgs()[0];

        Result<UserDO> result = loginService.loginUser(loginUser);
        if (result.isSuccess()) {
            result.setSuccess(false);
            HttpServletResponse httpResponse = (HttpServletResponse) joinPoint.getArgs()[1];
            String value = function.value();
            // 生成token
            String token = TokenProccessor.getInstance().makeToken();
            UserDO userDO = result.getModule();
            userDO.setToken(token);
//            UserSessionUtil.putCurrebtUser(userDO);
            // 用户信息存入redis
            RedisUtil.op().setVex(token, 60 * 60 * 4,userDO);
            // 存入cookie
            CookieUtil.saveCookie(userDO, httpResponse, value);
            result.setSuccess(true);
        }
        joinPoint.proceed();
        return result;
    }
}
