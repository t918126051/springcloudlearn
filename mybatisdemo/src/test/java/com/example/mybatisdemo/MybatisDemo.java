package com.example.mybatisdemo;

import com.example.mybatisdemo.pojo.GoodsOrder;
import com.example.mybatisdemo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisDemo {
    @Autowired
    private OrderService orderService;
    @Test
    public void test(){
      List<GoodsOrder> a = orderService.getAllGoods();
      System.out.println(a.get(0).toString());
    }
}
