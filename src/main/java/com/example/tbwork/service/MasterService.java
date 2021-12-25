package com.example.tbwork.service;

import com.example.tbwork.dao.MasterDao;
import com.example.tbwork.pojo.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {
    @Autowired
    MasterDao masterDao;
    public boolean isExist(String account){
        Master master= masterDao.findByAccount(account);
        return null!=master;
    }
    public Master get(String account,String password){
        return masterDao.getByAccountAndPassword(account,password);
    }
}
