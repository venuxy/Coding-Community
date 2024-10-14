package com.venux.subject.domain.handler.subject;

import com.venux.subject.common.enums.IsDeletedFlagEnum;
import com.venux.subject.common.enums.SubjectInfoTypeEnum;
import com.venux.subject.domain.convert.BriefSubjectConverter;
import com.venux.subject.domain.entity.SubjectInfoBO;
import com.venux.subject.domain.entity.SubjectOptionBO;
import com.venux.subject.infra.basic.entity.SubjectBrief;
import com.venux.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 简答题目的策略类
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectBriefService subjectBriefService;
    
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {

        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(Long.valueOf(subjectId));

        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;

    }

}
