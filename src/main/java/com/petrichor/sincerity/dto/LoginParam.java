package com.petrichor.sincerity.dto;

import lombok.Data;

@Data
public class LoginParam {
    private String username;
    private String password;
    private String captchaKey;
    private String captcha;
}
