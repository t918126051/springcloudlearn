package com.example.cros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CrosDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrosDemoApplication.class,args);
    }
}
