package com.pzc.navigationcenterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ryf
 * @date 5/8/21 8:12 PM
 */
@SpringBootApplication
//@ImportResource("classpath:navigation-center-service-application.xml")
public class NavigationCenterServiceApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(NavigationCenterServiceApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
