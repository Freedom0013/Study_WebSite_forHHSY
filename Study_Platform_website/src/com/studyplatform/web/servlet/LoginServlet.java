package com.studyplatform.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录处理Servlet
 * Title: LoginServlet
 * @date 2018年2月23日
 * @author Freedom0013
 */
public class LoginServlet extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 3L;
    
    /**
     * 处理get请求
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        
    }
    
    /**
     * 处理post请求
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public LoginServlet() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }

    
    public void init() throws ServletException {
        
    }

}
