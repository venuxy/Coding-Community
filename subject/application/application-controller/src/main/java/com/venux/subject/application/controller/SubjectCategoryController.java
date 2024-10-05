package com.venux.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.venux.subject.application.convert.SubjectCategoryDTOConverter;
import com.venux.subject.application.dto.SubjectCategoryDTO;
import com.venux.subject.common.entity.Result;
import com.venux.subject.domain.entity.SubjectCategoryBO;
import com.venux.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题分类controller
 *
 * @author: venux
 * @date: 2024/9/29
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isEmpty(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail("新增分类失败");
        }
    }
    /**
     * 查询岗位大类
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<SubjectCategoryDTO> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE
                    .convertDtoToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
                    convertBoToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }
    /**
     * 根据分类id查二级分类
     */
    @PostMapping("/queryCategoryByPrimary")
    public Result<SubjectCategoryDTO> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryCategoryByPrimary.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
                    convertBoToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryCategoryByPrimary.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }

    }

    /**
     * 更新分类
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.update.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新分类失败");
        }
    }

    /**
     * 删除分类
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.delete.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除分类失败");
        }
    }
}
