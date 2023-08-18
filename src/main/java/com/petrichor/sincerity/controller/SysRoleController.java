package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.annotation.NeedAuthority;
import com.petrichor.sincerity.api.CommonPage;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.model.RoleLinkPermissionBody;
import com.petrichor.sincerity.entity.SysRole;
import com.petrichor.sincerity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class SysRoleController extends BaseController {
    @Autowired
    SysRoleService sysRoleService;

    @NeedAuthority("permission:role-list")
    @GetMapping("list")
    public CommonResult<CommonPage<SysRole>> getRoleList(SysRole param) {
        startPage();
        List<SysRole> sysRoleList = sysRoleService.getRoleList(param);
        return CommonPage.restPage(sysRoleList);
    }

    @PostMapping("add")
    public CommonResult<Long> insertRole(@RequestBody SysRole sysRole) {
        if (sysRoleService.getRoleByRoleName(sysRole.getRoleName()) != null) {
            return CommonResult.failed("角色已存在");
        }
        sysRole.setCreateBy(getUserName());
        sysRoleService.insertRole(sysRole);
        return CommonResult.success(sysRole.getId());
    }

    @PostMapping("delete/{id}")
    public CommonResult<String> deleteRole(@PathVariable Long id) {
        sysRoleService.deleteRole(id, getUserName());
        return CommonResult.success("删除成功");
    }

    @PostMapping("edit")
    public CommonResult<String> editUser(@RequestBody SysRole sysRole) {
        sysRole.setUpdateBy(getUserName());
        sysRoleService.editRole(sysRole);
        return CommonResult.success("修改成功");
    }

    @PostMapping("roleLinkPermission")
    public CommonResult<String> roleLinkPermission(@RequestBody RoleLinkPermissionBody roleLinkPermissionBody) {
        sysRoleService.roleLinkPermission(roleLinkPermissionBody.getRoleId(), roleLinkPermissionBody.getPermissionIds());
        return CommonResult.success("关联成功");
    }

    @GetMapping("getRolePermissions")
    public CommonResult<List<Long>> getRolePermissions(@RequestParam("roleId") Long roleId) {
        List<Long> rolePermissions = sysRoleService.getRolePermissions(roleId);
        return CommonResult.success(rolePermissions);
    }
}
