package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.service.UserService;
import com.studyplatform.web.service.impl.UserServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

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
        DebugUtils.showLog("页面接收用户名：" + name +",密码：" + pass);
      //初步判断是否为空
        if (name.equals(null) || name == "") {
            request.getSession().setAttribute("errormessage", "用户名不能为空!");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else if (pass.equals(null) || pass == "") {
            request.getSession().setAttribute("errormessage", "密码不能为空!");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            UserService us = new UserServiceImpl();
            UserBean user = us.login(name, pass);
            if (user != null) {
                // 合法用户
//                DebugUtils.showLog("合法用户");
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/servlet/DepartmentServlet").forward(request, response);
            } else {
                // 非法用户
//                DebugUtils.showLog("非法用户");
                request.getSession().setAttribute("errormessage", "用户名或密码错误请重新输入！");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
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
