package com.example.tbwork.service;

import com.example.tbwork.dao.GoodsDao;
import com.example.tbwork.dao.ReviewDao;
import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Review;
import com.example.tbwork.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired ReviewDao reviewDao;
    @Autowired GoodsDao goodsDao;
    @Autowired
    UserDao userDao;
    public List<Review> reviewList(Goods goods){
        return reviewDao.findAllByGoods(goods);
    }
    public void addReview(int gid,int uid,String text){
        Review review=new Review();
        review.setText(text);
        Goods goods=goodsDao.findById(gid);
        User user=userDao.findById(uid);
        review.setGoods(goods);
        review.setUser(user);
        reviewDao.save(review);
    }
}
