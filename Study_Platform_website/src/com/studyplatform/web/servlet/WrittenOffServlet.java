package com.studyplatform.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户注销
 * Title: WrittenOffServlet
 * @date 2018年2月26日
 * @author Freedom0013
 */
public class WrittenOffServlet extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //false代表：不创建session对象，只是从request中获取。  
        HttpSession session = request.getSession(false);  
        if(session==null){  
            return;  
        }  
        session.removeAttribute("user");  
        //重定向到主页
        response.sendRedirect(request.getContextPath() + "/servlet/DepartmentServlet");  
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void init() throws ServletException {
 
    }
    
    public WrittenOffServlet() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }
}
