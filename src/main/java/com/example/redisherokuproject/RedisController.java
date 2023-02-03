package com.example.redisherokuproject;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisService redisService;

//    @PostMapping("/test")
//    public void test(@RequestBody ChatMessage chatMessage) {
//        redisPubService.sendMessage(chatMessage);
//    }
    @PostMapping("/test")
    public String test(@RequestBody RedisRequestDto requestDto) {
        String value = redisService.redisString(requestDto);
        return "벨류는 " + value;
    }
}