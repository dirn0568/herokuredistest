package com.example.redisherokuproject.redispubsub.Controller;


import com.example.redisherokuproject.redispubsub.Service.RedisPubService;
import com.example.redisherokuproject.redispubsub.dto.RedisRequestDto;
import com.example.redisherokuproject.redispubsub.Service.RedisService;
import com.example.redisherokuproject.redispubsub.dto.CoffeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class RedisController {

    private final RedisService redisService;

    private final RedisPubService redisPubService;

//    @PostMapping("/test")
//    public void test(@RequestBody ChatMessage chatMessage) {
//        redisPubService.sendMessage(chatMessage);
//    }

    @ResponseBody
    @PostMapping("/test")
    public String test(@RequestBody RedisRequestDto requestDto) {
        String value = redisService.redisString(requestDto);
        return "벨류는 " + value;
    }

    @ResponseBody
    @PostMapping("/test2")
    public void test2() {
        System.out.println("controller2");
        redisPubService.sendMessage2();
    }

    @ResponseBody
    @PostMapping("/test3")
    public void test(@RequestBody CoffeeDTO coffeeDTO) {
        System.out.println("test3");
        redisPubService.sendMessage3(coffeeDTO);
    }
}