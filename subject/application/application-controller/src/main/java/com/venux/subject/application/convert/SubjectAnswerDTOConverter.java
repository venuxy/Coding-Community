package com.venux.subject.application.convert;

import com.venux.subject.application.dto.SubjectAnswerDTO;
import com.venux.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: venux
 * @date: 2024/10/11
 */
@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);
    List<SubjectAnswerBO> convertListDTOToBO(List<SubjectAnswerDTO> optionList);
}
