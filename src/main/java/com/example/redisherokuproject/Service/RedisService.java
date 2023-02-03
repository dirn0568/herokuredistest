package com.example.redisherokuproject.Service;

import com.example.redisherokuproject.dto.RedisRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public String redisString(RedisRequestDto requestDto) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(requestDto.getName(), requestDto.getValue());
        String redis = (String)operations.get(requestDto.getName());
        return redis;
    }
}
