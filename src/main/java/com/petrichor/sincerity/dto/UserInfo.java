package com.petrichor.sincerity.dto;

import lombok.Data;

@Data
public class UserInfo {
    private long id;
    private String username;
    private String realname;
    private String phone;
    private String avatar;
}
