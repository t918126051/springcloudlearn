package com.example.emaidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmailDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailDemoApplication.class,args);
    }
}
