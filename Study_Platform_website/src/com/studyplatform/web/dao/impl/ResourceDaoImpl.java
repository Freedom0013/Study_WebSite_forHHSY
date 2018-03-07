package com.studyplatform.web.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.studyplatform.web.bean.ResourceBean;
import com.studyplatform.web.dao.ResourceDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * ResourceDao实现类
 * Title: ResourceDaoImpl
 * @date 2018年2月27日
 * @author Freedom0013
 */
public class ResourceDaoImpl implements ResourceDao {
    @Override
    public int addResource(ResourceBean resourcebean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updataResource(ResourceBean resourcebean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ResourceBean> getAllResourceByCourseId(int course_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<ResourceBean> resourcelist = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `resources` WHERE resource_course_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, course_id);
            resultset = statement.executeQuery();
            resourcelist = new ArrayList<ResourceBean>();
            while (resultset.next()) {
                ResourceBean resource = new ResourceBean();
                resource.setResource_id(resultset.getBigDecimal(1));
                resource.setResource_name(resultset.getString(2));
                resource.setResource_detail(resultset.getString(3));
                resource.setResource_type(resultset.getInt(4));
                resource.setResource_caption(resultset.getString(5));
                resource.setResource_degree(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                resource.setResource_addtime(ddtf.format(resultset.getTimestamp(7)));
                resource.setResource_course_id(resultset.getInt(8));
                resource.setResource_picture_id(resultset.getBigDecimal(9));
//                DebugUtils.showLog(resource.toString());
                resourcelist.add(resource);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return resourcelist;
    }

    @Override
    public List<ResourceBean> getAllbyDegree(int resource_degree ,int course_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<ResourceBean> resourcelist = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `resources` WHERE resource_course_id = ? AND resource_degree = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, course_id);
            statement.setInt(2, resource_degree);
            resultset = statement.executeQuery();
            resourcelist = new ArrayList<ResourceBean>();
            while (resultset.next()) {
                ResourceBean resource = new ResourceBean();
                resource.setResource_id(resultset.getBigDecimal(1));
                resource.setResource_name(resultset.getString(2));
                resource.setResource_detail(resultset.getString(3));
                resource.setResource_type(resultset.getInt(4));
                resource.setResource_caption(resultset.getString(5));
                resource.setResource_degree(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                resource.setResource_addtime(ddtf.format(resultset.getTimestamp(7)));
                resource.setResource_course_id(resultset.getInt(8));
                resource.setResource_picture_id(resultset.getBigDecimal(9));
//                DebugUtils.showLog(resource.toString());
                resourcelist.add(resource);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return resourcelist;
    }

    @Override
    public ResourceBean getResourceById(BigDecimal resource_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResourceBean resource = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `resources` WHERE resource_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, resource_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                resource = new ResourceBean();
                resource.setResource_id(resultset.getBigDecimal(1));
                resource.setResource_name(resultset.getString(2));
                resource.setResource_detail(resultset.getString(3));
                resource.setResource_type(resultset.getInt(4));
                resource.setResource_caption(resultset.getString(5));
                resource.setResource_degree(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                resource.setResource_addtime(ddtf.format(resultset.getTimestamp(7)));
                resource.setResource_course_id(resultset.getInt(8));
                resource.setResource_picture_id(resultset.getBigDecimal(9));
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return resource;
    }

    @Override
    public List<ResourceBean> getAllResourceListByPage(int page_Num, int page_Size) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<ResourceBean> resourcelist = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM resources LIMIT ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (page_Num-1)*page_Size);
            statement.setInt(2, page_Size*page_Num);
            resultset = statement.executeQuery();
            resourcelist = new ArrayList<ResourceBean>();
            while (resultset.next()) {
                ResourceBean resource = new ResourceBean();
                resource.setResource_id(resultset.getBigDecimal(1));
                resource.setResource_name(resultset.getString(2));
                resource.setResource_detail(resultset.getString(3));
                resource.setResource_type(resultset.getInt(4));
                resource.setResource_caption(resultset.getString(5));
                resource.setResource_degree(resultset.getInt(6));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                resource.setResource_addtime(ddtf.format(resultset.getTimestamp(7)));
                resource.setResource_course_id(resultset.getInt(8));
                resource.setResource_picture_id(resultset.getBigDecimal(9));
//                DebugUtils.showLog(resource.toString());
                resourcelist.add(resource);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return resourcelist;
    }

    @Override
    public int deleteResource(BigDecimal resource_id) {
        // TODO Auto-generated method stub
        return 0;
    }
}
