package com.pzc.myredis;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

public class MyJedis {

    private MyJedisCluster myJedisCluster;

    private MyJedisConfig myJedisConfig;

    public void init(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

        String[] ipAndPortArr = myJedisConfig.getRedisIps().split(",");
        for (String ipAndPort : ipAndPortArr) {
            String[] ipPort = ipAndPort.split(":");
            String ip = ipPort[0];
            String port = ipPort[1];

            jedisClusterNodes.add(new HostAndPort(ip, Integer.valueOf(port)));
        }


        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(myJedisConfig.getMinIdle());
        poolConfig.setMaxIdle(myJedisConfig.getMaxIdle());
        poolConfig.setMaxTotal(myJedisConfig.getMaxTotal());
        poolConfig.setMaxWaitMillis(myJedisConfig.getMaxWaitMillis());



        if(StrUtil.isBlank(myJedisConfig.getPassWord())){
            myJedisCluster=new MyJedisCluster(jedisClusterNodes,
                    myJedisConfig.getConnectTimeOut(),
                    myJedisConfig.getSoTimeOut(), myJedisConfig.getMaxAttempts(),
                    poolConfig);
        }else{
            myJedisCluster =new MyJedisCluster(jedisClusterNodes,
                    myJedisConfig.getConnectTimeOut(),
                    myJedisConfig.getSoTimeOut(), myJedisConfig.getMaxAttempts(),
                    myJedisConfig.getPassWord(),
                    poolConfig);
        }




    }

    public MyJedisCluster op(){
        if(myJedisCluster==null){
            init();
        }

        return myJedisCluster;
    }


    public void setMyJedisCluster(MyJedisCluster myJedisCluster) {
        this.myJedisCluster = myJedisCluster;
    }

    public MyJedisConfig getMyJedisConfig() {
        return myJedisConfig;
    }

    public void setMyJedisConfig(MyJedisConfig myJedisConfig) {
        this.myJedisConfig = myJedisConfig;
    }
}
