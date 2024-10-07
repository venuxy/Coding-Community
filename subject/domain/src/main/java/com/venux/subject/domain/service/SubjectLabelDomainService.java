package com.venux.subject.domain.service;

import com.venux.subject.domain.entity.SubjectLabelBO;

import java.util.List;


public interface SubjectLabelDomainService {

    /**
     * 新增标签
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 根据分类id查询标签
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
