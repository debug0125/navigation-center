package com.pzc.navigationweb.common.util;

import com.pzc.navigationweb.dto.reqdto.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author ryf
 * @date 5/11/21 4:56 PM
 */
public class UserSession {

    private static final String CURRENT_USER_IN_SESSION = "logininfo";

    /**
     * 得到session
     */
    private static HttpSession getSession() {
        //SpringMVC获取session的方式通过RequestContextHolder
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        session.setMaxInactiveInterval(60 * 60 * 5);
        return session;
    }

    /**
     * 设置当前用户到session中
     */
    public static void putCurrebtUser(LoginUser currentUser) {
        currentUser.setToken(UUID.randomUUID().toString());
        HttpSession session = getSession();
        session.setAttribute(CURRENT_USER_IN_SESSION, currentUser);
    }

    /**
     * 获取当前用户
     */
    public static LoginUser getCurreentUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (LoginUser) session.getAttribute(CURRENT_USER_IN_SESSION);
        }
        return new LoginUser();
    }

    public static void removeCurreentUser() {
        HttpSession session = getSession();
        session.removeAttribute(CURRENT_USER_IN_SESSION);
    }
}
