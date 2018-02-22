package com.studyplatform.web.system;

/**
 * 系统通用状态码
 * Title: SystemCommonValue
 * @date 2018年2月2日
 * @author Freedom0013
 */
public class SystemCommonValue {
    /** 执行成功状态码  :: 0*/
    public static final int OPERATION_SUCCESS = 0;
    /** 执行失败状态码  :: 1*/
    public static final int OPERATION_FAILED = 1;
    
    
    /** 基础试题类型码 :: 0*/
    public static final int EXAM_QUESTION_EASY_VALUE = 0;
    /** 中级试题类型码  :: 1*/
    public static final int EXAM_QUESTION_NOMAL_VALUE = 1;
    /** 高级试题类型码 :: 2 */
    public static final int EXAM_QUESTION_HARD_VALUE = 2;
    /** 单选试题状态码  :: 0*/
    public static final int EXAM_QUESTION_TYPE_SINGLE = 0;
    /** 多选试题状态码  :: 1*/
    public static final int EXAM_QUESTION_TYPE_MULTI = 1;
    /** 判断试题状态码  :: 2*/
    public static final int EXAM_QUESTION_TYPE_JUDGE = 2;
}
