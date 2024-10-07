package com.venux.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举，1：单选，2：多选，3：判断，4：简答
 * @author: venux
 * @date 2024/10/7 17:41
 */
@Getter
public enum SubjectInfoTypeEnum {

    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答"),
    ;

    public int code;

    public String desc;

    SubjectInfoTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int codeVal){
        for(SubjectInfoTypeEnum resultCodeEnum : SubjectInfoTypeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }

}
