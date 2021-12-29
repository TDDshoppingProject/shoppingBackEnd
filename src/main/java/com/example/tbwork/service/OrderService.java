package com.example.tbwork.service;

import com.example.tbwork.dao.GoodsDao;
import com.example.tbwork.dao.OrderDao;
import com.example.tbwork.dao.UserDao;
import com.example.tbwork.pojo.Goods;
import com.example.tbwork.pojo.Order;
import com.example.tbwork.pojo.Shop;
import com.example.tbwork.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ShopService shopService;

    public Order createOrder(int number,int gid,int uid){
        Order order=new Order();
        order.setNumber(number);
        Goods goods=goodsDao.findById(gid);
        order.setGoods(goods);
        order.setUser(userDao.findById(uid));
        order.setShop(goods.getShop());
        float total=goods.getPrice()*number;
        order.setTotal(total);
        orderDao.save(order);
        return order;
    }
    public List<Order> orderListBusiness(int id){
        User business=userDao.findById(id);
        Shop shop=shopService.getByUser(business);
        Sort sort=Sort.by(Sort.Direction.ASC,"status");
        return orderDao.findAllByShop(shop,sort);
    }
    public List<Order> orderListUser(int id){
        User user=userDao.findById(id);
        Sort sort=Sort.by(Sort.Direction.DESC,"status");
        return orderDao.findAllByUser(user,sort);
    }
    public Order setOrderStatus(Order order){
        int status=order.getStatus()+1;
        order.setStatus(status);
        orderDao.save(order);
        return order;
    }
    public Order deleteOrder(Order order){
        orderDao.delete(order);
        return order;
    }
    public Map<String,Object> getAOrder(Order order){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",order.getId());
        map.put("number",order.getNumber());
        map.put("status",order.getStatus());
        map.put("goods",order.getGoods());
        map.put("shop",order.getShop());
        map.put("user",order.getUser());
        map.put("total",order.getTotal());
        return map;
    }
    public List<Map> getOrders(List<Order> list){
        List<Map> result=new ArrayList<Map>();
        for (Order order:list) {
            result.add(getAOrder(order));
        }
        return result;
    }
}
