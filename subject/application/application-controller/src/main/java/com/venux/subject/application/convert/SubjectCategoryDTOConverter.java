package com.venux.subject.application.convert;

import com.venux.subject.application.dto.SubjectCategoryDTO;
import com.venux.subject.domain.entity.SubjectCategoryBO;
import com.venux.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoToCategoryDTOList(List<SubjectCategoryBO> subjectCategoryBOList);

}
