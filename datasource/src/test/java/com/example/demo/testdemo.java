package com.example.demo;


import com.example.demo.mapper.MysqlOneMapper;
import com.example.demo.mapper.MysqlTwoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class testdemo {
    private  static Logger logger = LoggerFactory.getLogger(testdemo.class);
    @Resource
    private MysqlOneMapper mysqlOneMapper;
    @Resource
    private MysqlTwoMapper mysqlTwoMapper;

    @Test
    public  void  test1(){
        List a  = mysqlOneMapper.getAllUser();
        System.out.println(a);
        List b =  mysqlTwoMapper.getAllSysUser();
        System.out.println(b);
    }
}
