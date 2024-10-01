package com.venux.subject.application.controller;

import com.venux.subject.infra.basic.entity.SubjectCategory;
import com.venux.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题controller
 *
 * @author: venux
 * @date: 2024/9/29
 */
@RestController
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @GetMapping("/test")
    public String test(){
        System.out.println("hello world");
        return "hello world";
//        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
//        return subjectCategory.getCategoryName();
    }




}
