package com.petrichor.sincerity.dto;

import com.petrichor.sincerity.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserList extends BaseEntity {
    private Long id;
    private String userName;
    private String realName;
    private String phone;
    private String avatar;
}
