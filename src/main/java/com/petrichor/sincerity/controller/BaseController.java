package com.petrichor.sincerity.controller;


import com.github.pagehelper.PageHelper;
import com.petrichor.sincerity.model.LoginResult;
import com.petrichor.sincerity.util.Convert;
import com.petrichor.sincerity.util.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class BaseController {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    protected void startPage() {
        HttpServletRequest request = ServletUtils.getRequest();
        int page = Convert.toInt(request.getParameter("page"), 1);
        int pageSize = Convert.toInt(request.getParameter("pageSize"), 10);
        PageHelper.startPage(page, pageSize);
    }

    public String getUserName() {
        return getLoginResult().getUserInfo().getUsername();
    }


    public LoginResult getLoginResult() {
        String token = ServletUtils.getRequest().getHeader("X-Access-Token");
        return (LoginResult) redisTemplate.opsForValue().get(token);
    }

}