package com.venux.auth.domain.convert;

import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限bo转实体
 *
 * @author: venux
 * @date: 2024/11/13
 */
@Mapper
public interface AuthPermissionConverter {
    AuthPermissionConverter INSTANCE = Mappers.getMapper(AuthPermissionConverter.class);

    AuthPermission convertBoToEntity(AuthPermissionBO authPermissionBO);
}
