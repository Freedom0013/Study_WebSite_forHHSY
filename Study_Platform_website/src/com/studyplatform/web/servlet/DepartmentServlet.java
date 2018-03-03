package com.studyplatform.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.service.DepartmentService;
import com.studyplatform.web.service.impl.DepartmentServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 请求院系列表Servlet
 * Title: DepartmentServlet
 * @date 2018年3月2日
 * @author Freedom0013
 */
public class DepartmentServlet extends HttpServlet {

    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        WebUtils.setCharSet(request, response);
        
        //调用业务逻辑
        DepartmentService service = new DepartmentServiceImpl();
        
        ArrayList<DepartmentBean> departmentlist = (ArrayList<DepartmentBean>) service.getallDepartment();
        JSONObject department_json = new JSONObject();
        department_json.element("root", JSONArray.fromObject(departmentlist));
        DebugUtils.showLog(department_json.toString());
        response.getWriter().write(department_json.toString()); 
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void init() throws ServletException {
    
    }
    
    public DepartmentServlet() {
        super();
    }
    
    public void destroy() {
        super.destroy(); 
    }
}
