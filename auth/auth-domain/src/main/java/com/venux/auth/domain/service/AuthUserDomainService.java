package com.venux.auth.domain.service;

import com.venux.auth.domain.entity.AuthUserBO;

public interface AuthUserDomainService {


    Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);
}
