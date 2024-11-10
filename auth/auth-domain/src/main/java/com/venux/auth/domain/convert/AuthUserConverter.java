package com.venux.auth.domain.convert;

import com.venux.auth.domain.entity.AuthUserBO;
import com.venux.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserConverter {

    AuthUserConverter INSTANCE = Mappers.getMapper(AuthUserConverter.class);


    AuthUser convertBoToEntity(AuthUserBO authUserBO);
}
