package com.petrichor.sincerity.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private long id;
    private String username;
    private String password;
    private String realname;
    private String phone;
    private long roleId;
}
