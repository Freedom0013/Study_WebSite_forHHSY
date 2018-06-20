package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.studyplatform.web.service.QuestionService;
import com.studyplatform.web.service.impl.QuestionServiceImpl;
import com.studyplatform.web.servlet.formbean.AnswerQuestionBean;
import com.studyplatform.web.servlet.formbean.ReplayQuestion;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 显示答案ServletForAndroid
 * Title: AD_AnswerServlet
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class AnswerAction extends HttpServlet {
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
        
        String user_answer_json = request.getParameter("user_answer_json");
        String user_question_answer = user_answer_json.replaceAll("\'", "\"");

        Gson gson = new Gson();
        JsonObject rootJson = new JsonParser().parse(user_question_answer).getAsJsonObject();
        JsonArray user_answer_list = rootJson.get("root").getAsJsonArray();
        ArrayList<AnswerQuestionBean> user_answers = new ArrayList<AnswerQuestionBean>();
        for (JsonElement jsonElement : user_answer_list) {
            JsonObject jo = jsonElement.getAsJsonObject();
            AnswerQuestionBean answer = gson.fromJson(jo, AnswerQuestionBean.class);
            user_answers.add(answer);
        }

        QuestionService que_service = new QuestionServiceImpl();

        ArrayList<ReplayQuestion> requestionlist = new ArrayList<ReplayQuestion>();
        for (AnswerQuestionBean answer_bean : user_answers) {
            QuestionBean question = que_service.getQuestionById(answer_bean.getQuestion_id());
            ReplayQuestion requestion = new ReplayQuestion();
            requestion.setQuestion_id(question.getQuestion_id());
            requestion.setQuestion_stem(question.getQuestion_stem());
            requestion.setQuestion_option_id(question.getQuestion_option_id());
            requestion.setQuestion_level(question.getQuestion_level());
            requestion.setQuestion_answer(question.getQuestion_answer());
            requestion.setQuestion_analysis(question.getQuestion_analysis());
            requestion.setQuestion_type(question.getQuestion_type());
            requestion.setQuestion_addtime(question.getQuestion_addtime());
            requestion.setQuestion_course_id(question.getQuestion_course_id());
            requestion.setQuestion_chapter(question.getQuestion_chapter());
            requestion.setOption(question.getOption());
            requestion.setTest_index(answer_bean.getTest_index());
            requestion.setUser_answer(answer_bean.getUser_answer());
            requestionlist.add(requestion);
        }

        JSONObject question_answer_json = new JSONObject();
        question_answer_json.element("root", JSONArray.fromObject(requestionlist));
        DebugUtils.showLog(question_answer_json.toString());
        request.setAttribute("user_answer_json", question_answer_json.toString());
        
        out.write(question_answer_json.toString());  
        out.flush();  
        out.close();  
    }
    
    public AnswerAction() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }
    
    public void init() throws ServletException {
    }
}
