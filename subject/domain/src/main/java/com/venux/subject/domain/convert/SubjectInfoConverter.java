package com.venux.subject.domain.convert;

import com.venux.subject.domain.entity.SubjectInfoBO;
import com.venux.subject.domain.entity.SubjectOptionBO;
import com.venux.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertListInfoToBO(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO optionBO, SubjectInfo subjectInfo);
}
