package com.petrichor.sincerity.mapper;

import com.petrichor.sincerity.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    int insertPermission(SysPermission sysPermission);

    List<SysPermission> getAllPermissions(SysPermission sysPermission);

    int updatePermission(SysPermission sysPermission);

    int deletePermission(Long id, String updateBy);
}

