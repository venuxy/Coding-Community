package com.venux.auth.application.convert;

import com.venux.auth.application.dto.AuthPermissionDTO;
import com.venux.auth.application.dto.AuthRolePermissionDTO;
import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色权限信息dto 转换 bo
 * @author: venux
 * @date 2024/11/9 15:53
 */
@Mapper
public interface AuthRolePermissionDTOConverter {
    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);
    List<AuthRolePermissionBO> convertListDTOToBO(List<AuthRolePermissionDTO> optionList);

    AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO);
}
