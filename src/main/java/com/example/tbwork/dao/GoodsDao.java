package com.example.tbwork.dao;

import com.example.tbwork.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsDao extends JpaRepository<Goods,Integer> {
    Goods findById(int id);
    void deleteByName(String name);
    List<Goods> findAllByNameLike(String keyword);
}
