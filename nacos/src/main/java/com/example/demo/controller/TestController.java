package com.example.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.example.demo.pojo.User;
import com.example.demo.service.StatisService;
import com.example.demo.utils.StringUtils;
import com.example.demo.utils.jsonUtils;
import com.example.demo.vo.NanChangResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private jsonUtils jsonUtils;
    @Autowired
    private StatisService statisService;

    @GetMapping("/test")
    public String get(HttpServletResponse response) throws Exception {
        jsonUtils.getJsonFile(response);
        return "ok";
    }

    @GetMapping("/hello")
    public String hello(HttpServletResponse response) throws Exception {
        return "html/hello";
    }
    @GetMapping("/getPath")
    public String getPath()  {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        return jarF.getParentFile().toString();
    }

    @GetMapping("/getStatistic")
    public NanChangResult lampListResultStatistic()  {
        return statisService.countLampByCounty();
    }

    @RequestMapping("/createTable/{tableName}")
    public NanChangResult createTable(@PathVariable Map<String,String> map){
        String tableName  = StringUtils.toUnderString(map.get("tableName"));;
        return  statisService.createTable(tableName);
    }

    @RequestMapping("/findTable/{tableName}")
    public NanChangResult findTable(@PathVariable Map<String,String> map){
        String tableName  = StringUtils.toUnderString(map.get("tableName"));
        return  statisService.existTable(tableName);
    }

    @RequestMapping("/getPole/{poleId}")
    public NanChangResult getPole(@PathVariable("poleId") long poleId ){
        return  statisService.getPole(poleId);
    }

    @RequestMapping("/createUser")
    public  NanChangResult createUser(User user){
        return  statisService.createUser(user);

    }

    @RequestMapping("/getUser")
    public  NanChangResult getUser(String userName,String password){
        return  statisService.getUser(userName,password);

    }


}
