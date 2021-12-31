package com.example.tbwork.dao;

import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsDao extends JpaRepository<Goods,Integer> {
    Goods findById(int id);
    void deleteByName(String name);
    List<Goods> findAllByNameLike(String keyword);
    List<Goods> findAllByShop(Shop shop);
    void deleteById(int id);
}
