package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    SysUser login(@Param("userName") String userName, @Param("password") String password);
}
