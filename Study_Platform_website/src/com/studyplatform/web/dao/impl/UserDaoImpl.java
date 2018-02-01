package com.studyplatform.web.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.dao.UserDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * UserDao实现类
 * Title: UserDaoImpl
 * @date 2018年2月1日
 * @author Freedom0013
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void add(UserBean user) {

    }

    @Override
    public UserBean findUserByUserNameAndPassword(String username, String password) {

        return null;
    }

    @Override
    public UserBean findUserByUserName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        UserBean user = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
//            String sql = "SELECT * FROM `users` WHERE user_name='" + name + "'";
            String sql = "SELECT * FROM `users` WHERE user_name=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            DebugUtils.showLog("查询语句："+sql);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                user = new UserBean();
                user.setUser_id(resultset.getInt(1));
                user.setUser_picture_id(resultset.getBigDecimal(2));
                user.setUser_name(resultset.getString(3));
                user.setUser_password(resultset.getString(4));
                user.setUser_nickname(resultset.getString(5));
//                String registtime = resultset.getString(6);
//                registtime.substring(0, registtime.length() - 3);
                user.setUser_register_time(resultset.getString(6));
                user.setUser_realname(resultset.getString(7));
                user.setUser_age(resultset.getInt(8));
                user.setUser_gendar(resultset.getInt(9));
                user.setUser_phone(resultset.getString(10));
                user.setUser_email(resultset.getString(11));
                user.setUser_fraction_id(resultset.getInt(12));
                user.setUser_university(resultset.getString(13));
                user.setUser_integral(resultset.getInt(14));
                user.setUser_city(resultset.getString(15));
                user.setUser_qq(resultset.getBigDecimal(16));
//                String lasttime = resultset.getString(17);
//                lasttime.substring(0, lasttime.length() - 3);
                user.setUser_lastlogin_time(resultset.getString(17));
                user.setUser_grade(resultset.getString(18));
                user.setUser_status(resultset.getInt(19));
                user.setUser_admin_flag(resultset.getInt(20));
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            // 关闭结果集
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭Statement对象
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }
}
