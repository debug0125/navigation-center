package com.pzc.navigationweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ryf
 * @date 5/8/21 3:26 PM
 */
@ImportResource("classpath:spring-application.xml")
@ServletComponentScan(basePackages = {"com.pzc.navigationweb.filter"})
@EnableScheduling
@SpringBootApplication
public class NavigationWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavigationWebApplication.class, args);
    }

}
