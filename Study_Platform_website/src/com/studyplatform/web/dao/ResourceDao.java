package com.studyplatform.web.dao;

import java.util.List;

import com.studyplatform.web.bean.ResourceBean;

/**
 * ResourceDao（资源数据处理层接口）
 * Title: ResourceDao
 * @date 2018年2月27日
 * @author Freedom0013
 */
public interface ResourceDao {
    /**
     * 添加资源
     * @param resourcebean 资源Bean
     * @return 添加成功标识
     */
    int addResource(ResourceBean resourcebean);
    
    /**
     * 删除资源
     * @param resource_id 资源id
     * @return 删除成功标识
     */
    int deleteResource(int resource_id);
    
    /**
     * 修改资源信息
     * @param resourcebean 资源Bean
     * @return 修改成功标识
     */
    int updataResource(ResourceBean resourcebean);
    
    /**
     * 获取所有课程对应资源列表
     * @param course_id 课程id
     * @return 课程对应资源列表
     */
    List<ResourceBean> getAllResourceByCourseId(int course_id);
    
    /**
     * 获取所有当前分数和课程对应的资源列表
     * @param resource_degree 课程分数等级
     * @param course_id 课程id
     * @return 当前分数和课程对应的资源列表
     */
    List<ResourceBean> getAllbyDegree(int resource_degree,int course_id);
    
    /**
     * 获取单个资源详情
     * @param resource_id 资源id
     * @return 资源bean
     */
    ResourceBean getResourceById(int resource_id);
    
    /**
     * 获取所有资源数据（分页）
     * @param page_Num 当前页码
     * @param page_Size 每页数据大小
     * @return 当前分页数据列表
     */
    List<ResourceBean> getAllResourceListByPage(int page_Num,int page_Size);
}
