package com.example.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Component
public class jsonUtils {
    public  void getJsonFile(HttpServletResponse response) throws Exception {
//        String property = System.getProperty("java.class.path");
//        System.out.println(property);

//        //获取跟目录
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//        if(!path.exists()) path = new File("");
//        System.out.println("path:"+path.getAbsolutePath());
//
////如果上传目录为/static/images/upload/，则可以如下获取：
//        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
//        if(!upload.exists()) upload.mkdirs();
//        System.out.println("upload url:"+upload.getAbsolutePath());
//
//        String basePatha1 = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//        basePatha1 = URLDecoder.decode(basePatha1, "utf-8");
//        System.out.println(basePatha1);

        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        System.out.println(jarF.getParentFile().toString());
        System.out.println(h.getDir()+"/static/jsonData");
//
//        String path1 = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static/jsonData";
//        System.out.println(path1);
        //File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        File file = new File(h.getDir()+"/static/jsonData/exampleWrite.json");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 4 ;i++ ) {
            JSONObject obj=new JSONObject();
            obj.put("name","张三"+i);
            obj.put("age",20+i);
            jsonArray.add(obj);
        }
        osw.write(jsonArray.toString());
        osw.flush();
        osw.close();

   }
}
