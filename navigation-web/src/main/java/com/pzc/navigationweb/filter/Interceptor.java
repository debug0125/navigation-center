package com.pzc.navigationweb.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pzc.myredis.MyJedis;
import com.pzc.navigationweb.common.util.*;
import com.pzc.navigationweb.constant.LoginTokenConstant;
import com.pzc.navigationweb.constant.enumtype.UserErrorCodeEnum;
import com.pzc.navigationweb.domain.dbdo.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ryf
 * @date 5/8/21 6:11 PM
 */
public class Interceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = CookieUtil.getCookie(request, LoginTokenConstant.TOKEN_KEY);
//        UserDO userDO = UserSessionUtil.getCurreentUser(token);
        UserDO userDO = UserRedisUtil.getUser(token);
        if (userDO == null) {
            //若为空则返回登录界面
            failRequest(UserErrorCodeEnum.REQUEST_TOKEN.getErrCode(), UserErrorCodeEnum.REQUEST_TOKEN.getErrMsg(), response);
            return false;
        }

        //用户信息 存入上下文
        UserSessionUtil.putCurrebtUserBykey(userDO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("================= jin ru  after ===============");
    }


    private void failRequest(String errCode, String errMsg, HttpServletResponse response) {
        Result result = new Result();
        result.setErrCode(errCode);
        result.setErrMsg(errMsg);
        result.setSuccess(false);
        writeAjaxJSONResponse(result, response);
    }

    private void writeAjaxJSONResponse(Result result, HttpServletResponse response) {
        // HTTP 1.1
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        // Proxies.
        response.setDateHeader("Expires", 0);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
        } catch (Throwable e) {
            //StoreLoggerUtil.getLogger().error(e.getMessage(), e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
