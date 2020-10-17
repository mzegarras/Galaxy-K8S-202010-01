package com.example.lab01.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//We use @Configuration so that Spring creates a Spring bean in the application context.
@Configuration
@EnableConfigurationProperties({Lab01Properties.class, DbProperties.class,EventProperties.class})
public class LabConfiguration {
}
