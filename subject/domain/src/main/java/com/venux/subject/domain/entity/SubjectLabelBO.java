package com.venux.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签bo
 * @author: venux
 * @date: 2024/10/5 15:22
 */
@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = 552370257610746133L;
/**
     * 主键
     */
    private Long id;
/**
     * 标签分类
     */
    private String labelName;

/**
     * 排序
     */
    private Integer sortNum;

    /**
     * 分类id
     */
    private Long categoryId;
}

