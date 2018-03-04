package com.studyplatform.web.service;

import java.util.List;

import com.studyplatform.web.bean.CourseBean;

/**
 * 业务逻辑封装接口（课程列表）
 * Title: UserService
 * @date 2018年2月22日
 * @author Freedom0013
 */
public interface CourseService {
    /**
     * 根据专业id获取所有专业对应课程
     * @param professions_id 专业id
     * @return 课程列表
     */
    List<CourseBean> getAllCourseByProId(int professions_id);
    
    /**
     * 根据课程id获取对应课程详情
     * @param course_id 课程id
     * @return 课程详情
     */
    CourseBean getCourseDetailByCouId(int course_id);
}
