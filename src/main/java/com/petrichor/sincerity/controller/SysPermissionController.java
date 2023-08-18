package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.annotation.NeedAuthority;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.entity.SysPermission;
import com.petrichor.sincerity.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission")
public class SysPermissionController extends BaseController {
    @Autowired
    SysPermissionService sysPermissionService;

    @PostMapping("add")
    public CommonResult<Long> addPermission(@RequestBody SysPermission sysPermission) {
        sysPermission.setCreateBy(getUserName());
        sysPermissionService.insertPermission(sysPermission);
        Long id = sysPermission.getId();
        return CommonResult.success(id);
    }

    @NeedAuthority("permission:permission-list")
    @GetMapping("allPermissions")
    public CommonResult<List<SysPermission>> getPermissionList(@RequestParam(required = false) SysPermission sysPermission) {
        List<SysPermission> allPermissions = sysPermissionService.getAllPermissions(sysPermission);
        return CommonResult.success(sysPermissionService.getPermissionTree(allPermissions));
    }

    @RequestMapping("edit")
    public CommonResult<String> updatePermission(@RequestBody SysPermission sysPermission) {
        Long id = sysPermission.getId();
        if (id == null) {
            return CommonResult.failed("编辑对象不存在");
        }
        sysPermission.setUpdateBy(getUserName());
        sysPermissionService.updatePermission(sysPermission);
        return CommonResult.success("修改成功");
    }

    @RequestMapping("delete/{id}")
    public CommonResult<String> deletePermission(@PathVariable("id") Long id, String updateBy) {
        if (id == null) {
            return CommonResult.failed("删除对象不存在");
        }
        sysPermissionService.deletePermission(id, updateBy);
        return CommonResult.success("删除成功");
    }
}
