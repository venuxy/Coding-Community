package com.venux.auth.domain.service;

import com.venux.auth.domain.entity.AuthRoleBO;
import com.venux.auth.domain.entity.AuthUserBO;

public interface AuthRoleDomainService {


    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
