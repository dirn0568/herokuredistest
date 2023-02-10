package com.example.redisherokuproject.redispubsub.Service;

import com.example.redisherokuproject.redispubsub.dto.CoffeeDTO;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
    public void sendMessage(CoffeeDTO coffeeDTO) {
        System.out.println("그저 오는지 확인중");
        System.out.println(coffeeDTO.getName());
        System.out.println(coffeeDTO.getTitle());
    }
}
