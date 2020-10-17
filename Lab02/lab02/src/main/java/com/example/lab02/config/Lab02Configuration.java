package com.example.lab02.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({EventProperties.class,CacheProperties.class})
public class Lab02Configuration {
}
