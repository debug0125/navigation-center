package com.pzc.navigationweb.config;

import com.pzc.navigationweb.filter.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryf
 * @date 5/8/21 7:41 PM
 */
@Configuration
public class WebMvc implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = new ArrayList<>();
        excludeList.add("/register");
        excludeList.add("/login");
        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeList);

    }
}
