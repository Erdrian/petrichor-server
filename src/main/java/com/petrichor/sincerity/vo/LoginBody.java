package com.petrichor.sincerity.vo;

import lombok.Data;

@Data
public class LoginBody {
    private String userName;
    private String password;
    private String captchaKey;
    private String captcha;
}
