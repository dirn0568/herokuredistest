package com.example.redisherokuproject.Controller;

import com.example.redisherokuproject.Service.RedisPublisher;
import com.example.redisherokuproject.Service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequestMapping("/pubsub")
@Controller
@RequiredArgsConstructor
public class PubSubController {
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;
    private Map<String, ChannelTopic> channels;

    @GetMapping("/chat/{roomName}")
    public String chatRoom(Model model) {
        return "ChatRoom";
    }

    @GetMapping("/topics")
    public String chatRooms(Model model) {
        return "ChatRoomList";
    }

    @PostConstruct
    public void init() {
        channels = new HashMap<>();
    }
    // 토픽 목록
    @ResponseBody
    @GetMapping("/topicss")
    public Set<String> getTopicAll() {
        return channels.keySet();
    }
    // 토픽 생성
    @ResponseBody
    @PutMapping("/topics/{name}")
    public String createTopic(@PathVariable String name) {
        System.out.println("아니 여기로 안와???");
        System.out.println(name);
        ChannelTopic channel = new ChannelTopic(name);
        redisMessageListener.addMessageListener(redisSubscriber, channel);
        channels.put(name, channel);
        return "success";
    }
    // 메시지 발행
    @ResponseBody
    @PostMapping("/topics/{name}")
    public void pushMessage(@PathVariable String name, @RequestParam String message) {
        ChannelTopic channel = channels.get(name);
        redisPublisher.publish(channel, message);
    }
    // 토픽 제거
    @ResponseBody
    @DeleteMapping("/topics/{name}")
    public void deleteTopic(@PathVariable String name) {
        ChannelTopic channel = channels.get(name);
        redisMessageListener.removeMessageListener(redisSubscriber, channel);
        channels.remove(name);
    }
}