package com.example.redisherokuproject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubService {
    private final RedisTemplate<String, Object> redisTemplate;
    public void sendMessage(ChatMessage chatMessage) {
        System.out.println("챗메세지2 : " + chatMessage);
        redisTemplate.convertAndSend("topic1", chatMessage);
    }

    public void sendMessage2() {
        redisTemplate.convertAndSend("ch01", "Coffee, latte");
    }
}
