package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    SysRole getRoleById(@Param("id") Long id);

    SysRole getRoleByRoleName(@Param("roleName") String roleName);

    List<SysRole> getRoleList(SysRole sysRole);

    Long insertRole(SysRole sysRole);

    int deleteRole(@Param("id") Long id, @Param("updateBy") String updateBy);

    int editRole(SysRole sysRole);
}
