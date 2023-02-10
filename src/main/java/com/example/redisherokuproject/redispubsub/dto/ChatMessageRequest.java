package com.example.redisherokuproject.redispubsub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequest {
    private String RoomId;
    private String message;
}
