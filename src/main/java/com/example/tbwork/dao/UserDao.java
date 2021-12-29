package com.example.tbwork.dao;

import com.example.tbwork.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByAccount(String account);
    User findById(int id);
    User getByAccountAndPassword(String account,String password);
    List<User> findAllByBusiness(int business);
    List<User> findAllByAccountAndBusiness(String account,int business);
    void deleteByAccount(String account);
    List<User> findAllByAccount(String account);

}
