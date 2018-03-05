package com.studyplatform.web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.studyplatform.web.utils.DebugUtils;

/**
 * 手动数据库链接类
 * Title: ConnectToDataBase
 * @date 2018年1月21日
 * @author Freedom0013
 */
public class ConnectToDataBase {
	/**
	 * 获取数据库连接
	 * Title: getDataBaseConnection
	 * @return 连接对象
	 */
	public static Connection getDataBaseConnection() {
		//数据库信息
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/study_platform?useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String password = "root";

		// 加载驱动程序
		try {
			Class.forName(driver);
			DebugUtils.showLog("驱动程序加载成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 链接数据库
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				DebugUtils.showLog("连接数据库成功！");
			}
		} catch (SQLException e) {
			DebugUtils.showLog("链接数据库失败: " + e.getMessage());
		}

		return conn;
	}
}