package com.studyplatform.web.system;

/**
 * 系统通用状态码
 * Title: SystemCommonValue
 * @date 2018年2月2日
 * @author Freedom0013
 */
public class SystemCommonValue {
    /** 上线/测试地址切换标识（true：本地 false：线上）*/
    public static final boolean isTest = true;
    /** Log标识（true：显示  false：隐藏） */
    public static final boolean isDebug = true;
    /** 显示常规级信息 */
    public static final boolean Logger = true;
    /** 显示错误级信息 */
    public static final boolean Error = true;
    
    /** 本地地址 */
    public static final String HOST;
    /** 云服务器地址 */
    public static final String DOMAIN = "119.27.161.250:8080/Study_Platform_website";
    static {
        if (isTest) {
            HOST = "192.168.0.106:8080/Study_Platform_website";     //本地地址
        } else {
            HOST = "" + DOMAIN;
        }
    }
    
    /** 下载文件名 */
    public static final String DOWNLOAD_APP_NAME = "studytree.apk";
    /** 下载路径 */
    public static final String DOWNLOAD_URL = "http://" + HOST + "/studytree.apk";
    
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
    
    /** 页面请求编码格式 */
    public static final String WEB_REQUEST_ENCODING = "UTF-8";
    /** 页面响应编码格式 */
    public static final String WEB_RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8";
}
