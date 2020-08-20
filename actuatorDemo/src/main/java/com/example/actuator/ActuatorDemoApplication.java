package com.example.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ActuatorDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorDemoApplication.class,args);
    }
}
