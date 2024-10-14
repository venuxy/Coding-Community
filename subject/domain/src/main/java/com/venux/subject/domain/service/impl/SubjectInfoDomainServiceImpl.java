package com.venux.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.venux.subject.common.entity.PageResult;
import com.venux.subject.common.enums.IsDeletedFlagEnum;
import com.venux.subject.domain.convert.SubjectInfoConverter;
import com.venux.subject.domain.entity.SubjectInfoBO;
import com.venux.subject.domain.entity.SubjectOptionBO;
import com.venux.subject.domain.handler.subject.SubjectTypeHandler;
import com.venux.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.venux.subject.domain.service.SubjectInfoDomainService;
import com.venux.subject.infra.basic.entity.SubjectInfo;
import com.venux.subject.infra.basic.entity.SubjectLabel;
import com.venux.subject.infra.basic.entity.SubjectMapping;
import com.venux.subject.infra.basic.service.SubjectInfoService;
import com.venux.subject.infra.basic.service.SubjectLabelService;
import com.venux.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    private static final String RANK_KEY = "subject_rank";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }

        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());

        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertListInfoToBO(subjectInfoList);
        pageResult.setTotal(count);
        pageResult.setRecords(subjectInfoBOList);

        return pageResult;

    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId().intValue());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);

        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = subjectLabelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());

        bo.setLabelName(labelNameList);
        return bo;
    }


}
