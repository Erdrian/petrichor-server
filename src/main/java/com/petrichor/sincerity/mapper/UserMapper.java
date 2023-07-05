package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserById(@Param("id") long id);
}
