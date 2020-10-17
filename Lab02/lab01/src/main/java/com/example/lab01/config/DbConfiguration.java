package com.example.lab01.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DbConfiguration {

    private final DbProperties properties;

    public DbConfiguration(DbProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(properties.getUsername())
                .password(properties.getPassword())
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .build();
    }


}
