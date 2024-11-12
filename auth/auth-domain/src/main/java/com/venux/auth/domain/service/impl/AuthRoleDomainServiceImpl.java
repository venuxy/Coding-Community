package com.venux.auth.domain.service.impl;

import com.venux.auth.domain.convert.AuthRoleConverter;
import com.venux.auth.domain.convert.AuthUserConverter;
import com.venux.auth.domain.entity.AuthRoleBO;
import com.venux.auth.domain.entity.AuthUserBO;
import com.venux.auth.domain.service.AuthRoleDomainService;
import com.venux.auth.domain.service.AuthUserDomainService;
import com.venux.auth.infra.basic.entity.AuthRole;
import com.venux.auth.infra.basic.entity.AuthUser;
import com.venux.auth.infra.basic.service.AuthRoleService;
import com.venux.auth.infra.basic.service.AuthUserService;
import com.venux.subject.common.enums.IsDeletedFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;
    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleConverter.INSTANCE.convertBoToEntity(authRoleBO);
        return authRoleService.insert(authRole) > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleConverter.INSTANCE.convertBoToEntity(authRoleBO);
        return authRoleService.update(authRole) > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleBO.getId());
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return authRoleService.update(authRole) > 0;
    }

}
