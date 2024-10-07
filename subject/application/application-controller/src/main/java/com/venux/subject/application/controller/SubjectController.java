package com.venux.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.subject.application.convert.SubjectCategoryDTOConverter;
import com.venux.subject.application.dto.SubjectCategoryDTO;
import com.venux.subject.application.dto.SubjectInfoDTO;
import com.venux.subject.common.entity.Result;
import com.venux.subject.domain.entity.SubjectCategoryBO;
import com.venux.subject.domain.service.SubjectInfoDomainService;
import com.venux.subject.infra.basic.entity.SubjectCategory;
import com.venux.subject.infra.basic.entity.SubjectInfo;
import com.venux.subject.infra.basic.service.SubjectCategoryService;
import com.venux.subject.infra.basic.service.SubjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题controller
 *
 * @author: venux
 * @date: 2024/9/29
 */
@RestController
@Slf4j
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()),
                    "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds())
                    , "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds())
                    , "标签id不能为空");

            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail("新增分类失败");
        }
    }




}
