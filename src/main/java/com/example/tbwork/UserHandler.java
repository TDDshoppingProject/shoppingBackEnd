package com.example.tbwork;

import com.example.tbwork.service.UserService;
import com.example.tbwork.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserHandler implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception {
        System.out.println();
        String token=request.getHeader("Authorization");
        if(token!=null){
            boolean result = TokenUtil.verify(token);
            if(result){
                String account=TokenUtil.getUserAccount(token);
                if(userService.isExist(account)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
