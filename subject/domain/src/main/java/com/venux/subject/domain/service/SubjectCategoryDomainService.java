package com.venux.subject.domain.service;

import com.venux.subject.domain.entity.SubjectCategoryBO;

import java.util.List;


public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
