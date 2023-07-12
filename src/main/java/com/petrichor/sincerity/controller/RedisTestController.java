package com.petrichor.sincerity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisTestController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping("/redis")
    public Long redis() {
        redisTemplate.delete("list");
        return redisTemplate.opsForValue().increment("test");
    }
}
