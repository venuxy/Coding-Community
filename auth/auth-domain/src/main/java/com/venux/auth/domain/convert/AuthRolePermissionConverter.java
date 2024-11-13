package com.venux.auth.domain.convert;

import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.domain.entity.AuthRolePermissionBO;
import com.venux.auth.infra.basic.entity.AuthPermission;
import com.venux.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色权限bo转实体
 *
 * @author: venux
 * @date: 2024/11/13
 */
@Mapper
public interface AuthRolePermissionConverter {
    AuthRolePermissionConverter INSTANCE = Mappers.getMapper(AuthRolePermissionConverter.class);

    AuthRolePermission convertBoToEntity(AuthRolePermissionBO AuthRolePermissionBO);
}
