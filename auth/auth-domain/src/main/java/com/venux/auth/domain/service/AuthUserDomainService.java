package com.venux.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.venux.auth.domain.entity.AuthUserBO;

public interface AuthUserDomainService {


    Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);
}
