package com.petrichor.sincerity.service;

import com.petrichor.sincerity.annotation.SnowFlakeId;
import com.petrichor.sincerity.entity.SysPermission;
import com.petrichor.sincerity.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SysPermissionService implements SysPermissionMapper {
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @SnowFlakeId
    @Override
    public int insertPermission(SysPermission sysPermission) {
        return sysPermissionMapper.insertPermission(sysPermission);
    }

    @Override
    public List<SysPermission> getAllPermissions(SysPermission sysPermission) {
        return sysPermissionMapper.getAllPermissions(sysPermission);
    }

    @Override
    public int updatePermission(SysPermission sysPermission) {
        return sysPermissionMapper.updatePermission(sysPermission);
    }

    @Override
    public int deletePermission(Long id, String updateBy) {
        return sysPermissionMapper.deletePermission(id, updateBy);
    }

    public List<SysPermission> getPermissionListByParentId(Long parentId, List<SysPermission> allPermissions) {
        ArrayList<SysPermission> permissions = new ArrayList<>();
        for (SysPermission sysPermission : allPermissions) {
            if (Objects.equals(sysPermission.getParentId(), parentId)) {
                permissions.add(sysPermission);
            }
        }
        return permissions;
    }

    public void setPermissionChildren(List<SysPermission> tarPermissions, List<SysPermission> allPermissions) {
        for (SysPermission permission : tarPermissions) {
            Long parentId = permission.getId();
            List<SysPermission> _permissions = getPermissionListByParentId(parentId, allPermissions);
            if (!_permissions.isEmpty()) {
                permission.setChildren(_permissions);
                setPermissionChildren(_permissions, allPermissions);
            }
        }
    }

    public List<SysPermission> getPermissionTree(List<SysPermission> allPermissions) {
        List<SysPermission> noParentPermissions = allPermissions.
                stream().
                filter(p -> p.getParentId() == null).
                toList();
        setPermissionChildren(noParentPermissions, allPermissions);
        return noParentPermissions;
    }
}
