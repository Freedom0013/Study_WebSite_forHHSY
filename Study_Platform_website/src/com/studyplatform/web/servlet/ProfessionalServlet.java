package com.studyplatform.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.bean.ProfessionBean;
import com.studyplatform.web.service.DepartmentService;
import com.studyplatform.web.service.ProfessionalService;
import com.studyplatform.web.service.impl.DepartmentServiceImpl;
import com.studyplatform.web.service.impl.ProfessionalServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 请求专业列表Servlet
 * Title: ProfessionalServlet
 * @date 2018年3月2日
 * @author Freedom0013
 */
public class ProfessionalServlet extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        WebUtils.setCharSet(request, response);
        
        String department_id = (String) request.getParameter("department_id");
        int departmentid = Integer.parseInt(department_id);
        
        ProfessionalService service = new ProfessionalServiceImpl();
        
        ArrayList<ProfessionBean> professionlist = (ArrayList<ProfessionBean>) service.getAllProfessionsByDepId(departmentid);
        JSONObject profession_json = new JSONObject();
        profession_json.element("root", JSONArray.fromObject(professionlist));
        DebugUtils.showLog(profession_json.toString());
        
        request.setAttribute("profession_list_json",profession_json.toString());
        request.setAttribute("department_id", departmentid);
        
        DepartmentService department_service = new DepartmentServiceImpl();
        ArrayList<DepartmentBean> departmentlist = (ArrayList<DepartmentBean>) department_service.getallDepartment();
        JSONObject department_json = new JSONObject();
        department_json.element("root", JSONArray.fromObject(departmentlist));
        request.setAttribute("all_department_list", department_json.toString());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/professional_detail.jsp");
        dispatcher.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void init() throws ServletException {
    }
    
    public ProfessionalServlet() {
        super();
    }
    
    public void destroy() {
        super.destroy(); 
    }
}
