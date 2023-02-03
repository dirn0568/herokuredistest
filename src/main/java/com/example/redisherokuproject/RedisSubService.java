package com.example.redisherokuproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubService implements MessageListener {
    public static List<String> messageList = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("여기로 오나");
        System.out.println("String Message received: " + message.toString());
        log.info("String Message received: " + message.toString());

        //        try {
//            ChatMessage chatMessage = mapper.readValue(message.getBody(), ChatMessage.class);
//            messageList.add(message.toString());
//
//            System.out.println("받은 메시지 = " + message.toString());
//            System.out.println("chatMessage.getSender() = " + chatMessage.getSender());
//            System.out.println("chatMessage.getContext() = " + chatMessage.getContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
