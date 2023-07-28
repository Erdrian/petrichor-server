package com.petrichor.sincerity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class User extends BaseEntity{
    private Long id;
    private String userName;
    private String password;
    private String realName;
    private String phone;
    private Long roleId;
    private String avatar;
    private String status;
}
