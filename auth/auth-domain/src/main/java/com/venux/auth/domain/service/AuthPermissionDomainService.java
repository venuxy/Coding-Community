package com.venux.auth.domain.service;

import com.venux.auth.domain.entity.AuthPermissionBO;

public interface AuthPermissionDomainService {


    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);
}
