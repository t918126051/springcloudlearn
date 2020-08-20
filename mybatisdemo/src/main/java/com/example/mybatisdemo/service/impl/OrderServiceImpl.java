package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.mapper.GoodsOrderMapper;
import com.example.mybatisdemo.pojo.GoodsOrder;
import com.example.mybatisdemo.pojo.GoodsOrderExample;
import com.example.mybatisdemo.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private GoodsOrderMapper goodsOrderMapper;
    @Override
    public List<GoodsOrder> getAllGoods() {
        PageHelper.startPage(5,1);
        GoodsOrderExample example = new GoodsOrderExample();
        List<GoodsOrder> lists = goodsOrderMapper.selectByExample(example);
        PageInfo<GoodsOrder> pageInfo = new PageInfo<GoodsOrder>(lists);
        List<GoodsOrder> result = pageInfo.getList();
        List<GoodsOrder> newresult = new ArrayList<>();
        for(GoodsOrder g : result){
            newresult.add(g);
        }
        return  newresult;
    }
}
