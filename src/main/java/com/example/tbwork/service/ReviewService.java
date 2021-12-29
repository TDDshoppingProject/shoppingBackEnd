package com.example.tbwork.service;

import com.example.tbwork.dao.GoodsDao;
import com.example.tbwork.dao.ReviewDao;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired ReviewDao reviewDao;
    @Autowired GoodsDao goodsDao;
    public List<Review> reviewList(Goods goods){
        return reviewDao.findAllByGoods(goods);
    }
    public void addReview(int id,String text){
        Review review=new Review();
        review.setText(text);
        Goods goods=goodsDao.findById(id);
        review.setGoods(goods);
        reviewDao.save(review);
    }
}
