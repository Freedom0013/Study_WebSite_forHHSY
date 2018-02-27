package com.studyplatform.web.dao.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.studyplatform.web.bean.CourseBean;
import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.bean.FractionBean;
import com.studyplatform.web.bean.ProfessionBean;
import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.dao.CourseDao;
import com.studyplatform.web.dao.DepartmentDao;
import com.studyplatform.web.dao.FractionDao;
import com.studyplatform.web.dao.ProfessionalDao;
import com.studyplatform.web.dao.QuestionDao;
import com.studyplatform.web.dao.UserDao;
import com.studyplatform.web.dao.impl.CourseDaoImpl;
import com.studyplatform.web.dao.impl.DepartmentDaoImpl;
import com.studyplatform.web.dao.impl.FractionDaoImpl;
import com.studyplatform.web.dao.impl.ProfessionalDapImpl;
import com.studyplatform.web.dao.impl.QuestionDaoImpl;
import com.studyplatform.web.dao.impl.UserDaoImpl;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DebugUtils;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Dao测试类（冗余）
 * Title: DaoImplTest
 * @date 2018年2月1日
 * @author Freedom0013
 */
public class DaoImplTest {
    /**
     * 测试
     */
    public static void main(String[] args) {
//---------------------------UserDao-------------------------------------
//        UserDao ud = new UserDaoImpl();
//        //根据姓名查找
////        UserBean user = ud.findUserByUserName("admin");
////        DebugUtils.showLog(user.toString());
//        //登录
////        UserBean userLogin = ud.findUserByUserNameAndPassword("admin","admin");
////        DebugUtils.showLog(userLogin.toString());
//        
//        
//        //注册
//        //BigDecimal转Int
////        BigDecimal a=new BigDecimal(12.88);
////        int b=a.intValue();
////        DebugUtils.showLog(b);//b=12; 
//        
//        //Int转BigDecimal
//        int picture_id = 0;
//        BigDecimal big_picture_id = new BigDecimal(picture_id);
//        //获取当前时间
//        Date time=new Date();    
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//        DebugUtils.showLog(df.format(time));   
//        int qq = 587458548;
//        BigDecimal big_qq = new BigDecimal(qq);
//        //注册测试
//        UserBean addUser = new UserBean(0,big_picture_id,"xixixixi","987654321","胡一菲",df.format(time).toString(),"娄艺潇",28,0,"15965884578","11223344@gmail.com",0,"上海戏剧学院",0,"上海",big_qq,df.format(time).toString(),"大四",1,1);
//        
//        int success = ud.add(addUser);    //新增
////        int success = ud.UpdataUser(addUser);   //更新
//        if(success == SystemCommonValue.OPERATION_SUCCESS){
//            DebugUtils.showLog("执行成功！");
//        }
//        
//-----------------------QuestionDao---------------------------        
//        QuestionDao dao = new QuestionDaoImpl();
//        dao.ObtainExaminationList(1, 4, 3, 3);
        
//--------------------------DepartmentDao----------------------        
//        DepartmentDao dao = new DepartmentDaoImpl();
//        ArrayList<DepartmentBean> list = (ArrayList<DepartmentBean>) dao.getAllDepartment();
//        for(DepartmentBean bean:list){
//            DebugUtils.showLog("test:::"+bean.toString());
//        }
//        DepartmentBean bean = dao.getDepartmentById(1);
//        DebugUtils.showLog("testbean:::"+bean.toString());

//-----------------------------ProfessionalDao-----------------------
//        ProfessionalDao dao = new ProfessionalDapImpl();
//        ArrayList<ProfessionBean> list = (ArrayList<ProfessionBean>) dao.getAllProfession();
//        for (ProfessionBean bean : list) {
//            DebugUtils.showLog("test1:::" + bean.toString());
//        }
//        
//        ArrayList<ProfessionBean> list1 = (ArrayList<ProfessionBean>) dao.getAllProfessionByDepartmentId(7);
//        for (ProfessionBean bean : list1) {
//            DebugUtils.showLog("test2:::" + bean.toString());
//        }
//        ProfessionBean bean = dao.getProfessionById(1);
//        DebugUtils.showLog("testbean3:::" + bean.toString());

//-----------------------------ProfessionalDao-----------------------
//      CourseDao dao = new CourseDaoImpl();
//      ArrayList<CourseBean> list = (ArrayList<CourseBean>) dao.getAllCourse();
//      for (CourseBean bean : list) {
//          DebugUtils.showLog("test1:::" + bean.toString());
//      }
//      
//      ArrayList<CourseBean> list1 = (ArrayList<CourseBean>) dao.getAllCourseByProfessionId(2);
//      for (CourseBean bean : list1) {
//          DebugUtils.showLog("test2:::" + bean.toString());
//      }
//      CourseBean bean = dao.getCourseById(1);
//      DebugUtils.showLog("testbean3:::" + bean.toString());
        
//-------------------------------FractionDao--------------------------
//        FractionDao dao = new FractionDaoImpl();
//        Date time=new Date();    
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//        FractionBean testbean = new FractionBean(53,df.format(time),1,8);
//        
//        int re = dao.addFraction(testbean);
//        if(re == SystemCommonValue.OPERATION_SUCCESS){
//            DebugUtils.showLog("添加成功");
//        }else{
//            DebugUtils.showLog("添加失败");
//        }
//        ArrayList<FractionBean> list = (ArrayList<FractionBean>) dao.getAllFractionByUserId(1);
//        for(FractionBean bean : list){
//            DebugUtils.showLog("test1:::"+bean.toString());
//        }
//        
//        FractionBean bean = dao.getFractionByFractionId(11);
//        DebugUtils.showLog("test2:::"+bean.toString());
        
    }
}
