package com.example.redisherokuproject.redispubsub.Service;

import com.example.redisherokuproject.redispubsub.dto.CoffeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisMessageDtoSubscriber implements MessageListener {
    private static List<CoffeeDTO> coffeeDTOS = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final RedisTemplate redisTemplate;
    //private final SimpMessageSendingOperations messagingTemplate; // stomp 같은걸 위한것?
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("알게쓴데 여기는 왜 안옴");
        try {
            System.out.println("RedisMessagedtoSubscriber");
            CoffeeDTO coffeeDTO = mapper.readValue(message.getBody(), CoffeeDTO.class);
            coffeeDTOS.add(coffeeDTO);

            System.out.println("DTO Message receive : " + coffeeDTO.getName());
            System.out.println("DTO Message receive : " + coffeeDTO.getPrice());
            System.out.println("Total Coffee : " + coffeeDTOS.size());

            //messagingTemplate.convertAndSend("/sub/chat/room/" + coffeeDTO.getTitle(), "메세지 보내깅");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
