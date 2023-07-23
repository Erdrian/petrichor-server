package com.petrichor.sincerity.dto;

import com.petrichor.sincerity.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserList extends BaseEntity {
    private Long id;
    private String username;
    private String realname;
    private String phone;
    private Long roleId;
    private String avatar;
}
