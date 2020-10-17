package com.example.lab02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final CacheProperties properties;

    public CacheConfiguration(CacheProperties properties) {
        this.properties = properties;
    }


    @Bean
    public LettuceConnectionFactory createConnectionFactory() {
        LettuceConnectionFactory connectionFactory;
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();

        if (properties.getEnableSsl()) {
            builder.useSsl().disablePeerVerification();
        }

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
        configuration.setPassword(RedisPassword.of(properties.getPassword()));
        connectionFactory = new LettuceConnectionFactory(configuration, builder.build());

        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }



}
