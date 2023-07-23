package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.dto.UserList;
import com.petrichor.sincerity.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUserById(@Param("id") Long id);

    User getUserByUserName(@Param("username") String username);

    User getUserByPhone(@Param("phone") String phone);

    List<UserList> getUserList(UserList userList);

    Long addUser(User user);
}
