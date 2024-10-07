package com.venux.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 简答题(SubjectBrief)实体类
 *
 * @author makejava
 * @since 2024-10-07 17:33:44
 */
@Data
public class SubjectBrief implements Serializable {
    private static final long serialVersionUID = 273361510977936591L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 题目id
     */
    private Integer subjectId;
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
     * 是否删除
     */
    private Integer isDeleted;

}

