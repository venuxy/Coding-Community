package com.venux.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签dto
 * @author: venux
 * @date: 2024/10/5 15:22
 */
@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = 552370257610746133L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;


    /**
     * 标签分类
     */
    private String labelName;

    /**
     * 排序
     */
    private Integer sortNum;

}

