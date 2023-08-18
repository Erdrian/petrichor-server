package com.petrichor.sincerity.model;

import com.petrichor.sincerity.entity.SysPermission;
import lombok.Data;

import java.util.List;

@Data
public class LoginResult {
    private UserInfo userInfo;
    private String token;
    private List<String> authorizes;
    private List<String> routes;
    private List<SysPermission> navigation;

    @Data
    public static class UserInfo {
        private Long id;
        private String username;
        private String realname;
        private String phone;
        private String avatar;
    }
}
