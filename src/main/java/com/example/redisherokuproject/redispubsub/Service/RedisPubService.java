package com.example.redisherokuproject.redispubsub.Service;

import com.example.redisherokuproject.redispubsub.dto.ChatMessage;
import com.example.redisherokuproject.redispubsub.dto.CoffeeDTO;
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

    public void sendMessage3(CoffeeDTO coffeeDTO) {
        System.out.println("test3, service");
        redisTemplate.convertAndSend("ch02", coffeeDTO);
    }
}
