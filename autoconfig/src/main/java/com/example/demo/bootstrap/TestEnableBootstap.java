package com.example.demo.bootstrap;

import com.example.demo.annotation.DefineAnnoation;
import com.example.demo.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableHelloWorld
//@DefineAnnoation
@EnableAutoConfiguration
public class TestEnableBootstap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(TestEnableBootstap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String hello = context.getBean("hello", String.class);
        //String def = context.getBean("defineAnnoation",String.class);
        System.out.println("hello Bean: " + hello);
        //System.out.println("\r\n"+def);
        context.close();
    }
}