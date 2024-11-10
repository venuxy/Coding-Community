package com.venux.auth.application.convert;

import com.venux.auth.application.dto.AuthUserDTO;
import com.venux.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户信息dto 转换 bo
 * @author: venux
 * @date 2024/11/9 15:53
 */
@Mapper
public interface AuthUserDTOConverter {
    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);
    List<AuthUserBO> convertListDTOToBO(List<AuthUserDTO> optionList);

    AuthUserBO convertDTOToBO(AuthUserDTO authUserDTO);
}
