package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonPage;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.dto.SysUserList;
import com.petrichor.sincerity.entity.SysUser;
import com.petrichor.sincerity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SysUserController extends BaseController {
    @Autowired
    SysUserService sysUserService;


    @GetMapping("/list")
    public CommonResult<CommonPage<SysUserList>> getUserList(SysUserList param) {
        startPage();
        List<SysUserList> sysUserList = sysUserService.getUserList(param);
        return CommonResult.success(CommonPage.restPage(sysUserList));
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
        return CommonResult.success(sysUser.getId());
    }

    @PostMapping("/delete/{id}")
    public CommonResult<String> deleteUser(@PathVariable Long id) {
        sysUserService.deleteUser(id, getUserName());
        return CommonResult.success("删除成功");
    }

    @PostMapping("/edit")
    public CommonResult<String> editUser(@RequestBody SysUser sysUser) {
        sysUser.setUpdateBy(getUserName());
        sysUserService.editUser(sysUser);
        return CommonResult.success("修改成功");
    }
}
