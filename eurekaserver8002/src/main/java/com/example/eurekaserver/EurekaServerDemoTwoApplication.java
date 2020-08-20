package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerDemoTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerDemoTwoApplication.class,args);
    }
}
