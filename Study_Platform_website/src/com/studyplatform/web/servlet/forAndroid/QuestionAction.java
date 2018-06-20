package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.QuestionBean;
import com.studyplatform.web.service.QuestionService;
import com.studyplatform.web.service.impl.QuestionServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * Title: QuestionAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class QuestionAction extends HttpServlet {
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

        //获取上级页面传递的课程id
        String course_id = (String) request.getParameter("course_id");
        int courseid = Integer.parseInt(course_id);
        
        QuestionService service = new QuestionServiceImpl();
        
        //生成试题
        ArrayList<QuestionBean> questionlist = (ArrayList<QuestionBean>) service.getRandomExaminationByCourse(courseid);
        
        //封装试题json
        JSONObject question_json = new JSONObject();
        question_json.element("root", JSONArray.fromObject(questionlist));
        DebugUtils.showLog(question_json.toString());
        
        //响应
        request.setAttribute("question_json",question_json.toString());
        request.setAttribute("course_id", course_id);
        
        //封装数据
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        data_array.add(question_json);
        JSONObject course_id_json = new JSONObject();
        course_id_json.element("course_id",course_id);
        data_array.add(course_id_json);
        data.element("data", data_array);
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public QuestionAction() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void init() throws ServletException {
    }
}
