package com.studyplatform.web.service;

import java.util.List;

import com.studyplatform.web.bean.ResourceBean;

/**
 * 业务逻辑封装接口（资源操作）
 * Title: ResourceService
 * @date 2018年2月22日
 * @author Freedom0013
 */
public interface ResourceService {
    /**
     * 根据成绩等级和课程id获取资源
     * @param resource_degree 课程等级码
     * @param course_id 课程id
     * @return 对应资源列表
     */
    List<ResourceBean> getResourceByDegree(int resource_degree ,int course_id);
    
    /**
     * 根据页码获取全部资源列表
     * @param page_Num 页码  
     * @param pageSize 页面大小
     * @return 对应页面资源列表
     */
    List<ResourceBean> getAllResourceListByPage(int page_Num,int page_Size);
    
    /**
     * 获取单个资源详情信息
     * @param resource_id 资源id
     * @return 单个资源详细信息
     */
    ResourceBean getResourceById(int resource_id);
    
    /**
     * 根据课程id获取对应课程资源
     * @param course_id 课程id
     * @return 对应课程资源列表
     */
    List<ResourceBean> getResourceByCourseId(int course_id);
}
