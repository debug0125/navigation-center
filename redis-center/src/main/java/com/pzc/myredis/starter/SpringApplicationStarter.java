//package com.pzc.myredis.starter;
//
//import com.pzc.myredis.mybatis.SpringBootVFS;
//import org.apache.ibatis.io.VFS;
//import org.springframework.boot.SpringApplication;
//import org.springframework.core.io.ClassPathResource;
//
//import java.util.Properties;
//
//public class SpringApplicationStarter {
//
//
//    public static void run(Class clazz ,String[] args ) throws Throwable{
//        //支持springboot mybatis对jar的扫描
//        VFS.addImplClass(SpringBootVFS.class);
//        SpringApplication application = new SpringApplication(clazz);
//        application.run(args);
//
//    }
//
//
//
//    public static void loadApplicationProperties() throws Throwable{
//
//        ClassPathResource classPathResource =new ClassPathResource("application.properties");
//        Properties properties =new Properties();
//        properties.load(classPathResource.getInputStream());
//        System.getProperties().load(classPathResource.getInputStream());
//
//    }
//
//}
