package com.venux.auth.application.convert;

import com.venux.auth.application.dto.AuthPermissionDTO;
import com.venux.auth.application.dto.AuthRoleDTO;
import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 权限信息dto 转换 bo
 * @author: venux
 * @date 2024/11/9 15:53
 */
@Mapper
public interface AuthPermissionDTOConverter {
    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);
    List<AuthPermissionBO> convertListDTOToBO(List<AuthPermissionDTO> optionList);

    AuthPermissionBO convertDTOToBO(AuthPermissionDTO authPermissionDTO);
}
