package com.petrichor.sincerity.service;

import com.petrichor.sincerity.dto.UserList;
import com.petrichor.sincerity.entity.User;
import com.petrichor.sincerity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserService implements UserMapper {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<UserList> getUserList(UserList userList) {
        return userMapper.getUserList(userList);
    }
}