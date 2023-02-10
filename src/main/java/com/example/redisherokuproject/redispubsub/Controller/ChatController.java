package com.example.redisherokuproject.redispubsub.Controller;

import com.example.redisherokuproject.redispubsub.Service.ChatService;
import com.example.redisherokuproject.redispubsub.dto.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageRequest chatMessageRequest) {
        System.out.println("여기로 오나 메세지맵핑!!!!!");
        chatService.sendMessage(chatMessageRequest);
    }
}
