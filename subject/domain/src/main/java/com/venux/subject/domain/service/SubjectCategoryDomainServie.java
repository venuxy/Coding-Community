package com.venux.subject.domain.service;

import com.venux.subject.domain.entity.SubjectCategoryBO;
import com.venux.subject.infra.basic.entity.SubjectCategory;
import org.springframework.stereotype.Service;


public interface SubjectCategoryDomainServie {

    void add(SubjectCategoryBO subjectCategoryBO);

}
