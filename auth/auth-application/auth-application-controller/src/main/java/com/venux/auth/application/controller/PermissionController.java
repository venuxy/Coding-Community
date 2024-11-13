package com.venux.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.auth.application.convert.AuthPermissionDTOConverter;
import com.venux.auth.application.dto.AuthPermissionDTO;
import com.venux.auth.domain.entity.AuthPermissionBO;
import com.venux.auth.domain.service.AuthPermissionDomainService;
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
@RequestMapping("/permission/")
@Slf4j
public class PermissionController {
    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean>add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getName(), "角色名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getPermissionKey(), "角色标识不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色失败");
        }
    }

    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if(log.isInfoEnabled()){
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新角色失败");
        }
    }

    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if(log.isInfoEnabled()){
                log.info("PermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除角色失败");
        }
    }
}
