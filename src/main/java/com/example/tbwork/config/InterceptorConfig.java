package com.example.tbwork.config;

import com.example.tbwork.handler.BusinessHandler;
import com.example.tbwork.handler.IdHandler;
import com.example.tbwork.handler.MasterHandler;
import com.example.tbwork.handler.UserHandler;
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
    @Autowired
    private BusinessHandler businessHandler;
    @Autowired
    private IdHandler idHandler;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/login");
        excludePath.add("/register");
        excludePath.add("/admin/**");
        excludePath.add("/goodslist");
        registry.addInterceptor(masterHandler).addPathPatterns("/admin/**");
        registry.addInterceptor(userHandler).addPathPatterns("/**").excludePathPatterns(excludePath);
        registry.addInterceptor(businessHandler).addPathPatterns("/business/**");
        registry.addInterceptor(idHandler).addPathPatterns("/business/{id}/**").addPathPatterns("/{id}/orderlist");
    }
}
