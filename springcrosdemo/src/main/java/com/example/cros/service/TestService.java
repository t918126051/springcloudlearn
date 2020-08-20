package com.example.cros.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncMethod() {
        sleep();
        logger.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<String>("hello async");
    }

    @Async("asyncThreadPoolTaskExecutor")
    public Future<List<String>> asyncMethod2() {
        sleep();
        logger.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        List<String> a = new ArrayList<>();
        a.add("hello2");
        return new AsyncResult<List<String>>(a);
    }

    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
