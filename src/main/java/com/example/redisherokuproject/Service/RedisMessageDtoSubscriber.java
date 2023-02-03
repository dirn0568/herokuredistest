package com.example.redisherokuproject.Service;

import com.example.redisherokuproject.dto.CoffeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisMessageDtoSubscriber implements MessageListener {
    private static List<CoffeeDTO> coffeeDTOS = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("알게쓴데 여기는 왜 안옴");
        try {
            System.out.println("RedisMessagedtoSubscriber");
            CoffeeDTO coffeeDTO = mapper.readValue(message.getBody(), CoffeeDTO.class);
            coffeeDTOS.add(coffeeDTO);

            System.out.println("DTO Message receive : " + message.toString());
            System.out.println("Total Coffee : " + coffeeDTOS.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
