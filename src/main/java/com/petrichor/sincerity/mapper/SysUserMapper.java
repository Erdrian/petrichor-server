package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.vo.SysUserList;
import com.petrichor.sincerity.vo.SysUserRole;
import com.petrichor.sincerity.entity.SysPermission;
import com.petrichor.sincerity.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    SysUser getUserById(@Param("id") Long id);

    SysUser getUserByUserName(@Param("userName") String userName);

    SysUser getUserByPhone(@Param("phone") String phone);

    List<SysUserList> getUserList(SysUserList sysUserList);

    int insertUser(SysUser sysUser);

    int deleteUser(@Param("id") Long id, @Param("updateBy") String updateBy);

    int editUser(SysUser sysUser);

    int userLinkRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    int userUnlinkRoles(@Param("userId") Long userId);

    List<SysPermission> getUserPermissionsByUserId(@Param("userId") Long userId);

    List<SysUserRole> getUserRoleIdsByUserIds(@Param("userIds") List<Long> userIds);
}
