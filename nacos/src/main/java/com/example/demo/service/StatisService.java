package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.vo.NanChangResult;

public interface StatisService {

    NanChangResult countLampByCounty();
    NanChangResult createTable(String tableName);
    NanChangResult existTable(String tableName);
    NanChangResult getPole(long poleId);
    NanChangResult createUser(User user);
    NanChangResult getUser(String userName,String password);

}
