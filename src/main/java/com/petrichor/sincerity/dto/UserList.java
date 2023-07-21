package com.petrichor.sincerity.dto;

import lombok.Data;

@Data
public class UserList  {
    private Long id;
    private String username;
    private String realname;
    private String phone;
    private Long roleId;
    private String avatar;
}
