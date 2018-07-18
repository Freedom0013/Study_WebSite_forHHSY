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
import com.studyplatform.web.bean.ProfessionBean;
import com.studyplatform.web.service.DepartmentService;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.ProfessionalService;
import com.studyplatform.web.service.impl.DepartmentServiceImpl;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.service.impl.ProfessionalServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * Title: ProfessionalAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class ProfessionalAction extends HttpServlet {
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

        //获取上级页面传入的department_id
        String department_id = (String) request.getParameter("department_id");
        int departmentid = Integer.parseInt(department_id);
        String sign = request.getParameter("sign");
        DebugUtils.showLog("department_id = "+department_id+",sign"+sign);
        
        //获取专业列表
        ProfessionalService service = new ProfessionalServiceImpl();
        ArrayList<ProfessionBean> professionlist = (ArrayList<ProfessionBean>) service.getAllProfessionsByDepId(departmentid);
        
        //获取专业图片列表
        PictureService pic_service = new PictureServiceImpl();
        ArrayList<PictureBean> pic_list = new ArrayList<PictureBean>();
        for(ProfessionBean bean : professionlist){
            PictureBean pic = pic_service.getPictureById(bean.getProfession_picture_id());
            pic_list.add(pic);
        }
        
        //封装图片json
        JSONObject pic_json = new JSONObject();
        pic_json.element("pic", JSONArray.fromObject(pic_list));
        DebugUtils.showLog(pic_json.toString());
        
        //封装专业json
        JSONObject profession_json = new JSONObject();
        profession_json.element("root", JSONArray.fromObject(professionlist));
        DebugUtils.showLog(profession_json.toString());
        
        //封装数据
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        data_array.add(profession_json);
        data_array.add(pic_json);
        JSONObject department_id_json = new JSONObject();
        department_id_json.element("department_id",departmentid);
        data_array.add(department_id_json);
        data.element("data", data_array);
        
        request.setAttribute("profession_list_json",profession_json.toString());
        request.setAttribute("department_id", departmentid);
        request.setAttribute("pic_json", pic_json.toString());
        
        //--------冗余(因为没有了大类下拉快捷选择栏)---------
        DepartmentService department_service = new DepartmentServiceImpl();
        ArrayList<DepartmentBean> departmentlist = (ArrayList<DepartmentBean>) department_service.getallDepartment();
        JSONObject department_json = new JSONObject();
        department_json.element("root", JSONArray.fromObject(departmentlist));
        request.setAttribute("all_department_list", department_json.toString());
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public ProfessionalAction() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }

    public void init() throws ServletException {
    }
}
