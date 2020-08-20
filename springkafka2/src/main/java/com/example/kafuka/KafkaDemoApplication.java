package com.example.kafuka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDemoApplication {
    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(KafkaDemoApplication.class);
        sp.run(args);
    }
}
