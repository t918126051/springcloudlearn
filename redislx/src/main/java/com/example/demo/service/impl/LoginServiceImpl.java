package com.example.demo.service.impl;

import com.example.demo.dto.NanChangResult;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public NanChangResult loginIn(String userName, String password, HttpServletRequest request) {
        User user = userMapper.selectUserByNameAndPwd(userName,password);
        if(user != null){
            String sessionId = request.getSession().getId();
            redisTemplate.opsForValue().set(sessionId,user);
            return NanChangResult.build(200,"成功");
        }else{
            return NanChangResult.build(500,"错误");
        }
    }
}
