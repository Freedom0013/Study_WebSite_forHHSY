package com.studyplatform.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.studyplatform.web.bean.FractionBean;
import com.studyplatform.web.dao.FractionDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * FractionDao实现类
 * Title: FractionDaoImpl
 * @date 2018年2月27日
 * @author Freedom0013
 */
public class FractionDaoImpl implements FractionDao {
    @Override
    public int addFraction(FractionBean fractionbean) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "INSERT INTO fractions (fraction_content,fraction_addtime,fraction_course_id,fraction_user_id) "
                    + "VALUES ('"
                    +fractionbean.getFraction_content()+"','"
                    +fractionbean.getFraction_addtime()+"','"
                    +fractionbean.getFraction_course_id()+"','"
                    +fractionbean.getFraction_user_id()+"')";
            DebugUtils.showLog("添加语句：" + sql);
            statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return SystemCommonValue.OPERATION_FAILED;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemCommonValue.OPERATION_FAILED;
        } finally {
            // 关闭Statement对象
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return SystemCommonValue.OPERATION_FAILED;
                }
            }
            if (connection != null) {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return SystemCommonValue.OPERATION_FAILED;
                }
            }
        }
        return SystemCommonValue.OPERATION_SUCCESS;
    }

    @Override
    public int deleteFraction(int fraction_id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updataFraction(FractionBean fractionbean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public FractionBean getFractionByFractionId(int fraction_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        FractionBean fraction = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `fractions` WHERE fraction_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, fraction_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                fraction = new FractionBean();
                fraction.setFraction_id(resultset.getBigDecimal(1));
                fraction.setFraction_content(resultset.getInt(2));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                fraction.setFraction_addtime(ddtf.format(resultset.getTimestamp(3)));
                fraction.setFraction_course_id(resultset.getInt(4));
                fraction.setFraction_user_id(resultset.getInt(5));
//                DebugUtils.showLog(fraction.toString());
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return fraction;
    }

    @Override
    public List<FractionBean> getAllFractionByUserId(int fraction_user_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<FractionBean> fractionList = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `fractions` WHERE fraction_user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, fraction_user_id);
            resultset = statement.executeQuery();
            fractionList = new ArrayList<FractionBean>();
            while (resultset.next()) {
                FractionBean fraction = new FractionBean();
                fraction.setFraction_id(resultset.getBigDecimal(1));
                fraction.setFraction_content(resultset.getInt(2));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                fraction.setFraction_addtime(ddtf.format(resultset.getTimestamp(3)));
                fraction.setFraction_course_id(resultset.getInt(4));
                fraction.setFraction_user_id(resultset.getInt(5));
//                DebugUtils.showLog(fraction.toString());
                fractionList.add(fraction);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return fractionList;
    }
}
