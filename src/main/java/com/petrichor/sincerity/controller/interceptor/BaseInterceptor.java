package com.petrichor.sincerity.controller.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.util.ServletUtils;
import com.petrichor.sincerity.util.StringUtils;
import com.petrichor.sincerity.util.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.jdbc.Null;
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


    public boolean unauthorized() throws IOException {
        write(CommonResult.unauthorized(null));
        return false;
    }

    public boolean forbidden() throws IOException {
        write(CommonResult.forbidden(null));
        return false;
    }

    public PrintWriter getWriter() throws IOException {
        HttpServletResponse response = ServletUtils.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        return response.getWriter();
    }

    public void write(CommonResult<Null> result) throws IOException {
        String s = mapper.writeValueAsString(result);
        getWriter().write(s);
    }

    public String getToken() {
        String token = ServletUtils.getRequest().getHeader("X-Access-Token");
        return StringUtils.isEmpty(token) ? "" : token;
    }
}
