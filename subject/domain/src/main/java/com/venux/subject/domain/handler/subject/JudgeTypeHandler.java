package com.venux.subject.domain.handler.subject;

import com.venux.subject.common.enums.IsDeletedFlagEnum;
import com.venux.subject.common.enums.SubjectInfoTypeEnum;
import com.venux.subject.domain.convert.JudgeSubjectConverter;
import com.venux.subject.domain.entity.SubjectAnswerBO;
import com.venux.subject.domain.entity.SubjectInfoBO;
import com.venux.subject.domain.entity.SubjectOptionBO;
import com.venux.subject.infra.basic.entity.SubjectJudge;
import com.venux.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题目的策略类
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectJudgeService subjectJudgeService;
    
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断题目的插入
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(Long.valueOf(subjectId));
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);

        List<SubjectAnswerBO> subjectAnswerBOList = JudgeSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }

}
