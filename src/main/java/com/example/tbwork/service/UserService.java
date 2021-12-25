package com.example.tbwork.service;

import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired UserDao userDao;
    @Autowired ShopService shopService;
    public boolean isExist(String account){
        User user=getByAccount(account);
        return null!=user;
    }
    public User getByAccount(String account){
        return userDao.findByAccount(account);
    }
    public void add(User user){
        userDao.save(user);
    }
    public User get(String account, String password) {
        return userDao.getByAccountAndPassword(account,password);
    }

    public List<User> list(){
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        return userDao.findAll(sort);
    }
    public void deleteByAccount(String account){
        userDao.deleteByAccount(account);
    }
    public List<Map> businessList(){
        List<User> userList=userDao.findAllByBusiness(1);
        List<Map> result=getBusiness(userList);
        return result;
    }
    public List<User> listByAccount(String account){
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        return userDao.findAllByAccount(account);
    }
    public List<Map> listBusinessByAccount(String account,int business){
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        List<User> userList=userDao.findAllByAccountAndBusiness(account, business);
        List<Map> result=getBusiness(userList);
        return result;
    }
    public void cancelBusiness(User user){
        user.setBusiness(0);
        userDao.save(user);
    }
    public List<Map> getBusiness(List<User> userList) {
        Map<String, Object> list=null;
        List<Map> result = new ArrayList<Map>();
        for (User user : userList) {
            list = new HashMap<>();
            list.put("id", user.getId() + "");
            list.put("account", user.getAccount());
            list.put("mobile", user.getMobile());
            list.put("email", user.getEmail());
            list.put("shopname", (shopService.getByUser(user)).getName());
            result.add(list);
        }
        return result;
    }
}

