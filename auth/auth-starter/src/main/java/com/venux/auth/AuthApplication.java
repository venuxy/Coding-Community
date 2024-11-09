package com.venux.auth;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 鉴权微服务启动类
 */
@SpringBootApplication
@ComponentScan("com.venux")
//@MapperScan("com.venux.**.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
        SaManager.getSaTokenDao().set("name", "value", 100000);
    }

}
