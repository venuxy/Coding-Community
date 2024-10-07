package com.venux.subject.application.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.subject.application.convert.SubjectLabelDTOConverter;
import com.venux.subject.application.dto.SubjectLabelDTO;
import com.venux.subject.common.entity.Result;
import com.venux.subject.domain.entity.SubjectLabelBO;
import com.venux.subject.domain.service.SubjectLabelDomainService;
import com.venux.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签controller
 *
 * @author: venux
 * @date: 2024/10/5
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {


    private SubjectLabelDomainService subjectLabelDomainService;

    public SubjectLabelController(SubjectLabelDomainService subjectLabelDomainService) {
        this.subjectLabelDomainService = subjectLabelDomainService;
    }

    /**
     * 新增标签
     */
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkArgument(!StringUtils.isEmpty(subjectLabelDTO.getLabelName()),
                    "标签名称不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result =  subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.add.error:{}",e.getMessage(),e);
            return Result.fail("新增标签失败");
        }


    }

    /**
     * 更新标签
     */
    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.update.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result =  subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新标签失败");
        }
    }

    /**
     * 删除标签
     */
    @RequestMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result =  subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除标签失败");
        }
    }

    /**
     * 查询某分类下的标签列表
     */
    @RequestMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            List<SubjectLabelBO> result =  subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> labelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBoToLabelDTOList(result);
            return Result.ok(labelDTOList);
        } catch (Exception e) {
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}",e.getMessage(),e);
            return Result.fail("查询标签失败");
        }
    }
}
