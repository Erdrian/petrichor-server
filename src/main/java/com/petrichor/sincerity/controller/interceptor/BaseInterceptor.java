package com.petrichor.sincerity.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.util.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

public class BaseInterceptor implements HandlerInterceptor {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public void unauthorized(HttpServletResponse response) throws IOException {
        String s = mapper.writeValueAsString(CommonResult.unauthorized(null));
        getWriter(response).write(s);
    }

    public void forbidden(HttpServletResponse response) throws IOException {
        String s = mapper.writeValueAsString(CommonResult.forbidden(null));
        getWriter(response).write(s);
    }

    public PrintWriter getWriter(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        return response.getWriter();
    }
}
