package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.pojo.Master;
import com.example.tbwork.pojo.User;
import com.example.tbwork.service.MasterService;
import com.example.tbwork.service.UserService;
import com.example.tbwork.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    MasterService masterService;
    @CrossOrigin
    @PostMapping("/register")//注册
    @ResponseBody
    public Object register(User user){
        String account = user.getAccount();
        String password = user.getPassword();
        account= HtmlUtils.htmlEscape(account);//防止恶意注册的奇怪用户名
        user.setAccount(account);
        boolean exist= userService.isExist(account)||masterService.isExist(account);
        if(exist) {
            return new Result(400,"用户名被占用",null);//用户名已占用
        }
        user.setPassword(password);
        userService.add(user);
        return new Result(201,"用户注册成功",null);
    }
    @CrossOrigin
    @PostMapping("/login")//登录
    @ResponseBody
    public Object login(@RequestBody User userParam, HttpSession session){
        String account=HtmlUtils.htmlEscape(userParam.getAccount());//防止恶意注册
        String password=userParam.getPassword();
        String token= TokenUtil.sign(userParam);
        Master master=masterService.get(account,password);
        User user=userService.get(account,password);
        if(null!=master){return new Result(200,"admin",token);}
        if(null!=user){ return new Result(200,"user",token); }
        return new Result(400,"账号或密码错误",null);//账号密码错


    }

}
