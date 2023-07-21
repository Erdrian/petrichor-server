package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.dto.UserList;
import com.petrichor.sincerity.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUserById(@Param("id") Long id);

    List<UserList> getUserList(UserList userList);
}
