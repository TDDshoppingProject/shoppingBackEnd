package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import com.example.tbwork.service.GoodsService;
import com.example.tbwork.service.MasterService;
import com.example.tbwork.service.ShopService;
import com.example.tbwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterController {
    @Autowired MasterService masterService;
    @Autowired UserService userService;
    @Autowired GoodsService goodsService;
    @Autowired ShopService shopService;
    @PostMapping("/admin/userlist")//用户查询
    public Result userList(@RequestBody String account){//有参数按参数查询，没有查询所有
        account=account.substring(1,account.length()-1);
        if(account.length()!=0){
            return new Result<List>(200,"查询成功",userService.listByAccount(account));
        }
        return new Result<List>(200,"查询成功",userService.list());
    }
    @Transactional
    @PostMapping("/admin/deleteuser")//删除用户
    public Result deleteUser(@RequestBody String account){
        account=account.substring(1,account.length()-1);
        userService.deleteByAccount(account);
        return new Result<List>(200,"删除成功",null);

    }
    @PostMapping("/admin/businesslist")
    public Result businessList(@RequestBody String account){
        account=account.substring(1,account.length()-1);
        System.out.println(account);
        if(account.length()!=0){
            return new Result<List>(200,"查询成功",userService.listBusinessByAccount(account,1));
        }
        return new Result<List>(200,"查询成功",userService.businessList());
    }
    @PostMapping("/admin/cancelbusiness")//取消商家资格并删除商店
    public Result cancelBusiness(@RequestBody String account){
        account=account.substring(1,account.length()-1);
        User user=userService.getByAccount(account);
        userService.cancelBusiness(user);
        Shop shop=shopService.getByUser(user);
        shopService.deleteShop(shop);
        return new Result<List>(200,"删除成功",null);
    }
    @PostMapping("/goodslist")
    public Result goodsList(@RequestBody String name){
        name=name.substring(1,name.length()-1);
        if(name.length()!=0){
            return new Result<List>(200,"查询成功",goodsService.listByName(name));
        }
        return new Result<List>(200,"查询成功",goodsService.list());
    }
    @Transactional
    @PostMapping("/admin/deletegoods")
    public Result deleteGoods(@RequestBody String name){
        name=name.substring(1,name.length()-1);
        goodsService.deleteByName(name);
        return new Result<List>(200,"删除成功",null);
    }

}
