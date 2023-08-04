package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.dto.SysUserList;
import com.petrichor.sincerity.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    SysUser getUserById(@Param("id") Long id);

    SysUser getUserByUserName(@Param("userName") String userName);

    SysUser getUserByPhone(@Param("phone") String phone);

    List<SysUserList> getUserList(SysUserList sysUserList);

    Long insertUser(SysUser sysUser);

    int deleteUser(@Param("id") Long id, @Param("updateBy") String updateBy);

    int editUser(SysUser sysUser);
}
