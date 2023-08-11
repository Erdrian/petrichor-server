package com.petrichor.sincerity.service;

import com.petrichor.sincerity.annotation.SnowFlakeId;
import com.petrichor.sincerity.entity.SysRole;
import com.petrichor.sincerity.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService implements SysRoleMapper {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public SysRole getRoleById(Long id) {
        return sysRoleMapper.getRoleById(id);
    }

    @Override
    public SysRole getRoleByRoleName(String roleName) {
        return sysRoleMapper.getRoleByRoleName(roleName);
    }

    @Override
    public List<SysRole> getRoleList(SysRole sysRole) {
        return sysRoleMapper.getRoleList(sysRole);
    }

    @Override
    public List<SysRole> getRoleList() {
        return sysRoleMapper.getRoleList();
    }

    @SnowFlakeId
    @Override
    public Long insertRole(SysRole sysRole) {
        return sysRoleMapper.insertRole(sysRole);
    }

    @Override
    public int deleteRole(Long id, String updateBy) {
        return sysRoleMapper.deleteRole(id, updateBy);
    }

    @Override
    public int editRole(SysRole sysRole) {
        return sysRoleMapper.editRole(sysRole);
    }

    @Override
    public int roleLinkPermission(Long roleId, List<Long> permissionId) {
        roleUnLinkPermission(roleId);
        return sysRoleMapper.roleLinkPermission(roleId, permissionId);
    }

    @Override
    public int roleUnLinkPermission(Long roleId) {
        return sysRoleMapper.roleUnLinkPermission(roleId);
    }

    @Override
    public List<Long> getRolePermissions(Long roleId) {
        return sysRoleMapper.getRolePermissions(roleId);
    }
}
