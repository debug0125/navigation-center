package com.pzc.navigationweb.common.util;

import com.pzc.navigationweb.domain.dbdo.UserDO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author ryf
 * @date 5/17/21 3:34 PM
 */
public class UserRedisUtil {

    public static UserDO getUser(String token){
        UserDO userDO = RedisUtil.op().getV(token);
        if (userDO != null) {
            return userDO;
        }
        return null;
    }
}
