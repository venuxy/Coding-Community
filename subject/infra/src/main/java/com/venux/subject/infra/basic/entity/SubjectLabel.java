package com.venux.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目标签类
 * @author: venux
 * @date: 2024/10/5 15:53
 */
@Data
public class SubjectLabel implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 排序
     */
    private Integer sortNum;
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

    private Integer isDeleted;
    /**
     * 更新时间
     */
    private Date updateTime;

}

