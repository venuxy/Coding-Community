package com.venux.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-11-13 11:23:48
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 628134210793314665L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private List<Long> permissionIdList;
}

