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

    /** 高级资源类别码 （80-100分）*/
    public static final int RESOURCE_TYPE_EXPERT = 1;
    /** 中级资源类别码（80-60分） */
    public static final int RESOURCE_TYPE_MIDDLE = 2;
    /** 初级资源类别码 （60分以下）*/
    public static final int RESOURCE_TYPE_PRIMARY = 3;
    
    /** 视频资源分类标识码 */
    public static final int RESOURCE_CATEGORY_VIDEO = 1;
    /** 书籍资源分类标识码 */
    public static final int RESOURCE_CATEGORY_BOOK = 2;
    
    /** 管理员标识码 */
    public static final int USER_IS_ADMINISTRATOR = 0;
    /** 正常用户标识码 */
    public static final int USER_IS_NOMAL = 1;
}
