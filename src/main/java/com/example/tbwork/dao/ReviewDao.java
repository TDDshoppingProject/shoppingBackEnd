package com.example.tbwork.dao;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDao extends JpaRepository<Review,Integer> {
    List<Review> findAllByGoods(Goods goods);
}
