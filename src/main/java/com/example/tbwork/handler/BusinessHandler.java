package com.example.tbwork.handler;

import com.example.tbwork.dao.UserDao;
import com.example.tbwork.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BusinessHandler implements HandlerInterceptor {
    @Autowired UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception {
        System.out.println("token获取成功");
        String token=request.getHeader("Authorization");
        System.out.println(token);
        if(token!=null){
            System.out.println("token验证中");
            boolean result = TokenUtil.verify(token);
            if(result){
                System.out.println("token验证成功");
                String account=TokenUtil.getUserAccount(token);
                if(userDao.findByAccount(account).getBusiness()==1){
                    return true;
                }
                return false;
            }
        }
        System.out.println("token是null");
        return false;
    }
}
