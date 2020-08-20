package com.example.cros.controller;

import com.example.cros.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class AsyncController
{



        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private TestService testService;

        @GetMapping("async")
        public String testAsync() {
            long start = System.currentTimeMillis();
            logger.info("异步方法开始");

            Future<String> future = testService.asyncMethod();
            Future<List<String>> future2 = testService.asyncMethod2();
            String res = "";
            List<String> res2 = new ArrayList<>();

            try {
                 res = future.get();
                 res2 = future2.get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            logger.info("异步方法结束");
            long end = System.currentTimeMillis();
            logger.info("总耗时：{} ms", end - start);
            return res+res2.toString();

        }

        @GetMapping("sync")
        public void testSync() {
            long start = System.currentTimeMillis();
            logger.info("同步方法开始");

            testService.syncMethod();

            logger.info("同步方法结束");
            long end = System.currentTimeMillis();
            logger.info("总耗时：{} ms", end - start);
        }

}
