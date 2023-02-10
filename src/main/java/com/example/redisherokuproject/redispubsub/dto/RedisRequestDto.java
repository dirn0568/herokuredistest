package com.example.redisherokuproject.redispubsub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RedisRequestDto {
    private String name;
    private String value;
}
