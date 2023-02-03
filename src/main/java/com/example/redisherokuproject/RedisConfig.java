package com.example.redisherokuproject;


import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
//    @Value("${spring.redis.password}")
//    private String password;
    @Value("${spring.redis.host}")
    private String host;

//    @Value("${spring.redis.port}")
//    private int port;

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword(password);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }

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

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        System.out.println(host);
//        System.out.println(port);
//        return new LettuceConnectionFactory(host, port);
//    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
}