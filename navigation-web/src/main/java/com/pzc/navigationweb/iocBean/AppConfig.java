package com.pzc.navigationweb.iocBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ryf
 * @date 8/16/21 10:45 AM
 */
@Configuration
public class AppConfig {
    // 使用@Bean 注解表明myBean需要交给Spring进行管理
    // 未指定bean 的名称，默认采用的是 "方法名" + "首字母小写"的配置方式
//    @Bean
    public MyBean myBean(){
        return new MyBean();
    }

    public class MyBean {

        public MyBean(){
            System.out.println("MyBean Initializing>>>>>>>>>>>>>>>>>>>>");
        }

        public String getTestStr(){
            return "MyBean test>>>";
        }

        public void initTest(){
            System.out.println("MyBean init>>>");
        }
    }
}