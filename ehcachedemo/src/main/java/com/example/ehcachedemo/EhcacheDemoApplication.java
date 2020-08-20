package com.example.ehcachedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EhcacheDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EhcacheDemoApplication.class,args);
    }
}
