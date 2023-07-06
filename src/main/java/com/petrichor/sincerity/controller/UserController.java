package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;


}
