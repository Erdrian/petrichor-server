package com.petrichor.sincerity.model;

import com.petrichor.sincerity.entity.BaseEntity;
import com.petrichor.sincerity.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserList extends BaseEntity {
    private Long id;
    private String userName;
    private String realName;
    private String phone;
    private String avatar;
    private List<SysRole> roles;
}
