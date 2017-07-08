package com.ibm.watsonwork;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class ApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("WebhookProcess-");
        executor.initialize();
        return executor;
    }
}
