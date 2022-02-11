package com.pzc.navigationweb.common.util;

import com.pzc.navigationweb.domain.dbdo.UserDO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author ryf
 * @date 5/17/21 3:34 PM
 */
public class UserSessionUtil {

    private static final String USER_INFO = "userInfo";

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
    public static void putCurrentUser(UserDO userDO) {
        HttpSession session = getSession();
        session.setAttribute(userDO.getToken(), userDO);
//        RequestContext.get().put("userInfo", userInfoDTO);
    }

    public static void putCurrentUserBykey(UserDO userDO) {
        HttpSession session = getSession();
        session.setAttribute(UserSessionUtil.USER_INFO, userDO);
//        RequestContext.get().put("userInfo", userInfoDTO);
    }

    /**
     * 获取当前用户
     */
    public static UserDO getCurreentUser(String token) {
        HttpSession session = getSession();
        if (session != null) {
            return (UserDO) session.getAttribute(token);
        }
        return new UserDO();
    }

    public static UserDO getCurrentUserByKey() {
        HttpSession session = getSession();
        if (session != null) {
            return (UserDO) session.getAttribute(UserSessionUtil.USER_INFO);
        }
        return new UserDO();
    }

    public static void removeCurreentUser(String token) {
        HttpSession session = getSession();
        session.removeAttribute(token);
    }
}
