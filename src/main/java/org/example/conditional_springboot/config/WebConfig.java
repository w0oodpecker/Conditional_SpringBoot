package org.example.conditional_springboot.config;

import org.example.conditional_springboot.profile.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    @ConditionalOnProperty(name = "profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }


    @Bean
    @ConditionalOnProperty(name = "profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
