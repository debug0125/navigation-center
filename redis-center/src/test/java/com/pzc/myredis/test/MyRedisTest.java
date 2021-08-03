package com.pzc.myredis.test;

import com.pzc.myredis.MyJedis;
import com.pzc.myredis.MyJedisConfig;

import java.util.ArrayList;
import java.util.List;

public class MyRedisTest {

    private static String redisIps="127.0.0.1:7000,127.0.0.1:7001,127.0.0.1:7002";
    private static String password="";

    public static void main(String[] args) throws Throwable {

//        List<Person> list =new ArrayList<>();
//
//        for(int i=0;i<1000;i++){
//
//            Person p = new Person();
//
//            p.setAge(1);
//            p.setName("暗黑");
//            p.setDesc("后悔哦啊");
//
//            list.add(p);
//        }


        MyJedisConfig jedisConfig =new   MyJedisConfig();
        jedisConfig.setRedisIps(redisIps);
        jedisConfig.setPassWord(password);


        MyJedis myJedis = new MyJedis();
        myJedis.setMyJedisConfig(jedisConfig);
        myJedis.init();

//        myJedis.op().delV("name");
//        myJedis.op().setVex("name",30,"ryf");
        System.out.println((String) myJedis.op().getV("name"));



//        myJedis.op().setV("list",list);
//
//
//        List<Person> list1=  myJedis.op().getV("list");



//        myJedis.op().setVex("1111",10,"2222");
//
//        Thread.sleep(5000L);
//        System.out.println((String)myJedis.op().getV("1111"));
//        Thread.sleep(5000L);
//        System.out.println((String)myJedis.op().getV("1111"));
//        Thread.sleep(5000L);
//        System.out.println((String)myJedis.op().getV("1111"));

    }

}
