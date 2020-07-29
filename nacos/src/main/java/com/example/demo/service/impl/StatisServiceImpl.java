package com.example.demo.service.impl;

import com.example.demo.bo.LampConditionAndNumBo;
import com.example.demo.mapper.LampMapper;
import com.example.demo.pojo.LampPole;
import com.example.demo.pojo.User;
import com.example.demo.service.StatisService;
import com.example.demo.utils.MD52Utils;
import com.example.demo.utils.MD5Utils;
import com.example.demo.vo.NanChangResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisServiceImpl implements StatisService {

    @Resource
    private LampMapper lampMapper;

    @Override
    public NanChangResult countLampByCounty() {
        List<LampConditionAndNumBo> results =lampMapper.selectCountyNumByLamp();
        Map map2=new HashMap<>();
        for (int i = 0; i < results.size(); i++){
            map2.put(results.get(i).getConditionName(),results.get(i).getLampCount());
        }

        return NanChangResult.ok(map2);
    }

    @Override
    public NanChangResult createTable(String tableName) {
        int res = lampMapper.createTable(tableName);
        return NanChangResult.ok(res) ;
    }

    @Override
    public NanChangResult existTable(String tableName) {
        int res = lampMapper.existTable(tableName);
        return  NanChangResult.ok(res);
    }

    @Override
    public NanChangResult getPole(long poleId) {
        LampPole lampPole = lampMapper.selectPoleById(poleId);
        return NanChangResult.ok(lampPole);
    }

    @Override
    public NanChangResult createUser(User user) {
        String password = user.getPassword();
        String md5passsword  = MD52Utils.string2MD5(password);
        user.setPassword(md5passsword);
        int res = lampMapper.insertUser(user);
        return NanChangResult.ok(res);
    }

    @Override
    public NanChangResult getUser(String userName, String password) {
        User user =  lampMapper.getUserIsExist(userName);
        if(user == null) {
            return NanChangResult.build(500,"用户不存在");
        }
        else {
          String loginPwd = MD52Utils.convertMD5(MD52Utils.convertMD5(user.getPassword()));
          if (loginPwd.equals(MD52Utils.string2MD5(password))){
              return NanChangResult.ok(user);
          }else{
              return NanChangResult.build(500,"密码错误");
          }
        }

    }
}
