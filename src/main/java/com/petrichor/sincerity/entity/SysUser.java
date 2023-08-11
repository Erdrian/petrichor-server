package com.petrichor.sincerity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class SysUser extends BaseEntity {
    private Long id;
    private String userName;
    private String password;
    private String realName;
    private String phone;
    private String avatar;
    private String status;
    private List<Long> roleIds;
}
