package com.petrichor.sincerity.dto;

import lombok.Data;

@Data
public class LoginBody {
    private String userName;
    private String password;
    private String captchaKey;
    private String captcha;
}
