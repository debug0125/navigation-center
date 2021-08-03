package com.pzc.navigationweb.common.util;

import com.pzc.myredis.MyJedis;
import com.pzc.myredis.MyJedisCluster;


/**
 * @author ryf
 * @date 8/2/21 2:01 PM
 */
public class RedisUtil {

    private static MyJedis myJedis;

    public void setMyJedis(MyJedis myJedis) {
        RedisUtil.myJedis = myJedis;
    }

    public static MyJedisCluster op() {
        return myJedis.op();
    }
}
