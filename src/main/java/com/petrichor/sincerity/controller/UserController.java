package com.petrichor.sincerity.controller;

import com.github.pagehelper.page.PageMethod;
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
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/list")
    public CommonResult<CommonPage<UserList>> getUserList(UserList param) {
//        SecurityContextHolder
        PageMethod.startPage(1, 10);
        List<UserList> userList = userService.getUserList(param);
        System.out.println(userList.size());
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @PostMapping("/add")
    public CommonResult<String> addUser(User user) {
        User userByUserName = userService.getUserByUserName(user.getUsername());
        if(userByUserName !=null){
            return  CommonResult.failed("用户名已存在");
        }
        User userByPhone = userService.getUserByPhone(user.getPhone());
        if(userByPhone !=null){
            return  CommonResult.failed("手机号已存在");
        }
        return CommonResult.success("成功");
    }

}
