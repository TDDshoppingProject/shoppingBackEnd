package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import com.example.tbwork.service.ShopService;
import com.example.tbwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    ShopService shopService;
    @PostMapping("/becomebusiness/{uid}/{businame}")//成为商家并创建店铺
    public Result becomeBusiness(@PathVariable("uid") int uid, @PathVariable("businame") String businame){
            Shop shop=userService.becomeBusiness(uid,businame);
            return new Result(200,"创建完成",shop);
    }
    @PostMapping("/setinfor/{uid}/{name}/{sex}/{age}/{mobile}/{email}/{address}/{password}")
    public Result setInfor(@PathVariable("uid")int uid,@PathVariable("name")String name,@PathVariable("sex")char sex,@PathVariable("age")int age,@PathVariable("mobile")String mobile,@PathVariable("email")String email,@PathVariable("address")String address,@PathVariable("password")String password){
        return new Result(200,"更新成功",userService.setInfor(uid, name, sex, age, mobile, email, address, password));
    }
    @PostMapping("/getinfor/{uid}")
    public Result getInfor(@PathVariable("uid")int uid){
        return new Result(200,"更新成功",userService.getInfor(uid));
    }
    @Transactional
    @PostMapping("/business/cancelbusiness/{uid}")
    public Result cancelBusiness(@PathVariable("uid") int uid){
        return new Result(200,"删除成功",userService.cancelBusiness(uid));
    }
    @PostMapping("/getshopinf/{uid}")
    public Result getShopInf(@PathVariable("uid")int uid){
        User user=userDao.findById(uid);
        return new Result(200,"删除成功",shopService.getByUser(user));
    }
}
