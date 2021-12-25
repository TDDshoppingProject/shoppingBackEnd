package com.example.tbwork.service;

import com.example.tbwork.dao.GoodsDao;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired GoodsDao goodsDao;
    public List<Map> list(){
        Sort sort=Sort.by(Sort.Direction.ASC,"id");
        List<Goods> goodsList=goodsDao.findAll(sort);
        List<Map> result=getGoods(goodsList);
        return result;
    }
    public void deleteByName(String name){
        goodsDao.deleteByName(name);
    }
    public List<Map> listByName(String name){
        List<Goods> goodsList=goodsDao.findAllByName(name);
        List<Map> result=getGoods(goodsList);
        return result;
    }
    public List<Map> getGoods(List<Goods> goodsList){
        Map<String, Object> list=null;
        List<Map> result = new ArrayList<Map>();
        for (Goods goods : goodsList) {
            list = new HashMap<>();
            list.put("id", goods.getId());
            list.put("img", goods.getImg());
            list.put("name", goods.getName());
            list.put("introduce", goods.getIntroduce());
            list.put("price", goods.getPrice());
            list.put("shopname",goods.getShop().getName());
            result.add(list);
        }
        return result;
    }
}
