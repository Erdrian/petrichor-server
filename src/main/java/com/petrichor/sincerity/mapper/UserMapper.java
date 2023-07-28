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

    Long insertUser(User user);

    int deleteUser(@Param("id") Long id, @Param("updateBy") String updateBy);

    int editUser(User user);
}
