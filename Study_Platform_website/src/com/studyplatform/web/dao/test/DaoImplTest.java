package com.studyplatform.web.dao.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.config.SystemCommonValue;
import com.studyplatform.web.dao.UserDao;
import com.studyplatform.web.dao.impl.UserDaoImpl;
import com.studyplatform.web.utils.DebugUtils;

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
        UserDao ud = new UserDaoImpl();
        //根据姓名查找
//        UserBean user = ud.findUserByUserName("admin");
//        DebugUtils.showLog(user.toString());
        //登录
//        UserBean userLogin = ud.findUserByUserNameAndPassword("admin","admin");
//        DebugUtils.showLog(userLogin.toString());
        
        
        //注册
        //BigDecimal转Int
//        BigDecimal a=new BigDecimal(12.88);
//        int b=a.intValue();
//        DebugUtils.showLog(b);//b=12; 
        
        //Int转BigDecimal
        int picture_id = 0;
        BigDecimal big_picture_id = new BigDecimal(picture_id);
        //获取当前时间
        Date time=new Date();    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        DebugUtils.showLog(df.format(time));   
        int qq = 587458548;
        BigDecimal big_qq = new BigDecimal(qq);
        //注册测试
        UserBean addUser = new UserBean(big_picture_id,"dogeee","123456789","小鸟",df.format(time).toString(),"张婷",20,0,"15847854854","888444555@gmail.com",0,"陕师大",0,"西安",big_qq,df.format(time).toString(),"大二",1,1);
        int success = ud.add(addUser);
        if(success == SystemCommonValue.OPERATION_SUCCESS){
            DebugUtils.showLog("插入执行成功！");
        }
    }
}
