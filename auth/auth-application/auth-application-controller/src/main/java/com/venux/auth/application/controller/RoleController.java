package com.venux.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.auth.application.convert.AuthRoleDTOConverter;
import com.venux.auth.application.dto.AuthRoleDTO;
import com.venux.auth.domain.entity.AuthRoleBO;
import com.venux.auth.domain.service.AuthRoleDomainService;
import com.venux.subject.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色controller
 * @author: venux
 * @date: 2024/11/10
 */
@RestController
@RequestMapping("/role/")
@Slf4j
public class RoleController {
    @Resource
    private AuthRoleDomainService authRoleDomainService;

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean>add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getRoleName(), "角色名称不能为空");
            Preconditions.checkNotNull(authRoleDTO.getRoleKey(), "角色标识不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色失败");
        }
    }

    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try{
            if(log.isInfoEnabled()){
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新角色失败");
        }
    }

    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try{
            if(log.isInfoEnabled()){
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除角色失败");
        }
    }
}
