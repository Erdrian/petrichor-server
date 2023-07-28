package com.petrichor.sincerity.dto;

import lombok.Data;

@Data
public class LoginResult {
    UserInfo userInfo;
    String token;

    @Data
    public static class UserInfo {
        private String username;
        private String realname;
        private String phone;
        private String avatar;
    }
}
