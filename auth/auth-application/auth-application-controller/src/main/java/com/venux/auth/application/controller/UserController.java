package com.venux.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.auth.application.convert.AuthUserDTOConverter;
import com.venux.auth.application.dto.AuthUserDTO;
import com.venux.auth.domain.entity.AuthUserBO;
import com.venux.auth.domain.service.AuthUserDomainService;
import com.venux.subject.common.entity.Result;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户controller
 * @author: venux
 * @date 2024/11/10 20:08
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    private void checkUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
    }

    /**
     * 注册用户
     */
    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        }catch (Exception e){
            log.error("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("注册用户失败");
        }
    }


    /**
     * 更新用户
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新用户失败");
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除用户失败");
        }
    }

    /**
     * 启用/禁用用户
     */
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus.error:{}",e.getMessage(),e);
            return Result.fail("删除用户失败");
        }
    }



    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode),"验证码不能为空!");
            return Result.ok(authUserDomainService.doLogin(validCode));
        }catch (Exception e){
            log.error("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail("用户登录失败");
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        ArrayList list = new ArrayList();
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

}
