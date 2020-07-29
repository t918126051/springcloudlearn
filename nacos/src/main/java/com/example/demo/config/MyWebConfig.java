package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Value("${jsonFilePath}")
    private String jsonFile;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(jsonFile);
        registry.addResourceHandler("/uploadJson/**").addResourceLocations(jsonFile);

    }
}

