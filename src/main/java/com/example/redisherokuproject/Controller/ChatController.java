package com.example.redisherokuproject.Controller;

import com.example.redisherokuproject.Service.ChatMessageService;
import com.example.redisherokuproject.dto.CoffeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;
    @MessageMapping("/chat/message")
    public void message(CoffeeDTO coffeeDTO) {
        chatMessageService.sendMessage(coffeeDTO);
    }
}
