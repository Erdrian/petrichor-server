package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonPage;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.dto.UserList;
import com.petrichor.sincerity.entity.User;
import com.petrichor.sincerity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;


    @GetMapping("/list")
    public CommonResult<CommonPage<UserList>> getUserList(UserList param) {
        startPage();
        List<UserList> userList = userService.getUserList(param);
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @PostMapping("/add")
    public CommonResult<Long> insertUser(@RequestBody User user) {
        if (userService.getUserByUserName(user.getUserName()) != null) {
            return CommonResult.failed("用户名已存在");
        }
        if (userService.getUserByPhone(user.getPhone()) != null) {
            return CommonResult.failed("手机号已存在");
        }
        user.setCreateBy(getUserName());
        userService.insertUser(user);
        return CommonResult.success(user.getId());
    }

    @PostMapping("/delete/{id}")
    public CommonResult<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id, getUserName());
        return CommonResult.success("删除成功");
    }

    @PostMapping("/edit")
    public CommonResult<String> editUser(@RequestBody User user) {
        user.setUpdateBy(getUserName());
        userService.editUser(user);
        return CommonResult.success("修改成功");
    }
}
