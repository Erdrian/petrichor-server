package com.petrichor.sincerity.controller.interceptor;

import com.petrichor.sincerity.annotation.NeedAuthority;
import com.petrichor.sincerity.dto.LoginResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;

@Order(2)
@Component
public class AuthorizeInterceptor extends BaseInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod h) {
            boolean annotationPresent = h.getMethod().isAnnotationPresent(NeedAuthority.class);
            if (!annotationPresent) {
                return true;
            } else {
                String token = request.getHeader("X-Access-Token");
                String authority = Objects.requireNonNull(h.getMethodAnnotation(NeedAuthority.class)).value();
                if (hasAuthority(token, authority)) {
                    return true;
                } else {
                    forbidden(response);
                    return false;
                }

            }
        } else {
            return true;
        }
    }

    public boolean hasAuthority(String token, String authority) {
        LoginResult loginResult = (LoginResult) tokenUtil.get(token);
        return loginResult.getAuthorizes().contains(authority);
    }
}