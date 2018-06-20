package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.DepartmentBean;
import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.service.DepartmentService;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.impl.DepartmentServiceImpl;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * Title: DepartmentAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class DepartmentAction extends HttpServlet {
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
        
        //调用业务逻辑
        DepartmentService service = new DepartmentServiceImpl();
        
        //获取所有大类信息列表
        ArrayList<DepartmentBean> departmentlist = (ArrayList<DepartmentBean>) service.getallDepartment();
        
        //获取大类对应图片信息列表
        PictureService pic_service = new PictureServiceImpl();
        ArrayList<PictureBean> pic_list = new ArrayList<PictureBean>();
        for(DepartmentBean bean : departmentlist){
            PictureBean pic = pic_service.getPictureById(bean.getDepartment_picture_id());
            pic_list.add(pic);
        }
        
        //封装大类信息
        JSONObject department_json = new JSONObject();
        department_json.element("root", JSONArray.fromObject(departmentlist));
        DebugUtils.showLog(department_json.toString());
        
        //封装图片信息json
        JSONObject pic_json = new JSONObject();
        pic_json.element("pic", JSONArray.fromObject(pic_list));
        DebugUtils.showLog(pic_json.toString());
        
        //封装数据
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        data_array.add(department_json);
        data_array.add(pic_json);
        data.element("data", data_array);
        
        //设置响应
        request.setAttribute("all_department_list", department_json.toString());
        request.setAttribute("pic_json", pic_json.toString());
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public DepartmentAction() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }

    public void init() throws ServletException {
    }
}
