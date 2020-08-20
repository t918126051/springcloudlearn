package com.example.demo;


import com.example.demo.mapper.UserMapper;

import com.example.demo.pojo.Permissions;
import com.example.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testdemo {
    private  static Logger logger = LoggerFactory.getLogger(testdemo.class);

    @Resource
    private UserMapper userMapper;


    @Test
    public void  test1(){
        User admin = userMapper.queryUserByUsername("admin");
        System.out.println(admin.toString());
        Permissions permission = userMapper.queryPermissionByUsername("admin");
        System.out.println(permission.toString());
    }

}
