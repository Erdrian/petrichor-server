package com.petrichor.sincerity.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
@ConfigurationProperties(prefix = "petrichor.token")
@Data
public class TokenUtil {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    private long ex;


    public <T> String getToken(T data) {
        String uuid = UUID.randomUUID().toString();
        String token = Base64.getEncoder().encodeToString(uuid.getBytes());
        redisTemplate.opsForValue().set(token, data, this.ex, TimeUnit.SECONDS);
        return token;
    }

    public void resetTokenEXTime(String key) {
        redisTemplate.opsForValue().getAndExpire(key, this.ex, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
