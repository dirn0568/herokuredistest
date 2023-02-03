package com.example.redisherokuproject.Controller;


import com.example.redisherokuproject.Service.RedisPubService;
import com.example.redisherokuproject.dto.RedisRequestDto;
import com.example.redisherokuproject.Service.RedisService;
import com.example.redisherokuproject.dto.CoffeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    ///////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/chat/room/{roomUUID}")
    public String chatRoom(Model model) {
        return "ChatRoom";
    }

    @GetMapping("/chatrooms")
    public String chatRooms(Model model) {
        return "ChatRoomList";
    }
}