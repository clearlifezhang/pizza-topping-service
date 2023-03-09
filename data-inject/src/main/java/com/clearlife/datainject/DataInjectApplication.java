package com.clearlife.datainject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootApplication
public class DataInjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataInjectApplication.class, args);
    }

}