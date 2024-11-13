package com.venux.auth.domain.service.impl;

import com.venux.auth.domain.convert.AuthPermissionConverter;
import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.domain.service.AuthPermissionDomainService;
import com.venux.auth.infra.basic.entity.AuthPermission;
import com.venux.auth.infra.basic.service.AuthPermissionService;
import com.venux.subject.common.enums.IsDeletedFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;
    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionConverter.INSTANCE.convertBoToEntity(authPermissionBO);
        return authPermissionService.insert(authPermission) > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionConverter.INSTANCE.convertBoToEntity(authPermissionBO);
        return authPermissionService.update(authPermission) > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return authPermissionService.update(authPermission) > 0;
    }

}
