package com.example.tbwork.controller;

import com.example.tbwork.Result;
import com.example.tbwork.dao.OrderDao;
import com.example.tbwork.pojo.Order;
import com.example.tbwork.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDao orderDao;
    @ResponseBody
    @PostMapping("/createorder/{number}/{gid}/{uid}")
    public Result createOrder(@PathVariable("number") int number,@PathVariable("gid")int gid,@PathVariable("uid") int uid){
        Order order=orderService.createOrder(number,gid,uid);
        return new Result(200,"创建成功",order);
    }
    @PostMapping("/business/{id}/orderlist")
    public Result orderListBusiness(@PathVariable("id") int id){//输入商家id，查询订单
        return new Result(200,"查询成功",orderService.getOrders(orderService.orderListBusiness(id)));
    }
    @PostMapping("/{id}/orderlist")
    public Result orderListUser(@PathVariable("id") int id){//输入用户id，查询订单
        return new Result(200,"查询成功",orderService.getOrders(orderService.orderListUser(id)));
    }
    @PostMapping("/business/setorderstatus/{id}")
    public Result setOrderStatus(@PathVariable("id") int id){//输入订单id，改变状态（商家）
        Order order=orderDao.findById(id);
        if(order.getStatus()!=1){return new Result(400,"没有权限",null);}
        return new Result(200,"设置成功",orderService.setOrderStatus(order));
    }
    @PostMapping("/setorderstatus/{id}")
    public Result setOrderStatus2(@PathVariable("id") int id){//输入订单id，改变状态
        Order order=orderDao.findById(id);
        if(order.getStatus()!=2){return new Result(400,"没有权限",null);}
        return new Result(200,"设置成功",orderService.setOrderStatus(order));
    }
    @Transactional
    @PostMapping("/deleteorder/{id}")
    public Result deleteOrder(@PathVariable("id") int id){//输入订单id，删除订单
        Order order=orderDao.findById(id);
        if(order.getStatus()!=3){return new Result(400,"没有权限",null);}
        return new Result(200,"删除成功",orderService.deleteOrder(order));
    }

}
