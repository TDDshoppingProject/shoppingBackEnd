package com.example.tbwork.dao;

import com.example.tbwork.pojo.Order;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {
    List<Order> findAllByShop(Shop shop, Sort sort);
    List<Order> findAllByUser(User user,Sort sort);
    Order findById(int id);
    void delete(Order order);
}
