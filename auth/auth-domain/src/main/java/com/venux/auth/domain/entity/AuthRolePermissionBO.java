package com.venux.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限bo
 * @author makejava
 * @since 2024-11-12 21:40:30
 */
@Data
public class AuthRolePermissionBO implements Serializable {
    private static final long serialVersionUID = 437292772449489914L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private List<Long> permissionIdList;
}

