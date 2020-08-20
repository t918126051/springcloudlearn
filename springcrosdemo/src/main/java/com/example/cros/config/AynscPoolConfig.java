package com.example.cros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AynscPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setMaxPoolSize(200);
        poolTaskExecutor.setCorePoolSize(20);
        poolTaskExecutor.setQueueCapacity(25);
        poolTaskExecutor.setKeepAliveSeconds(200);
        poolTaskExecutor.setThreadNamePrefix("asyncThread");
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        poolTaskExecutor.setAwaitTerminationSeconds(60);

        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        poolTaskExecutor.initialize();
        return poolTaskExecutor;

    }
}
