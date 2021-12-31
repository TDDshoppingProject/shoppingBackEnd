package com.example.tbwork.service;

import com.example.tbwork.dao.GoodsDao;
import com.example.tbwork.dao.ShopDao;
import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Review;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import com.example.tbwork.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired GoodsDao goodsDao;
    @Autowired ReviewService reviewService;
    @Autowired
    UserDao userDao;
    @Autowired
    ShopDao shopDao;
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
        name = '%' + name + '%';
        List<Goods> goodsList=goodsDao.findAllByNameLike(name);
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
            list.put("price", goods.getPrice());
            list.put("shopname",goods.getShop().getName());
            result.add(list);
        }
        return result;
    }
    public Map<String,Object> getGood(int id){
        Goods goods=goodsDao.findById(id);
        List<Review>reviews=reviewService.reviewList(goods);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("goods",goods);
        map.put("review",reviews);
        return map;
    }
    public List<Goods> goodsListByShop(int uid){
        User user=userDao.findById(uid);
        Shop shop=shopDao.findByUser(user);
        return goodsDao.findAllByShop(shop);
    }
    public Goods goodsUpload(int uid, String name, float price, MultipartFile img)throws Exception{
        User user=userDao.findById(uid);
        Shop shop=shopDao.findByUser(user);
        Goods goods=new Goods();
        goods.setName(name);
        goods.setPrice(price);
        String url=ImageUtils.imageUpload(img);
        goods.setImg(url);
        goodsDao.save(goods);
        return goods;
    }
    public Goods setGoods(int gid, String name, float price, MultipartFile img)throws Exception{
        Goods goods=goodsDao.findById(gid);
        goods.setName(name);
        goods.setPrice(price);
        String url=ImageUtils.imageUpload(img);
        goods.setImg(url);
        goodsDao.save(goods);
        return goods;
    }
    public void deleteGoods(int gid){
        goodsDao.deleteById(gid);
    }
}
