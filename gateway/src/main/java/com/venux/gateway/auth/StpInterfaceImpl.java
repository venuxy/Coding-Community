package com.venux.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    private static final Logger logger = LoggerFactory.getLogger(StpInterfaceImpl.class);

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        logger.info("调用 getPermissionList 方法, loginId: {}, loginType: {}", loginId, loginType);

        List<String> permissionList = new ArrayList<>();
        permissionList.add("subject:add");

        logger.info("返回权限列表: {}", permissionList);
        return permissionList;
    }

    public List<String> getRoleList(Object loginId, String loginType) {
        logger.info("调用 getRoleList 方法, loginId: {}, loginType: {}", loginId, loginType);

        List<String> roleList = new ArrayList<>();
        roleList.add("admin");

        logger.info("返回角色列表: {}", roleList);
        return roleList;
    }

}
