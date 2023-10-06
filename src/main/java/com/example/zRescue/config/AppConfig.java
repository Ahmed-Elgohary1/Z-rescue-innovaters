package com.example.zRescue.config;

import com.example.zRescue.service.MessageAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MessageAdapter messageAdapter() {
        return MessageAdapter.getInstance();
    }

    // Other bean definitions or configuration settings

}