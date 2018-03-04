package com.studyplatform.web.service.impl;

import java.util.List;

import com.studyplatform.web.bean.CourseBean;
import com.studyplatform.web.dao.CourseDao;
import com.studyplatform.web.dao.impl.CourseDaoImpl;
import com.studyplatform.web.service.CourseService;

/**
 * 业务逻辑封装接口实现（课程操作）
 * Title: CourseServiceImpl
 * @date 2018年3月3日
 * @author Freedom0013
 */
public class CourseServiceImpl implements CourseService {
    CourseDao dao = new CourseDaoImpl();
    
    @Override
    public List<CourseBean> getAllCourseByProId(int professions_id) {
        return dao.getAllCourseByProfessionId(professions_id);
    }

    @Override
    public CourseBean getCourseDetailByCouId(int course_id) {
        return dao.getCourseById(course_id);
    }
}
