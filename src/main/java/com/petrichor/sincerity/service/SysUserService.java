package com.petrichor.sincerity.service;

import com.petrichor.sincerity.annotation.SnowFlakeId;
import com.petrichor.sincerity.dto.SysUserList;
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
        System.out.println(sysUser);
        return sysUserMapper.insertUser(sysUser);
    }

    @Override
    public int deleteUser(Long id, String updateBy) {
        return sysUserMapper.deleteUser(id, updateBy);
    }

    @Override
    public int editUser(SysUser sysUser) {
        return sysUserMapper.editUser(sysUser);
    }

}
