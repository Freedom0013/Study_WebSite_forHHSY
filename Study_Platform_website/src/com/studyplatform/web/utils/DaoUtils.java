package com.studyplatform.web.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据层工具类
 * Title: DaoUtils
 * @date 2018年2月22日
 * @author Freedom0013
 */
public class DaoUtils {
    /**
     * 关闭资源
     * Title: closeResource
     */
    public static void closeResource(Connection connection, Statement statement, ResultSet resultset) {
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
}
