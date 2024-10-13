package com.venux.subject.domain.service;

import com.venux.subject.common.entity.PageResult;
import com.venux.subject.domain.entity.SubjectInfoBO;

import java.util.List;

/**
 * 题目领域服务
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);


    /**
     * 分页查询
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询单个题目信息
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}

