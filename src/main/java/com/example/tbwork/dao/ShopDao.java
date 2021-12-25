package com.example.tbwork.dao;

import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopDao extends JpaRepository<Shop,Integer> {
    Shop findByUser(User user);
}
