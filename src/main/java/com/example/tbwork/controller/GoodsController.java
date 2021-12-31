package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        return new Result(200,"查询成功",goodsService.listByName(name));
    }
    @PostMapping("/business/goodslistbyshop/{uid}")
    public Result goodsListByShop(@PathVariable("uid") int uid){
        return new Result(200,"查询成功",goodsService.goodsListByShop(uid));
    }
    @PostMapping("/img/business/goodsupload/{uid}/{name}/{price}/{img}")
    public Result goodsUpload(@PathVariable("uid")int uid, @PathVariable("name")String name, @PathVariable("price") float price,@PathVariable("img") MultipartFile img){
        try{
            return new Result(200,"创建成功",goodsService.goodsUpload(uid, name, price, img));
        }
        catch (Exception e){
            e.printStackTrace();
            return  new Result(120,"失败",null);
        }
    }
    @PostMapping("/img/business/setgoods/{gid}/{name}/{price}")
    public Result setGoods(MultipartFile img,@PathVariable("gid")int gid, @PathVariable("name")String name, @PathVariable("price") float price){
        try{
            return new Result(200,"更新成功",goodsService.setGoods(gid, name, price, img));
        }
        catch (Exception e){
            e.printStackTrace();
            return  new Result(120,"失败",null);
        }
    }
    @Transactional
    @PostMapping("/business/deletegoods/{gid}")
    public Result deleteGoods(@PathVariable("gid")int gid){
        goodsService.deleteGoods(gid);
        return new Result(200,"删除成功",null);
    }
}
