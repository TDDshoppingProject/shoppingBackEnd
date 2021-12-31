package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired ReviewService reviewService;

    @PostMapping("/addreview/{gid}/{uid}")
    public Result addReview(@RequestBody String text, @PathVariable("gid") int gid,@PathVariable("uid") int uid){//输入商品id
        text=text.substring(1,text.length()-1);
        reviewService.addReview(gid,uid,text);
        return new Result(200,"添加成功",null);
    }
}
