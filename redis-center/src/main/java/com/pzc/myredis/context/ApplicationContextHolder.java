//package com.pzc.myredis.context;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
///**
// * spring 容器持有者
// */
//public class ApplicationContextHolder implements ApplicationContextAware {
//
//    public static  ApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        ApplicationContextHolder.applicationContext = applicationContext;
//    }
//
//    public static <T> T getBean(String id, Class clazz) {
//        return (T) applicationContext.getBean(id);
//    }
//}
