package com.petrichor.sincerity.controller;

import com.github.pagehelper.page.PageMethod;
import com.petrichor.sincerity.api.CommonPage;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.api.ResultCode;
import com.petrichor.sincerity.dto.UserList;
import com.petrichor.sincerity.mapper.UserMapper;
import com.petrichor.sincerity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


    @GetMapping("/list")
    public CommonResult<CommonPage<UserList>> getUserList(UserList param) {

        PageMethod.startPage(1, 10);
        System.out.println(param.toString());
        List<UserList> userList = userMapper.getUserList(param);

        return CommonResult.success(CommonPage.restPage(userList));
//        return CommonResult.failed(ResultCode.FAILED);
    }

}
