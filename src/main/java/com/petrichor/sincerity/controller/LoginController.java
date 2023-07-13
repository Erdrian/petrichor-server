package com.petrichor.sincerity.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.dto.LoginParam;
import com.petrichor.sincerity.dto.LoginResult;
import com.petrichor.sincerity.dto.UserInfo;
import com.petrichor.sincerity.entity.User;
import com.petrichor.sincerity.service.LoginService;
import com.petrichor.sincerity.util.CreateCaptcha;
import com.petrichor.sincerity.util.CreateToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    CreateCaptcha createCaptcha;
    @Autowired
    DefaultKaptcha getCaptcha;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    LoginService userService;
    @Autowired
    CreateToken createToken;
    @Autowired
    ModelMapper modelMapper;

    //验证码
    @GetMapping("/getCaptcha")
    public CommonResult<String> getCaptcha(@RequestParam String captchaKey) throws IOException {
        if (captchaKey == null || captchaKey.isEmpty()) return CommonResult.failed("未提供CaptchaKey");
        String base64;
        try {
            String text = getCaptcha.createText();
            base64 = createCaptcha.getCaptchaBase64(text);
            stringRedisTemplate.opsForValue().set(captchaKey, text, 60, TimeUnit.SECONDS);
        } catch (IllegalArgumentException | IOException e) {
            return CommonResult.failed("验证码生成失败，请重试");
        }
        return CommonResult.success(base64);
    }

    //登录
    @PostMapping()
    public CommonResult<LoginResult> login(@RequestBody LoginParam loginParam) {
        String captcha = loginParam.getCaptcha();
        String captchaKey = loginParam.getCaptchaKey();
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (!Objects.equals(stringRedisTemplate.opsForValue().get(captchaKey), captcha)) {
            return CommonResult.failed("验证码错误");
        }
        User user = userService.login(username, password);
        if (user == null) {
            return CommonResult.failed("账号或者密码错误");
        }
        UserInfo userInfo = modelMapper.map(user, UserInfo.class);
        String token = createToken.getToken(user);
        LoginResult loginResult = new LoginResult();
        loginResult.setUserInfo(userInfo);
        loginResult.setToken(token);
        return CommonResult.success(loginResult);
    }
}
