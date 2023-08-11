package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.annotation.NeedAuthority;
import com.petrichor.sincerity.api.CommonPage;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.dto.SysUserList;
import com.petrichor.sincerity.dto.SysUserRole;
import com.petrichor.sincerity.entity.SysRole;
import com.petrichor.sincerity.entity.SysUser;
import com.petrichor.sincerity.service.SysRoleService;
import com.petrichor.sincerity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class SysUserController extends BaseController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService sysRoleService;

    @NeedAuthority("permission:user-list")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysUserList>> getUserList(SysUserList param) {
        startPage();
        List<SysUserList> sysUserList = sysUserService.getUserList(param);
        return CommonPage.restPage(setUserRoles(sysUserList));
    }

    @PostMapping("/add")
    public CommonResult<Long> insertUser(@RequestBody SysUser sysUser) {
        if (sysUserService.getUserByUserName(sysUser.getUserName()) != null) {
            return CommonResult.failed("用户名已存在");
        }
        if (sysUserService.getUserByPhone(sysUser.getPhone()) != null) {
            return CommonResult.failed("手机号已存在");
        }
        sysUser.setCreateBy(getUserName());
        sysUserService.insertUser(sysUser);
        Long id = sysUser.getId();
        sysUserService.userLinkRoles(id, sysUser.getRoleIds());
        return CommonResult.success(id);
    }

    @PostMapping("/delete/{id}")
    public CommonResult<String> deleteUser(@PathVariable Long id) {
        if (id == null) {
            return CommonResult.failed("删除对象不存在");
        }
        sysUserService.deleteUser(id, getUserName());
        return CommonResult.success("删除成功");
    }

    @PostMapping("/edit")
    public CommonResult<String> editUser(@RequestBody SysUser sysUser) {
        sysUser.setUpdateBy(getUserName());
        sysUserService.editUser(sysUser);
        return CommonResult.success("修改成功");
    }

    public SysRole getRoleById(List<SysRole> roleList, Long id) {
        return roleList.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    public List<SysUserList> setUserRoles(List<SysUserList> userList) {
        List<SysRole> roleList = sysRoleService.getRoleList();

        List<Long> userIds = userList.stream().map(SysUserList::getId).toList();

        //获取userId-roleId 数组
        List<SysUserRole> userRoleIds = sysUserService.getUserRoleIdsByUserIds(userIds);

        userList.forEach(u -> {
            Long userId = u.getId();
            List<SysRole> roles = userRoleIds.stream()
                    .filter(r -> r.getUserId().equals(userId))
                    .map(r -> getRoleById(roleList, r.getRoleId()))
                    .filter(Objects::nonNull)
                    .toList();
            u.setRoles(roles);
        });
        return userList;
    }
}
