package com.petrichor.sincerity.dto;

import lombok.Data;

@Data
public class LoginResult {
    UserInfo userInfo;
    String token;
}
