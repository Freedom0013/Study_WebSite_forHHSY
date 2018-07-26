package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.exception.UserExistException;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.UserService;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.service.impl.UserServiceImpl;
import com.studyplatform.web.servlet.formbean.UserFormBean;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.MD5Utils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * Title: RegisterAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class RegisterAction extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 控制字符集
        WebUtils.setCharSet(request, response);
        PrintWriter out = response.getWriter();

        String userJson = request.getParameter("userJson");
        String tokenstr = request.getParameter("token");
        long token = Long.valueOf(tokenstr).longValue();
        Gson gson = new Gson();
        UserBean bean = gson.fromJson(userJson, UserBean.class);
        // 封装数据到userbean中
        UserFormBean ufb = new UserFormBean();
        ufb.setRegusername(bean.getUser_name());
        ufb.setRegpassword(bean.getUser_password());
        ufb.setSec_regpassword(bean.getUser_password());
        ufb.setNickname(bean.getUser_nickname());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(token);
        ufb.setToken(sdf.format(date));

        // 封装数据
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        int responseCode = 0;
        // 验证数据
        if (ufb.validate()) {
            // 验证通过
            // 将formbean中的数据拷贝到UserBean对象
            UserBean user = new UserBean();
            user.setUser_name(ufb.getRegusername());
            user.setUser_password(MD5Utils.getMD5(ufb.getRegpassword()));
            user.setUser_register_time(ufb.getToken());
            user.setUser_nickname(ufb.getNickname());
            user.setUser_lastlogin_time(ufb.getToken());
            BigDecimal volumn = new BigDecimal("6");
            user.setUser_picture_id(volumn);
            user.setUser_qq(volumn);
            user.setUser_status(1);
            user.setUser_admin_flag(1);
            user.setUser_integral(0);
            // 第四步,注册用户
            // 调用业务逻辑层完成注册
            UserService us = new UserServiceImpl();
            try {
                DebugUtils.showLog("注册");
                us.register(user);
                // 注册成功
                responseCode = 0;
                // 合法用户
                JSONObject user_json = new JSONObject();
                user_json.element("userdata", JSONObject.fromObject(user));
                data_array.add(user_json);
                // 获取用户图片
                PictureService pic_service = new PictureServiceImpl();
                PictureBean pic = pic_service.getPictureById(user.getUser_picture_id());
                JSONObject pic_json = new JSONObject();
                pic_json.element("pic", JSONObject.fromObject(pic));
                data_array.add(pic_json);
            } catch (UserExistException e) {
                responseCode = 1;
                JSONObject errormessage_json = new JSONObject();
                errormessage_json.element("errormessage", "此用户名已经被注册过了，请重新输入！");
                data_array.add(errormessage_json);
            }
        } else {
            // 验证不通过
            // 完成数据的回显操作，把ufb对象存放到request
            HashMap<String,String> errors = (HashMap<String, String>) ufb.getErrors();
            responseCode = 1;
            JSONObject errormessage_json = new JSONObject();
            for(String errorindex :errors.keySet()){
                String error = errors.get(errorindex);
                if(error != null){
                    errormessage_json.element("errormessage", error);
                }
            }
            data_array.add(errormessage_json);
        }

        data.element("responseCode", responseCode);
        data.element("data", data_array);
        DebugUtils.showLog(data.toString());

        out.write(data.toString());
        out.flush();
        out.close();
    }

    public RegisterAction() {
        super();
    }
    public void destroy() {
        super.destroy(); 
    }

    public void init() throws ServletException {
    }
}
