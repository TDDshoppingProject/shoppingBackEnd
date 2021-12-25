package com.example.tbwork.config;

import com.example.tbwork.MasterHandler;
import com.example.tbwork.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private UserHandler userHandler;
    @Autowired
    private MasterHandler masterHandler;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/login");
        excludePath.add("/register");
        excludePath.add("/admin/**");
        registry.addInterceptor(masterHandler).addPathPatterns("/admin/**");
        registry.addInterceptor(userHandler).addPathPatterns("/**").excludePathPatterns(excludePath);
    }
}
