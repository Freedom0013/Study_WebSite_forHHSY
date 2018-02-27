package com.studyplatform.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.dao.DepartmentDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * DepartmentDao实现类
 * Title: DepartmentDaoImpl
 * @date 2018年2月27日
 * @author Freedom0013
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public int addDepartment(DepartmentBean departmentbean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteDepartment(int department_id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updataDepartment(DepartmentBean departmentbean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<DepartmentBean> getAllDepartment() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<DepartmentBean> departmentList = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `departments`";
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery();
            departmentList = new ArrayList<DepartmentBean>();
            while (resultset.next()) {
                DepartmentBean department = new DepartmentBean();
                department.setDepartment_id(resultset.getInt(1));
                department.setDepartment_name(resultset.getString(2));
                department.setDepartment_picture_id(resultset.getBigDecimal(3));
                department.setDepartment_caption(resultset.getString(4));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                department.setDepartment_addtime(ddtf.format(resultset.getTimestamp(5))); 
//                DebugUtils.showLog(department.toString());
                departmentList.add(department);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return departmentList;
    }

    @Override
    public DepartmentBean getDepartmentById(int department_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        DepartmentBean department = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `departments` WHERE department_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, department_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                department = new DepartmentBean();
                department.setDepartment_id(resultset.getInt(1));
                department.setDepartment_name(resultset.getString(2));
                department.setDepartment_picture_id(resultset.getBigDecimal(3));
                department.setDepartment_caption(resultset.getString(4));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                department.setDepartment_addtime(ddtf.format(resultset.getTimestamp(5))); 
                DebugUtils.showLog(department.toString());
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return department;
    }
}
