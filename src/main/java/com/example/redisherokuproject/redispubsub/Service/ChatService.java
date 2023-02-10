package com.example.redisherokuproject.redispubsub.Service;

import com.example.redisherokuproject.redispubsub.dto.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RedisTemplate redisTemplate;
    public void sendMessage(ChatMessageRequest chatMessageRequest) {
        System.out.println("챗 서비스@#@#!#@#!@!@");
        redisTemplate.convertAndSend("aaa", chatMessageRequest);
    }
}
