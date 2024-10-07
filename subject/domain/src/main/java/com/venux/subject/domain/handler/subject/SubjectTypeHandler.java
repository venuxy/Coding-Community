package com.venux.subject.domain.handler.subject;

import com.venux.subject.common.enums.SubjectInfoTypeEnum;
import com.venux.subject.domain.entity.SubjectInfoBO;

public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目的插入
     */
    void add(SubjectInfoBO subjectInfoBO);

}
