package com.petrichor.sincerity.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.util.CreateCaptcha;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    CreateCaptcha createCaptcha;
    @Autowired
    DefaultKaptcha getCaptcha;
    @Autowired
    StringRedisTemplate redisTemplate;

    //验证码
    @GetMapping("/getCaptcha")
    public CommonResult<String> getCaptcha(@RequestParam String captchaKey) throws IOException {
        if (captchaKey == null || captchaKey.isEmpty()) return CommonResult.failed("未提供CaptchaKey");
        String base64;
        try {
            String text = getCaptcha.createText();
            base64 = createCaptcha.getCaptchaBase64(text);
            redisTemplate.opsForValue().set(captchaKey, text, 60, TimeUnit.SECONDS);
        } catch (IllegalArgumentException | IOException e) {
            return CommonResult.failed("验证码生成失败，请重试");
        }
        return CommonResult.success(base64);
    }
}
