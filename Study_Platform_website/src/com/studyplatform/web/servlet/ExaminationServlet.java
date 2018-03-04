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
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

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
        for(QuestionBean bean : page_questions){
            String user_answer = request.getParameter(bean.getQuestion_id()+""); 
            if(user_answer.equals(bean.getQuestion_answer())){
                score+=10;
            }
        }
       
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
