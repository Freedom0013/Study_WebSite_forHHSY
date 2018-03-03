package com.studyplatform.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求课程列表Servlet
 * Title: CourseServlet
 * @date 2018年3月2日
 * @author Freedom0013
 */
public class CourseServlet extends HttpServlet {

    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void init() throws ServletException {
    
    }
    
    public CourseServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

}
