package com.example.tbwork.handler;

import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.User;
import com.example.tbwork.service.UserService;
import com.example.tbwork.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class IdHandler implements HandlerInterceptor {
    @Autowired
    UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception {
        System.out.println();
        String token=request.getHeader("Authorization");
        if(token!=null){
            boolean result = TokenUtil.verify(token);
            if(result){
                String account=TokenUtil.getUserAccount(token);
                Map pathVariables=(Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                int id=Integer.parseInt((String)pathVariables.get("id"));
                User user=userDao.findByAccount(account);
                System.out.println(id+" ");
                System.out.println(user.getId()+" ");
                if(user.getId()==id){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
