package com.pzc.navigationweb.common.util;

import com.pzc.navigationweb.domain.dbdo.UserDO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ryf
 * @date 5/17/21 3:51 PM
 */
public class CookieUtil {

    private static final int cookieMaxAge = 43200;

    public CookieUtil() {
    }

    public static void saveCookie(UserDO userDO, HttpServletResponse response, String tokenKey) {
        Cookie tokenCookie = new Cookie(tokenKey, userDO.getToken());
        Cookie accountCookie = new Cookie("account", userDO.getAccount());
        tokenCookie.setMaxAge(43200);
        tokenCookie.setPath("/");
        accountCookie.setMaxAge(43200);
        accountCookie.setPath("/");
        response.addCookie(tokenCookie);
        response.addCookie(accountCookie);
    }

    public static String getCookie(HttpServletRequest request, String cookieDomainName) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            Cookie[] var4 = cookies;
            int var5 = cookies.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Cookie cookie = var4[var6];
                if (cookieDomainName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        return cookieValue;
    }
}
