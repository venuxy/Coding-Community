package com.venux.auth.application.convert;

import com.venux.auth.application.dto.AuthRoleDTO;
import com.venux.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色信息dto 转换 bo
 * @author: venux
 * @date 2024/11/9 15:53
 */
@Mapper
public interface AuthRoleDTOConverter {
    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);
    List<AuthRoleBO> convertListDTOToBO(List<AuthRoleDTO> optionList);

    AuthRoleBO convertDTOToBO(AuthRoleDTO authRoleDTO);
}
