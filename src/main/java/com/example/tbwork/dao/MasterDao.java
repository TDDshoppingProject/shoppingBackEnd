package com.example.tbwork.dao;

import com.example.tbwork.pojo.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterDao extends JpaRepository<Master,Integer> {
    Master getByAccountAndPassword(String account,String password);
    Master findByAccount(String account);
}
