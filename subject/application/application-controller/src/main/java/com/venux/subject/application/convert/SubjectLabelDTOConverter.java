package com.venux.subject.application.convert;

import com.venux.subject.application.dto.SubjectLabelDTO;
import com.venux.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    List<SubjectLabelDTO> convertBoToLabelDTOList(List<SubjectLabelBO> subjectLabelBOList);

    SubjectLabelBO convertDtoToLabelBO(SubjectLabelDTO subjectLabelDTO);



}
