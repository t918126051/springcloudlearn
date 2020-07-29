package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.LampMapper;
import com.example.demo.pojo.LampPole;
import com.example.demo.utils.MD52Utils;
import com.example.demo.utils.jsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testdemo {
    private  static Logger logger = LoggerFactory.getLogger(testdemo.class);

    @Autowired
    private jsonUtils  jsonUtils;

    @Resource
    private LampMapper lampMapper;

    @Test
    public void  test1(){
        InetAddress inetAddress = null;

        try {
            inetAddress =  inetAddress.getLocalHost();
            String hostName = inetAddress.getHostName();
            String ip = inetAddress.getHostAddress();
            System.out.println(hostName);
            System.out.println(ip);
        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
//        try {
//            InputStream filepy = new FileInputStream("C:\\Users\\zhangliyou\\Desktop\\xuexi\\dataControl.py");
//            PythonInterpreter interpreter = new PythonInterpreter();
//            interpreter.execfile(filepy,);
//            filepy.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        try {
            Process proc;
            String[] args1 = new String[] {"D:\\Anaconda3\\python.exe","D:\\jar\\dataControl.py"};
            proc=Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int re = proc.waitFor();//因为是process这个进程调用.py文件， 所以要将当前进程阻塞等待.py文件执行完毕， 若果.py文件成功运行完毕，此方法将返回0，代表执行成功
            System.out.println(re);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test4(){
        LampPole  lampPole = lampMapper.selectPoleById(1L);
        System.out.println(lampPole.toString());
        String jsonStr = JSON.toJSONString(lampPole);
        System.out.println(jsonStr);

        LampPole lp = JSON.parseObject(jsonStr,LampPole.class);
        System.out.println(lp.toString());
    }

    @Test
    public void test5(){
        String newpwd = "654321";
        String md5newpwd = MD52Utils.string2MD5(newpwd);
        System.out.println(md5newpwd);
        String coventpwd = MD52Utils.convertMD5(MD52Utils.convertMD5(md5newpwd));
        System.out.println(coventpwd);
        if (md5newpwd.equals(coventpwd)){
            System.out.println("ok");
        }
    }

    @Test
    public void test6(){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String newpwd = "654321";
        char[] newpwds = newpwd.toCharArray();
        byte[] bypwds = new byte[newpwds.length];
        for ( int i = 0; i < newpwds.length; i++) {
           // System.out.println(newpwds[i]);
            bypwds[i] = (byte) newpwds[i];
            //System.out.println(bypwds[i] );
        }
        byte[] md5Bytes = md5.digest(bypwds);
        for (int j = 0 ; j < md5Bytes.length;j++) {
            System.out.println((int)md5Bytes[j]+","+((int)md5Bytes[j] & 0xff));
        }
    }


}
