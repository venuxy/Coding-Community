package com.venux.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色bo
 * @author makejava
 * @since 2024-11-10 20:03:49
 */
@Data
public class AuthRoleBO implements Serializable {
    private static final long serialVersionUID = -90520130858049051L;

    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色唯一标识
     */
    private String roleKey;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否被删除 0未删除 1已删除
     */
    private Integer isDeleted;

}

