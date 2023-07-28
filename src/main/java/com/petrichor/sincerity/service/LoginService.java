package com.petrichor.sincerity.service;

import com.petrichor.sincerity.entity.User;
import com.petrichor.sincerity.mapper.LoginMapper;
import com.petrichor.sincerity.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;

    public User login(String username, String password) {
        return loginMapper.login(username, SecurityUtils.digestPassword(password));
    }
}
