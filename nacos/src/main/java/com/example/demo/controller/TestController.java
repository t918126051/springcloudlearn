package com.example.demo.controller;


import com.example.demo.pojo.User;
import com.example.demo.pojo.Visitor;
import com.example.demo.service.StatisService;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.StringUtils;
import com.example.demo.utils.jsonUtils;
import com.example.demo.vo.NanChangResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {

    @Autowired
    private jsonUtils jsonUtils;
    @Autowired
    private StatisService statisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public String get(HttpServletResponse response) throws Exception {
        jsonUtils.getJsonFile(response);
        return "ok";
    }


    @GetMapping("/hello")
    public String hello(HttpServletResponse response) throws Exception {
        return "html/hello";
    }

    @GetMapping("/echart")
    public String echart() throws Exception {
        return "html/echartlx";
    }
    @GetMapping("/getPath")
    public String getPath()  {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        return jarF.getParentFile().toString();
    }

    @GetMapping("/getStatistic")
    @ResponseBody
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

    @RequestMapping("/setRv")
    @ResponseBody
    public  NanChangResult setRedisValue(HttpServletRequest request){
//        String sessionId = request.getSession().getId();
//        System.out.println(sessionId);
//        redisTemplate.opsForValue().set("+++sessionId",sessionId );
        Visitor a = new Visitor();
        a.setUserName("wang5");
        a.setPassword("123456");
        Long localTime = System.currentTimeMillis();
        redisTemplate.opsForValue().set("user1",a,5, TimeUnit.MINUTES);

        return NanChangResult.ok();



    }

    @RequestMapping("/getRv")
    @ResponseBody
    public  NanChangResult getRedisValue(){

       Object a =  redisTemplate.opsForValue().get("user1");

        return NanChangResult.ok(a);
    }
}
