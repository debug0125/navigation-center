package com.pzc.myredis;

import com.pzc.myredis.common.MyRedisErrorEnum;
import com.pzc.myredis.exception.MyRedisException;
import com.pzc.myredis.util.HessianSerializerUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class MyJedisCluster extends JedisCluster {

    public MyJedisCluster(HostAndPort node) {
        super(node);
    }

    public MyJedisCluster(HostAndPort node, int timeout) {
        super(node, timeout);
    }

    public MyJedisCluster(HostAndPort node, int timeout, int maxAttempts) {
        super(node, timeout, maxAttempts);
    }

    public MyJedisCluster(HostAndPort node, GenericObjectPoolConfig poolConfig) {
        super(node, poolConfig);
    }

    public MyJedisCluster(HostAndPort node, int timeout, GenericObjectPoolConfig poolConfig) {
        super(node, timeout, poolConfig);
    }

    public MyJedisCluster(HostAndPort node, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(node, timeout, maxAttempts, poolConfig);
    }

    public MyJedisCluster(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(node, connectionTimeout, soTimeout, maxAttempts, poolConfig);
    }

    public MyJedisCluster(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig poolConfig) {
        super(node, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }

    public MyJedisCluster(Set<HostAndPort> nodes) {
        super(nodes);
    }

    public MyJedisCluster(Set<HostAndPort> nodes, int timeout) {
        super(nodes, timeout);
    }

    public MyJedisCluster(Set<HostAndPort> nodes, int timeout, int maxAttempts) {
        super(nodes, timeout, maxAttempts);
    }

    public MyJedisCluster(Set<HostAndPort> nodes, GenericObjectPoolConfig poolConfig) {
        super(nodes, poolConfig);
    }

    public MyJedisCluster(Set<HostAndPort> nodes, int timeout, GenericObjectPoolConfig poolConfig) {
        super(nodes, timeout, poolConfig);
    }

    public MyJedisCluster(Set<HostAndPort> jedisClusterNode, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, timeout, maxAttempts, poolConfig);
    }

    public MyJedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, poolConfig);
    }


    public MyJedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig poolConfig) {

        super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }


    public <T> T getV(Object key) {
        if (key == null) {
            throw new MyRedisException(MyRedisErrorEnum.KEY_IS_NULL);
        }
        byte[] keyBytes = HessianSerializerUtil.serialize(key);


        byte[] valBytes = super.get(keyBytes);
        if (valBytes == null) {
            return null;
        }

        return HessianSerializerUtil.deserialize(valBytes);
    }

    public void setV(Object key, Object val) {

        if (key == null) {
            throw new MyRedisException(MyRedisErrorEnum.KEY_IS_NULL);
        }

        byte[] keyBytes = HessianSerializerUtil.serialize(key);
        byte[] valBytes = HessianSerializerUtil.serialize(val);

        super.set(keyBytes, valBytes);

    }

    public void delV(Object key) {

        if (key == null) {
            throw new MyRedisException(MyRedisErrorEnum.KEY_IS_NULL);
        }

        byte[] keyBytes = HessianSerializerUtil.serialize(key);

        super.del(keyBytes);

    }

    public void setVex(Object key,int timeout, Object val){

        if (key == null) {
            throw new MyRedisException(MyRedisErrorEnum.KEY_IS_NULL);
        }

        byte[] keyBytes = HessianSerializerUtil.serialize(key);
        byte[] valBytes = HessianSerializerUtil.serialize(val);

        super.setex(keyBytes,timeout,valBytes);
    }

    public Long incrV(Object key){
        byte[] keyBytes = HessianSerializerUtil.serialize(key);
        return super.incr(keyBytes);
    }

    public String getSpuCode(String prefix){
        String timePart = (new SimpleDateFormat("yyMMdd")).format(new Date());
        prefix = prefix + timePart;
        byte[] keyBytes = HessianSerializerUtil.serialize(prefix);
        Long incr = super.incr(keyBytes);
        // 一天过期时间
        super.expire(keyBytes, 86400);
        return String.format(prefix + "%04d", incr);
    }

}
