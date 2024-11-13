package com.venux.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.auth.application.convert.AuthPermissionDTOConverter;
import com.venux.auth.application.convert.AuthRolePermissionDTOConverter;
import com.venux.auth.application.dto.AuthPermissionDTO;
import com.venux.auth.application.dto.AuthRolePermissionDTO;
import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.domain.entity.AuthRolePermissionBO;
import com.venux.auth.domain.service.AuthPermissionDomainService;
import com.venux.auth.domain.service.AuthRolePermissionDomainService;
import com.venux.auth.infra.basic.service.AuthRolePermissionService;
import com.venux.subject.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限controller
 * @author: venux
 * @date: 2024/11/10
 */
@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {
    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean>add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getPermissionIdList(), "角色权限不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色权限失败");
        }
    }

}
