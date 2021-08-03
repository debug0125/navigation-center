package com.pzc.myredis;

public class MyJedisConfig {


    //所有redis服务器的ip地址，以逗号隔开
    private String redisIps;

    //密码
    private String passWord;

    //连接池中最少空闲的连接数
    private int minIdle = 0;

    //链接池中最大空闲的连接数
    private int maxIdle = 8;

    //链接池中最大连接数
    private int maxTotal = 8;

    //当连接池资源耗尽时，等待时间，超出则抛异常，默认为-1即永不超时
    //blockWhenExhausted
    //当这个值为true的时候，maxWaitMillis参数才能生效。为false的时候，当连接池没资源，则立马抛异常。默认为true
    private long maxWaitMillis =3000L;


    private int connectTimeOut=3000;

    private int soTimeOut=3000;

    private int maxAttempts =100;

    public String getRedisIps() {
        return redisIps;
    }

    public void setRedisIps(String redisIps) {
        this.redisIps = redisIps;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public int getSoTimeOut() {
        return soTimeOut;
    }

    public void setSoTimeOut(int soTimeOut) {
        this.soTimeOut = soTimeOut;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
