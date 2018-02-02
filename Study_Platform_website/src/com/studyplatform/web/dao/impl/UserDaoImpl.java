package com.studyplatform.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.dao.UserDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.MD5Utils;

/**
 * UserDao实现类
 * Title: UserDaoImpl
 * @date 2018年2月1日
 * @author Freedom0013
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int add(UserBean user) {
     // 获取数据库连接
        Connection connection = null;
        // 执行插入
        // 获取操作SQL语句的Statement对象：
        // 调用Connection的createStatement()方法来创建Statement的对象
        PreparedStatement statement = null;
        try {
            connection = C3p0Utils.getConnection();
            // 准备插入的SQL语句
//            String sql = "INSERT INTO users (user_picture_id, user_name, user_password,user_nickname,"
//                    + "user_register_time,user_realname,user_age,user_gendar,"
//                    + "user_phone,user_email,user_fraction_id,user_university,user_integral,"
//                    + "user_city,user_qq,user_lastlogin_time,user_grade,user_status,user_admin_flag) "
//                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            DebugUtils.showLog("添加语句："+sql);
//            statement = connection.prepareStatement(sql);
//            statement.setBigDecimal(1, user.getUser_picture_id());
//            statement.setString(2, user.getUser_name());
//            statement.setString(3, user.getUser_password());
//            statement.setString(4, user.getUser_nickname());
//            statement.setString(5, user.getUser_register_time());
//            statement.setString(6, user.getUser_realname());
//            statement.setInt(7, user.getUser_age());
//            statement.setInt(8, user.getUser_gendar());
//            statement.setString(9, user.getUser_phone());
//            statement.setString(10, user.getUser_email());
//            statement.setInt(11, user.getUser_fraction_id());
//            statement.setString(12, user.getUser_university());
//            statement.setInt(13, user.getUser_integral());
//            statement.setString(14, user.getUser_city());
//            statement.setBigDecimal(15, user.getUser_qq());
//            statement.setString(16, user.getUser_lastlogin_time());
//            statement.setString(17, user.getUser_grade());
//            statement.setInt(18, user.getUser_status());
//            statement.setInt(19, user.getUser_admin_flag());
            String sql = "INSERT INTO users (user_picture_id, user_name, user_password,user_nickname,"
                    + "user_register_time,user_realname,user_age,user_gendar,"
                    + "user_phone,user_email,user_fraction_id,user_university,user_integral,"
                    + "user_city,user_qq,user_lastlogin_time,user_grade,user_status,user_admin_flag) " + "VALUES ('"
                    + user.getUser_picture_id() + "','" 
                    + user.getUser_name() + "','" 
                    + user.getUser_password() + "','"
                    + user.getUser_nickname() + "','" 
                    + user.getUser_register_time() + "','" 
                    + user.getUser_realname() + "','" 
                    + user.getUser_age() + "','" 
                    + user.getUser_gendar() + "','" 
                    + user.getUser_phone() + "','"
                    + user.getUser_email() + "','" 
                    + user.getUser_fraction_id() + "','" 
                    + user.getUser_university() + "','" 
                    + user.getUser_integral() + "','" 
                    + user.getUser_city() + "','" 
                    + user.getUser_qq() + "','"
                    + user.getUser_lastlogin_time() + "','" 
                    + user.getUser_grade() + "','" 
                    + user.getUser_status() + "','" 
                    + user.getUser_admin_flag() + "')";
            DebugUtils.showLog("添加语句：" + sql);
            statement = connection.prepareStatement(sql);
            // 调用Statement对象的executeUpdate(sql) 执行SQL 语句的插入
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
    public UserBean findUserByUserNameAndPassword(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        UserBean user = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
//            String sql = "SELECT * FROM `users` WHERE user_name='" + username
//                    + "' AND user_password='" + password + "'";
            String sql = "SELECT * FROM `users` WHERE user_name=? AND user_password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, MD5Utils.getMD5(password));
            DebugUtils.showLog("登录查询语句："+sql);
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
                DebugUtils.showLog("UserBean"+user.toString());
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
//                user.setUser_register_time(resultset.getTimestamp(17).toLocaleString());
                //此处从Mysql中取出的DateTime字段精确到毫秒，会多出一个毫秒的.0，故要使用DateFormat进行格式化
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                user.setUser_register_time(ddtf.format(resultset.getTimestamp(17)));
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
                user.setUser_lastlogin_time(ddtf.format(resultset.getTimestamp(17)));
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
