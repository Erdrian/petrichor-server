package com.petrichor.sincerity.model;

import lombok.Data;

import java.util.List;

@Data
public class RoleLinkPermissionBody {
    private Long roleId;
    private List<Long> permissionIds;
}
