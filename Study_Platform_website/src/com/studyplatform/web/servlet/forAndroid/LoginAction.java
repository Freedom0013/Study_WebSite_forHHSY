package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.UserService;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.service.impl.UserServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * Title: LoginAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class LoginAction extends HttpServlet {
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

        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        DebugUtils.showLog("页面接收用户名：" + name + ",密码：" + pass);

        // 封装数据
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        int responseCode = 0;

        // 初步判断是否为空
        if (name.equals(null) || name == "") {
            responseCode = 2;
            JSONObject errormessage_json = new JSONObject();
            errormessage_json.element("errormessage", "用户名不能为空!");
            data_array.add(errormessage_json);
        } else if (pass.equals(null) || pass == "") {
            responseCode = 3;
            JSONObject errormessage_json = new JSONObject();
            errormessage_json.element("errormessage", "密码不能为空!");
            data_array.add(errormessage_json);
        } else {
            UserService us = new UserServiceImpl();
            UserBean user = us.login(name, pass);
            if (user != null) {
                // 合法用户
                JSONObject user_json = new JSONObject();
                user_json.element("userdata", JSONObject.fromObject(user));
                data_array.add(user_json);
                responseCode = 0;
                // 获取用户图片
                PictureService pic_service = new PictureServiceImpl();
                PictureBean pic = pic_service.getPictureById(user.getUser_picture_id());
                JSONObject pic_json = new JSONObject();
                pic_json.element("pic", JSONObject.fromObject(pic));
                data_array.add(pic_json);
            } else {
                // 非法用户
                JSONObject errormessage_json = new JSONObject();
                errormessage_json.element("errormessage", "手机号或密码错误请重新输入！");
                responseCode = 1;
                data_array.add(errormessage_json);
            }
        }

        data.element("responseCode", responseCode);
        data.element("data", data_array);
        DebugUtils.showLog(data.toString());

        out.write(data.toString());
        out.flush();
        out.close();
    }

    public LoginAction() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }

    public void init() throws ServletException {
    }
}
