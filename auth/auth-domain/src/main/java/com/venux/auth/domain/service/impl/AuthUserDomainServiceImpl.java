package com.venux.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.venux.auth.domain.convert.AuthUserConverter;
import com.venux.auth.domain.entity.AuthUserBO;
import com.venux.auth.domain.service.AuthUserDomainService;
import com.venux.auth.infra.basic.entity.AuthUser;
import com.venux.auth.infra.basic.service.AuthUserService;
import com.venux.subject.common.enums.IsDeletedFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;


    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConverter.INSTANCE.convertBoToEntity(authUserBO);
        Integer count = authUserService.insert(authUser);
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
