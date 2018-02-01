package com.studyplatform.web.dao.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.dao.impl.UserDaoImpl;

import junit.framework.Assert;

/**
 * Junit测试类
 * Title: DaoImplJunitTest
 * @date 2018年2月1日
 * @author Freedom0013
 */
public class DaoImplJunitTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testAdd() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testFindUserByUserNameAndPassword() {
//        fail("Not yet implemented");
//    }

    @Test
    public void testFindUserByUserName() {
        UserDaoImpl ud = new UserDaoImpl();
        UserBean user = ud.findUserByUserName("1111");
        Assert.assertNotNull(user);  
    }
}
