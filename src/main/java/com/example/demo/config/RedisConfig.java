package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.host}")
    private String host;

    @Bean
    public Jedis getJedis() {
        Jedis  jedis = new Jedis(host,port);
        return jedis;
    }
}