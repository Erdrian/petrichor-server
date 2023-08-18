package com.petrichor.sincerity.service;

import com.petrichor.sincerity.annotation.SnowFlakeId;
import com.petrichor.sincerity.model.SysUserList;
import com.petrichor.sincerity.model.SysUserRole;
import com.petrichor.sincerity.entity.SysPermission;
import com.petrichor.sincerity.entity.SysUser;
import com.petrichor.sincerity.mapper.SysUserMapper;
import com.petrichor.sincerity.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysUserService implements SysUserMapper {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserById(Long id) {
        return sysUserMapper.getUserById(id);
    }

    @Override
    public SysUser getUserByUserName(String userName) {
        return sysUserMapper.getUserByUserName(userName);
    }

    @Override
    public SysUser getUserByPhone(String phone) {
        return sysUserMapper.getUserByPhone(phone);
    }

    @Override
    public List<SysUserList> getUserList(SysUserList sysUserList) {
        return sysUserMapper.getUserList(sysUserList);
    }

    @SnowFlakeId
    @Override
    public Long insertUser(SysUser sysUser) {
        sysUser.setPassword(SecurityUtils.digestPassword(sysUser.getPassword()));
        userLinkRoles(sysUser.getId(), sysUser.getRoleIds());
        return sysUserMapper.insertUser(sysUser);
    }

    @Override
    public int deleteUser(Long id, String updateBy) {
        userUnlinkRoles(id);
        return sysUserMapper.deleteUser(id, updateBy);
    }

    @Override
    public int editUser(SysUser sysUser) {
        userLinkRoles(sysUser.getId(), sysUser.getRoleIds());
        return sysUserMapper.editUser(sysUser);
    }

    @Override
    public int userLinkRoles(Long userId, List<Long> roleIds) {
        userUnlinkRoles(userId);
        return sysUserMapper.userLinkRoles(userId, roleIds);
    }

    @Override
    public int userUnlinkRoles(Long userId) {
        return sysUserMapper.userUnlinkRoles(userId);
    }

    @Override
    public List<SysPermission> getUserPermissionsByUserId(Long userId) {
        return sysUserMapper.getUserPermissionsByUserId(userId);
    }

    @Override
    public List<SysUserRole> getUserRoleIdsByUserIds(List<Long> userIds) {
        return sysUserMapper.getUserRoleIdsByUserIds(userIds);
    }

}
