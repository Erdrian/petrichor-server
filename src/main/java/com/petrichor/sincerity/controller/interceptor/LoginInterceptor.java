package com.petrichor.sincerity.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.util.NotNeedLogin;
import com.petrichor.sincerity.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    private boolean tokenIsValid(String token) {
        Object tokenValue = redisTemplate.opsForValue().get(token);
        if (tokenValue != null) {
            tokenUtil.resetTokenEXTime(token);
        }
        return tokenValue != null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod h) {
            boolean annotationPresent = h.getMethod().isAnnotationPresent(NotNeedLogin.class);
            if (annotationPresent) {
                return true;
            } else {
                String token = request.getHeader("X-Access-Token");
                if (tokenIsValid(token)) {
                    return true;
                } else {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    String s = mapper.writeValueAsString(CommonResult.unauthorized(null));
                    writer.write(s);
                    return false;
                }
            }
        } else {
            return true;
        }
    }
}
