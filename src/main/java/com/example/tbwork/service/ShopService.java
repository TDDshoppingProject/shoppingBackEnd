package com.example.tbwork.service;

import com.example.tbwork.dao.ShopDao;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    ShopDao shopDao;
    public Shop getByUser(User user){
        return shopDao.findByUser(user);
    }
    public void deleteShop(Shop shop){
        shopDao.delete(shop);
    }

}
