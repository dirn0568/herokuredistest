package com.example.redisherokuproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisHerokuProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisHerokuProjectApplication.class, args);

    }

}
