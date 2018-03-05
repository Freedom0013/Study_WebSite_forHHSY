package com.studyplatform.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studyplatform.web.bean.QuestionBean;
import com.studyplatform.web.bean.ResourceBean;
import com.studyplatform.web.service.ResourceService;
import com.studyplatform.web.service.impl.ResourceServiceImpl;
import com.studyplatform.web.servlet.formbean.AnswerQuestionBean;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 测试提交Servlet
 * Title: ExaminationServlet
 * @date 2018年3月4日
 * @author Freedom0013
 */
public class ExaminationServlet extends HttpServlet {
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
        String question_json_text = request.getParameter("question_json_text");
        String text_questions = question_json_text.replaceAll("\'", "\"");
        
        String course_id = request.getParameter("course_id");
//        String user_id = request.getParameter("user_id");
        int courseid = Integer.parseInt(course_id);
//        if(user_id!=null){
//            int userid = Integer.parseInt(user_id);
//        }
        
        Gson gson = new Gson();
        JsonObject rootJson = new JsonParser().parse(text_questions).getAsJsonObject();
        JsonArray question_list = rootJson.get("root").getAsJsonArray();
        ArrayList<QuestionBean> page_questions = new ArrayList<QuestionBean>();
        for(JsonElement jsonElement : question_list){
            JsonObject jo = jsonElement.getAsJsonObject();
            QuestionBean question = gson.fromJson(jo, QuestionBean.class);
            page_questions.add(question);
        }
        
        int score = 0;
        int index = 0;
        JSONObject user_answer_json = new JSONObject();
        ArrayList<AnswerQuestionBean> user_answer_list = new ArrayList<AnswerQuestionBean>();
        for(QuestionBean bean : page_questions){
            AnswerQuestionBean user_answer_bean = new AnswerQuestionBean();
            user_answer_bean.setTest_index(index);
            user_answer_bean.setQuestion_id(bean.getQuestion_id());
            String user_answer = request.getParameter(bean.getQuestion_id()+"");
            user_answer_bean.setUser_answer(user_answer);
            if(user_answer.equals(bean.getQuestion_answer())){
                score+=10;
            }
            index++;
            user_answer_list.add(user_answer_bean);
        }
        user_answer_json.element("root", JSONArray.fromObject(user_answer_list));
        
        
        ResourceService rescourse_service = new ResourceServiceImpl();
        ArrayList<ResourceBean> resourseslist = null;
        if(score>=80 && score<=100){
            resourseslist = (ArrayList<ResourceBean>) rescourse_service.getResourceByDegree(SystemCommonValue.RESOURCE_TYPE_EXPERT, courseid);
        }else if(score>=60 && score<80){
            resourseslist = (ArrayList<ResourceBean>) rescourse_service.getResourceByDegree(SystemCommonValue.RESOURCE_TYPE_MIDDLE, courseid);
        }else{
            resourseslist = (ArrayList<ResourceBean>) rescourse_service.getResourceByDegree(SystemCommonValue.RESOURCE_TYPE_PRIMARY, courseid);
        }
        
        
        
        if(resourseslist!=null && resourseslist.size()!=0){
            JSONObject resourses = new JSONObject();
            resourses.element("root", JSONArray.fromObject(resourseslist));
            DebugUtils.showLog(resourses.toString());
            request.setAttribute("resourses_json",resourses.toString());
        }
        
        DebugUtils.showLog(user_answer_json.toString());
        request.setAttribute("user_answer_json",user_answer_json.toString());
        request.setAttribute("question_json_text", question_json_text);
        request.setAttribute("user_score",score);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/test_result.jsp");
        dispatcher.forward(request, response);
    }

    public void init() throws ServletException {
        
    }

    public ExaminationServlet() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }
}
