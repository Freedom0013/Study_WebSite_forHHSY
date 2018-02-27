package com.studyplatform.web.dao;

import java.util.List;

import com.studyplatform.web.bean.CourseBean;
import com.studyplatform.web.bean.ProfessionBean;

/**
 * CourseDao（课程数据处理层接口）
 * Title: CourseDao
 * @date 2018年2月27日
 * @author Freedom0013
 */
public interface CourseDao {
    /**
     * 添加课程
     * @param coursebean 课程封装Bean
     * @return 添加成功标识
     */
    int addCourse(CourseBean coursebean);
    
    /**
     * 删除课程
     * @param course_id 课程id
     * @return 删除成功标识
     */
    int deleteCourse(int course_id);
    
    /**
     * 修改课程信息
     * @param coursebean 课程Bean
     * @return 修改成功标识
     */
    int updataCourse(CourseBean coursebean);
    
    /**
     * 获取所有课程列表
     * @return 课程List
     */
    List<CourseBean> getAllCourse();
    
    /**
     * 通过专业id获取所有课程列表
     * @param pression_id 专业id
     * @return 专业所辖课程List
     */
    List<CourseBean> getAllCourseByProfessionId(int pression_id);
    
    /**
     * 获取单个课程详情信息
     * @param course_id 课程id
     * @return 课程详情Bean
     */
    CourseBean getCourseById(int course_id);
}
