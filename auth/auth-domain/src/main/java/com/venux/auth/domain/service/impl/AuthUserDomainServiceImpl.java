package com.venux.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.venux.auth.common.enums.AuthUserStatusEnum;
import com.venux.auth.domain.constants.AuthConstant;
import com.venux.auth.domain.convert.AuthUserConverter;
import com.venux.auth.domain.entity.AuthUserBO;
import com.venux.auth.domain.service.AuthUserDomainService;
import com.venux.auth.infra.basic.entity.AuthRole;
import com.venux.auth.infra.basic.entity.AuthUser;
import com.venux.auth.infra.basic.entity.AuthUserRole;
import com.venux.auth.infra.basic.service.AuthRoleService;
import com.venux.auth.infra.basic.service.AuthUserRoleService;
import com.venux.auth.infra.basic.service.AuthUserService;
import com.venux.subject.common.enums.IsDeletedFlagEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthRoleService authRoleService;


    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBoToEntity(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        int count = authUserService.insert(authUser);

        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBoToEntity(authUserBO);
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return authUserService.update(authUser) > 0;
    }


}
