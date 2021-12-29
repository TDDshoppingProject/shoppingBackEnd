package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired ReviewService reviewService;

    @PostMapping("/addreview/{id}")
    public Result addReview(@RequestBody String text, @PathVariable("id") int id){//输入商品id
        text=text.substring(1,text.length()-1);
        reviewService.addReview(id,text);
        return new Result(200,"添加成功",null);
    }
}
