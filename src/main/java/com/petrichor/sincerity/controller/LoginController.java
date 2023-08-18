package com.petrichor.sincerity.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.model.LoginBody;
import com.petrichor.sincerity.model.LoginResult;
import com.petrichor.sincerity.entity.SysPermission;
import com.petrichor.sincerity.entity.SysUser;
import com.petrichor.sincerity.service.LoginService;
import com.petrichor.sincerity.service.SysPermissionService;
import com.petrichor.sincerity.service.SysUserService;
import com.petrichor.sincerity.util.CaptchaUtil;
import com.petrichor.sincerity.annotation.NotNeedLogin;
import com.petrichor.sincerity.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    CaptchaUtil captchaUtil;
    @Autowired
    DefaultKaptcha getCaptcha;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    LoginService loginService;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysPermissionService sysPermissionService;

    //验证码
    @GetMapping("getCaptcha")
    @NotNeedLogin
    public CommonResult<String> getCaptcha(@RequestParam String captchaKey) throws IOException {
        if (captchaKey == null || captchaKey.isEmpty()) return CommonResult.failed("未提供CaptchaKey");
        String base64;
        try {
            String text = getCaptcha.createText();
            base64 = captchaUtil.getCaptchaBase64(text);
            redisTemplate.opsForValue().set(captchaKey, text, 60, TimeUnit.SECONDS);
        } catch (IllegalArgumentException | IOException e) {
            return CommonResult.failed("验证码生成失败，请重试");
        }
        return CommonResult.success(base64);
    }

    //登录
    @NotNeedLogin
    @PostMapping
    public CommonResult<LoginResult> login(@RequestBody LoginBody loginBody, HttpServletRequest request) {
        String captcha = loginBody.getCaptcha();
        String captchaKey = loginBody.getCaptchaKey();
        String userName = loginBody.getUserName();
        String password = loginBody.getPassword();
        if (!Objects.equals(redisTemplate.opsForValue().get(captchaKey), captcha)) {
            return CommonResult.failed("验证码错误");
        }
        SysUser sysUser = loginService.login(userName, password);
        if (sysUser == null) {
            return CommonResult.failed("账号或者密码错误");
        }
        String existToken = request.getHeader("X-Access-Token");
        if (!existToken.isEmpty()) {
            redisTemplate.delete(existToken);
        }
        return CommonResult.success(getLoginResult(sysUser));
    }

    public List<String> getAuthorizes(List<SysPermission> sysPermissions) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (SysPermission sysPermission : sysPermissions) {
            String authority = sysPermission.getAuthority();
            if (authority!=null && !authority.isEmpty()) {
                arrayList.add(authority);
            }
        }
        return arrayList;
    }

    public List<SysPermission> getNavigation(List<SysPermission> sysPermissions) {
        List<SysPermission> permissions = sysPermissions.stream().filter(SysPermission::isNavigation).toList();
        return sysPermissionService.getPermissionTree(permissions);
    }

    public LoginResult getLoginResult(SysUser sysUser) {
        List<SysPermission> userPermissions = sysUserService.getUserPermissionsByUserId(sysUser.getId());
        LoginResult.UserInfo userInfo = modelMapper.map(sysUser, LoginResult.UserInfo.class);
        LoginResult loginResult = new LoginResult();
        loginResult.setUserInfo(userInfo);
        loginResult.setAuthorizes(getAuthorizes(userPermissions));
        loginResult.setNavigation(getNavigation(userPermissions));
        String token = tokenUtil.getToken(loginResult);
        loginResult.setToken(token);
        return loginResult;
    }
}
