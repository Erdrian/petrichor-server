package com.petrichor.sincerity.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.dto.Captcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @Autowired
    DefaultKaptcha getCaptcha;

    @GetMapping("/api/getCaptcha")
    public CommonResult<String> getCaptcha(HttpServletRequest req, HttpServletResponse res) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = getCaptcha.createText();
            req.getSession().setAttribute("verificationCode", createText);
            // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = getCaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException | IOException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        CommonResult<Captcha> failed = CommonResult.failed();
        return CommonResult.success("data:image/png;base64," + new String(new Base64().encode(jpegOutputStream.toByteArray())));
//        接口返回图片的写法
//        res.setHeader("Cache-Control", "no-store");
//        res.setHeader("Pragma", "no-cache");
//        res.setDateHeader("Expires", 0);
//        res.setContentType("data:image/jpeg;base64");
//        ServletOutputStream resOutputStream = res.getOutputStream();
//        resOutputStream.write(captchaChallengeAsJpeg);
//        resOutputStream.flush();
//        resOutputStream.close();
    }
    //    @RequestMapping("/api/login")
//    public User login(@RequestBody User user) {
////        System.out.println(new Date().getTime());
////        Result<String> result = new Result<>();
////        result.setOk(true);
////        result.setCode(200);
////        result.setResult("登录成功");
////        result.setMsg("登录成功");
////        return result;
//        return userMapper.getUserById(1);
//    }
//
//    @GetMapping("/api/user/detail/{id}")
//    public User getUserById(@PathVariable("id") long id) {
//        return userMapper.getUserById(id);
//    }


}
