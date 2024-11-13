package com.venux.auth.domain.service.impl;

import com.venux.auth.domain.convert.AuthRolePermissionConverter;
import com.venux.auth.domain.entity.AuthRolePermissionBO;
import com.venux.auth.domain.service.AuthPermissionDomainService;
import com.venux.auth.domain.service.AuthRolePermissionDomainService;
import com.venux.auth.infra.basic.entity.AuthPermission;
import com.venux.auth.infra.basic.entity.AuthRole;
import com.venux.auth.infra.basic.entity.AuthRolePermission;
import com.venux.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> authRolePermissionList = new LinkedList<>();

        for (Long permissionId : authRolePermissionBO.getPermissionIdList()) {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(authRolePermissionBO.getRoleId());
            authRolePermission.setPermissionId(permissionId);
            authRolePermissionList.add(authRolePermission);
        }

        return authRolePermissionService.insertBatch(authRolePermissionList) > 0;
    }

}
