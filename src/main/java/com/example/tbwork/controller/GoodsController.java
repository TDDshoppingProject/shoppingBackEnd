package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @PostMapping("/getgoods/{id}")
    public Result getGoods(@PathVariable("id") int id){
        return new Result(200,"查询成功",goodsService.getGood(id));
    }
    @PostMapping("/goodslist/{name}")
    public Result goodsList(@PathVariable("name") String name){
        return new Result<List>(200,"查询成功",goodsService.listByName(name));
    }
}
