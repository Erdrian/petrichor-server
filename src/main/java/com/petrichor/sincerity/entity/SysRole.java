package com.petrichor.sincerity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {
    private Long id;
    private String roleName;
    private Long[] permissionIds;
}
