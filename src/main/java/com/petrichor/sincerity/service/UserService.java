package com.petrichor.sincerity.service;

import com.petrichor.sincerity.dto.UserList;
import com.petrichor.sincerity.entity.User;
import com.petrichor.sincerity.mapper.UserMapper;
import com.petrichor.sincerity.util.SecurityUtils;
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
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public List<UserList> getUserList(UserList userList) {
        return userMapper.getUserList(userList);
    }

    @Override
    public Long insertUser(User user) {
        user.setPassword(SecurityUtils.digestPassword(user.getPassword()));
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUser(Long id, String updateBy) {
        return userMapper.deleteUser(id, updateBy);
    }

    @Override
    public int editUser(User user) {
        return userMapper.editUser(user);
    }

}
