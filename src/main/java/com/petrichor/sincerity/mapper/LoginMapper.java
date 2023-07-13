package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.entity.User;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    User login(@Param("username") String username, @Param("password") String password);
}
