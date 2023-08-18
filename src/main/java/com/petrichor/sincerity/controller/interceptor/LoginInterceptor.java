package com.petrichor.sincerity.controller.interceptor;

import com.petrichor.sincerity.annotation.NotNeedLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Order(1)
@Component
public class LoginInterceptor extends BaseInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod h) {
            boolean annotationPresent = h.getMethod().isAnnotationPresent(NotNeedLogin.class);
            return annotationPresent || tokenIsValid(getToken()) || unauthorized();
        }
        return true;
    }

    private boolean tokenIsValid(String token) {
        Object tokenValue = redisTemplate.opsForValue().get(token);
        if (tokenValue != null) {
            tokenUtil.resetTokenEXTime(token);
        }
        return tokenValue != null;
    }
}
