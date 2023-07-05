package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.mapper.UserMapper;
import com.petrichor.sincerity.pojo.Result;
import com.petrichor.sincerity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/api/user")
    public Result<User> getUserById(long id, String name) {
        Result<User> result = new Result<>();
        User user = userMapper.getUserById(id);
        if (user != null) {
            result.setOk(true);
            result.setResult(user);
            result.setMsg("登录成功");
        } else {
            result.setOk(false);
            result.setMsg("用户名或者密码不正确");
        }
        result.setCode(200);
        return result;
    }
}
