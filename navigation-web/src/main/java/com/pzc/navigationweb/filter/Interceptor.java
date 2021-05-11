package com.pzc.navigationweb.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ryf
 * @date 5/8/21 6:11 PM
 */
public class Interceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("==========jin  ru pre  handle ====");
        HttpSession session = request.getSession();
        if (session.getAttribute("logininfo") == null) {
            response.setHeader("logininfo","unauth");
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("================= jin ru  after ===============");
    }
}
