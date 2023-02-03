package com.example.redisherokuproject;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisService redisService;

    private final RedisPubService redisPubService;

//    @PostMapping("/test")
//    public void test(@RequestBody ChatMessage chatMessage) {
//        redisPubService.sendMessage(chatMessage);
//    }

    @PostMapping("/test2")
    public void test2() {
        System.out.println("controller2");
        redisPubService.sendMessage2();
    }

    @PostMapping("/test")
    public String test(@RequestBody RedisRequestDto requestDto) {
        String value = redisService.redisString(requestDto);
        return "벨류는 " + value;
    }
}