package com.studyplatform.web.dao.test;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.dao.impl.UserDaoImpl;
import com.studyplatform.web.utils.DebugUtils;

/**
 * Dao测试类
 * Title: DaoImplTest
 * @date 2018年2月1日
 * @author Freedom0013
 */
public class DaoImplTest {
    public static void main(String[] args) {
        UserDaoImpl ud = new UserDaoImpl();
        UserBean user = ud.findUserByUserName("admin");
        DebugUtils.showLog(user.toString());
    }
}
