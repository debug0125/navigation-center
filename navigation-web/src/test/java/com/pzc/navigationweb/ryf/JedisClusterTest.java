package com.pzc.navigationweb.ryf;

import com.pzc.myredis.MyJedisCluster;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryf
 * @date 3/25/22 1:02 PM
 */
public class JedisClusterTest {
    public static void main(String[] args) {



        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String[] ipAndPortArr = "121.4.127.22:7000,121.4.127.22:7001,121.4.127.22:7002,121.4.127.22:7003,121.4.127.22:7004,121.4.127.22:7005".split(",");
        for (String ipAndPort : ipAndPortArr) {
            String[] ipPort = ipAndPort.split(":");
            String ip = ipPort[0];
            String port = ipPort[1];

            jedisClusterNodes.add(new HostAndPort(ip, Integer.valueOf(port)));
        }


        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(10);
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(30);
        poolConfig.setMaxWaitMillis(2000);




//        MyJedisCluster myJedisCluster =new MyJedisCluster(jedisClusterNodes,1000,30,3,"root",
//                poolConfig);
//
//        myJedisCluster.setV("rrr","999");

        //1.如果集群没有密码
        JedisCluster jc = new JedisCluster(jedisClusterNodes,poolConfig);
        //2.如果使用到密码，请使用如下构造函数
//        JedisCluster jc = new JedisCluster(jedisClusterNodes, 1000,30,3,"root",poolConfig);

        jc.set("ryf", "123");
        System.out.println("==================");
        System.out.println(jc.get("123"));
        jc.close();
    }
}
