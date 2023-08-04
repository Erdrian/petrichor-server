package com.petrichor.sincerity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermission extends BaseEntity {
    private Long id;
    private String name;
    private String authority;
    private boolean isNavigation;
    private boolean isRoute;
    private Long parentId;
    private Integer orderNum;
    private String path;
    private String title;
    private String icon;
}
