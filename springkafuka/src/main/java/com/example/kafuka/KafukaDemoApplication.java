package com.example.kafuka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafukaDemoApplication {
    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(KafukaDemoApplication.class);
        sp.run(args);
    }
}
