package com.petrichor.sincerity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CreateToken {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public <T> String getToken(T data) {
        String uuid = UUID.randomUUID().toString();
        String token = Base64.getEncoder().encodeToString(uuid.getBytes());
        long EX = 3600;
        redisTemplate.opsForValue().set(token, data, EX, TimeUnit.SECONDS);
        return token;
    }
}
