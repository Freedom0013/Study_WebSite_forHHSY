package com.studyplatform.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.exception.UserExistException;
import com.studyplatform.web.service.UserService;
import com.studyplatform.web.service.impl.UserServiceImpl;
import com.studyplatform.web.servlet.formbean.UserFormBean;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.MD5Utils;
import com.studyplatform.web.utils.WebUtils;

/**
 * 用户注册Servlet
 * Title: RegisterServlet
 * @date 2018年2月26日
 * @author Freedom0013
 */
public class RegisterServlet extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 检查token，这里如果不理解可以不管
        long token = Long.parseLong(request.getParameter("token"));
        long tokenInSession = Long.parseLong(request.getSession().getAttribute("token") + "");
//        DebugUtils.showLog("token："+token +",tokenInSession:"+tokenInSession);
        
//        for(String key:request.getParameterMap().keySet()){
//            DebugUtils.showLog(key);
//        }
//        for(String[] value:request.getParameterMap().values()){
//            for(int i = 0;i<value.length;i++){
//                DebugUtils.showLog(value[i]);
//            }
//        }
        if (token == tokenInSession) {
            response.getWriter().println("ok"); // 如果是第一次请求，则产生新的token
            request.getSession().setAttribute("token", System.currentTimeMillis());
        } else {
            response.getWriter().println("do not repeat submit");
        }
        
        //封装数据到userbean中
        UserFormBean ufb = WebUtils.fillFormBean(UserFormBean.class, request);
        DebugUtils.showLog("拷贝："+ufb.toString());
        ufb.setToken(WebUtils.transferLongToDate("yyyy-MM-dd HH:mm:ss", token));
        
        //验证数据
        if (ufb.validate()) {
            //验证通过
            //将formbean中的数据拷贝到UserBean对象
            UserBean user = new UserBean();
            user.setUser_name(ufb.getRegusername());
            user.setUser_password(MD5Utils.getMD5(ufb.getRegpassword()));
            user.setUser_register_time(ufb.getToken());
            user.setUser_nickname(ufb.getNickname());
            user.setUser_lastlogin_time(ufb.getToken());
            BigDecimal volumn = new BigDecimal("0");
            user.setUser_picture_id(volumn);
            user.setUser_qq(volumn);
            user.setUser_status(1);
            user.setUser_admin_flag(1);
            user.setUser_integral(0);
            DebugUtils.showLog("拷贝："+user.toString());
            // 第四步,注册用户
            // 调用业务逻辑层完成注册
            UserService us = new UserServiceImpl();
            try {
                us.register(user);
                // 注册成功
                // 返回登陆页面
                response.getWriter().write("恭喜你，注册成功，2秒后转向登陆页面");
                response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/login.jsp");
            } catch (UserExistException e) {
                // 说明用户已经注册过了
                ufb.getErrors().put("username", "此用户名已经被注册过了，请换一个");
                // 将ufb存入request对象
                request.setAttribute("user", ufb);
                request.getRequestDispatcher(request.getContextPath() + "/login.jsp#toregister").forward(request, response);
            }
        } else {
            // 验证不通过
            // 完成数据的回显操作，把ufb对象存放到request
            request.setAttribute("user", ufb);
            request.getRequestDispatcher(request.getContextPath() + "/login.jsp#toregister").forward(request, response);
        }
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void init() throws ServletException {
      
    }
    
    public RegisterServlet() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }
}
