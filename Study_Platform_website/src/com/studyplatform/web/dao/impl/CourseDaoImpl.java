package com.studyplatform.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.studyplatform.web.bean.CourseBean;
import com.studyplatform.web.dao.CourseDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * CourseDao实现类
 * Title: CourseDaoImpl
 * @date 2018年2月27日
 * @author Freedom0013
 */
public class CourseDaoImpl implements CourseDao {

    @Override
    public int addCourse(CourseBean coursebean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteCourse(int course_id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updataCourse(CourseBean coursebean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<CourseBean> getAllCourse() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<CourseBean> courseList = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `courses`";
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery();
            courseList = new ArrayList<CourseBean>();
            while (resultset.next()) {
                CourseBean course = new CourseBean();
                course.setCourse_id(resultset.getInt(1));
                course.setCourse_name(resultset.getString(2));
                course.setCourse_applypeople(resultset.getString(3));
                course.setCourse_summary(resultset.getString(4));
                course.setCourse_picture_id(resultset.getBigDecimal(5));
                course.setCourse_catalog_id(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                course.setCourse_addtime(ddtf.format(resultset.getTimestamp(7)));
                course.setCourse_profession_id(resultset.getInt(8));
                course.setCourse_degree(resultset.getInt(9));
//                DebugUtils.showLog(course.toString());
                courseList.add(course);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return courseList;
    }

    @Override
    public List<CourseBean> getAllCourseByProfessionId(int pression_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<CourseBean> courseList = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `courses` WHERE course_profession_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pression_id);
            resultset = statement.executeQuery();
            courseList = new ArrayList<CourseBean>();
            while (resultset.next()) {
                CourseBean course = new CourseBean();
                course.setCourse_id(resultset.getInt(1));
                course.setCourse_name(resultset.getString(2));
                course.setCourse_applypeople(resultset.getString(3));
                course.setCourse_summary(resultset.getString(4));
                course.setCourse_picture_id(resultset.getBigDecimal(5));
                course.setCourse_catalog_id(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                course.setCourse_addtime(ddtf.format(resultset.getTimestamp(7)));
                course.setCourse_profession_id(resultset.getInt(8));
                course.setCourse_degree(resultset.getInt(9));
//                DebugUtils.showLog(course.toString());
                courseList.add(course);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return courseList;
    }
    
    @Override
    public CourseBean getCourseById(int course_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        CourseBean course = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `courses` WHERE course_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, course_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                course = new CourseBean();
                course.setCourse_id(resultset.getInt(1));
                course.setCourse_name(resultset.getString(2));
                course.setCourse_applypeople(resultset.getString(3));
                course.setCourse_summary(resultset.getString(4));
                course.setCourse_picture_id(resultset.getBigDecimal(5));
                course.setCourse_catalog_id(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                course.setCourse_addtime(ddtf.format(resultset.getTimestamp(7)));
                course.setCourse_profession_id(resultset.getInt(8));
                course.setCourse_degree(resultset.getInt(9));
//                DebugUtils.showLog(course.toString());
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return course;
    }
}
