package com.venux.auth.domain.convert;

import com.venux.auth.domain.entity.AuthRoleBO;
import com.venux.auth.domain.entity.AuthUserBO;
import com.venux.auth.infra.basic.entity.AuthRole;
import com.venux.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleConverter {

    AuthRoleConverter INSTANCE = Mappers.getMapper(AuthRoleConverter.class);


    AuthRole convertBoToEntity(AuthRoleBO authRoleBO);
}
