package com.example.redisherokuproject.config;


import com.example.redisherokuproject.Service.RedisMessageDtoSubscriber;
import com.example.redisherokuproject.Service.RedisSubService;
import com.example.redisherokuproject.dto.ChatMessage;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import javax.annotation.PostConstruct;

@Configuration
public class RedisConfig {
//    @Value("${spring.redis.password}")
//    private String password;
    @Value("${spring.redis.host}")
    private String host;

//    @Value("${spring.redis.port}")
//    private int port;


    @Bean //ConnectionFactory 빈으로 등록
    public RedisConnectionFactory redisConnectionFactory() {

        //RedisProperties 에서 application.properties에서 설정한 URL을 가져온다
        RedisURI redisURI = RedisURI.create(host);

        // URI를 가지고 Configuration을 만들어 준 다음에
        // Connection을 만들어주는 factory를 만들어 factory를 반환한다
        org.springframework.data.redis.connection.RedisConfiguration configuration = LettuceConnectionFactory.createRedisConfiguration(redisURI);

        //Lettuce는 전통적인 제디스보다 나중에 나왔고 성능이 더 좋아서 사용한다
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);

        // Initializing 된다
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        return container;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));

        return redisTemplate;
    }

//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer( // (1)
//                                                                        RedisConnectionFactory connectionFactory,
//                                                                        MessageListenerAdapter listenerAdapter,
//                                                                        ChannelTopic channelTopic
//    ) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, channelTopic);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter listenerAdapter(RedisMessageDtoSubscriber subscriber) { // (2)
//        return new MessageListenerAdapter(subscriber, "onMessage");
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate
//            (RedisConnectionFactory connectionFactory) { // (3)
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
//        return redisTemplate;
//    }
//
//    @Bean
//    public ChannelTopic channelTopic() { // (4)
//        return new ChannelTopic("chatroom");
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatMessage.class)); // Jackson2JsonRedisSerializer<>(ChatMessage.class) 이거를 통해서 dto를 변환시켜서 저장시킬수있음
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
//
//
//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
//                                                                        MessageListenerAdapter messageListenerAdapter) {
//        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
//        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
//        redisMessageListenerContainer.addMessageListener(messageStringListener(), topic01());
//        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, topic02());
//
//        return redisMessageListenerContainer;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageStringListener() {
//        return new MessageListenerAdapter(new RedisSubService());
//    }
//
//    @Bean
//    public MessageListenerAdapter messageDtoListener(RedisMessageDtoSubscriber subscriber) {
//        return new MessageListenerAdapter(subscriber);
//    }
//
//    @Bean
//    public ChannelTopic topic01() {
//        return new ChannelTopic("ch01");
//    }
//
//    @Bean
//    public ChannelTopic topic02() {
//        return new ChannelTopic("ch02");
//    }
}

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword(password);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        System.out.println(host);
//        System.out.println(port);
//        return new LettuceConnectionFactory(host, port);
//    }

//    @Bean
//    public StringRedisTemplate stringRedisTemplate() { // 이건 뭐지?? 필요한건가??
//        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
//        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
//        return stringRedisTemplate;
//    }