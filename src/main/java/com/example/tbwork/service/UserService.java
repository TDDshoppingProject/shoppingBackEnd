package com.example.tbwork.service;

import com.example.tbwork.dao.ShopDao;
import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired UserDao userDao;
    @Autowired ShopService shopService;
    @Autowired
    ShopDao shopDao;
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
    public Shop becomeBusiness(int uid, String businame){
        User user=userDao.findById(uid);
        if(user.getBusiness()==0){
            user.setBusiness(1);
            userDao.save(user);
            Shop shop=new Shop();
            shop.setName(businame);
            shop.setUser(user);
            shopDao.save(shop);
            return shop;
        }
        return null;
    }
    public User setInfor(int uid,String name,char sex,int age,String mobile,String email,String address,String password){
        User user=userDao.findById(uid);
        user.setPassword(password);
        user.setAddress(address);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setAge(age);
        user.setSex(sex);
        user.setName(name);
        userDao.save(user);
        return user;
    }
    public User getInfor(int uid){
        return userDao.findById(uid);
    }
    public Shop cancelBusiness(int uid){
        User user=userDao.findById(uid);
        user.setBusiness(0);
        Shop shop=shopDao.findByUser(user);
        shopDao.delete(shop);
        return shop;
    }
}

