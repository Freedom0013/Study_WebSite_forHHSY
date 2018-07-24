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
import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.bean.QuestionBean;
import com.studyplatform.web.bean.ResourceBean;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.ResourceService;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.service.impl.ResourceServiceImpl;
import com.studyplatform.web.servlet.formbean.AnswerQuestionBean;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * Title: ExaminationAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class ExaminationAction extends HttpServlet {
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
        
        String question_json_text = request.getParameter("question_json_text");
        String text_questions = question_json_text.replaceAll("\'", "\"");
        
        String user_answer_map = request.getParameter("user_answer_map");
        
        //获取课程id
        String course_id = request.getParameter("course_id");
        
        DebugUtils.showLog(question_json_text);
        DebugUtils.showLog(user_answer_map);
        DebugUtils.showLog(course_id);
        
        //做题记录功能待完善
//        String user_id = request.getParameter("user_id");
        int courseid = Integer.parseInt(course_id);
//        if(user_id!=null){
//            int userid = Integer.parseInt(user_id);
//        }
        
        //解析页面传来的做题内容
        Gson gson = new Gson();
        
        //解析Json
        JsonObject dataJson = new JsonParser().parse(text_questions).getAsJsonObject();
        JsonArray dataArray = dataJson.getAsJsonArray("data");
        //解析数据
        JsonElement rootinfo = dataArray.get(0);
        JsonObject rootinfos = new JsonParser().parse(rootinfo.toString()).getAsJsonObject();
        JsonArray question_list = rootinfos.getAsJsonArray("root");
        
        ArrayList<QuestionBean> page_questions = new ArrayList<QuestionBean>();
        for(JsonElement jsonElement : question_list){
            JsonObject jo = jsonElement.getAsJsonObject();
            QuestionBean question = gson.fromJson(jo, QuestionBean.class);
            page_questions.add(question);
        }
        
        int score = 0;
        int index = 0;
        //解析Json
        JsonObject user_dataJson = new JsonParser().parse(user_answer_map).getAsJsonObject();
        JsonArray user_dataArray = user_dataJson.getAsJsonArray("user_data");
        ArrayList<AnswerQuestionBean> user_answer_list = new ArrayList<AnswerQuestionBean>();
        for(JsonElement jsonElement : user_dataArray){
            AnswerQuestionBean user_answer_bean = gson.fromJson(jsonElement, AnswerQuestionBean.class);
            user_answer_list.add(user_answer_bean);
        }
        if(user_answer_list.size()!=0){
            for(QuestionBean bean : page_questions){
                for(AnswerQuestionBean answerbean : user_answer_list){
                    int isequals = bean.getQuestion_id().compareTo(answerbean.getQuestion_id());
                    if (isequals == 0) {
                        if(bean.getQuestion_answer().equals(answerbean.getUser_answer())){
                            score+=10;
                        }
                    }
                }
            }
        }
        
        //推荐资源
        ResourceService rescourse_service = new ResourceServiceImpl();
        ArrayList<ResourceBean> resourseslist = null;
        if(score>=80 && score<=100){
            resourseslist = (ArrayList<ResourceBean>) rescourse_service.getResourceByDegree(SystemCommonValue.RESOURCE_TYPE_EXPERT, courseid);
        }else if(score>=60 && score<80){
            resourseslist = (ArrayList<ResourceBean>) rescourse_service.getResourceByDegree(SystemCommonValue.RESOURCE_TYPE_MIDDLE, courseid);
        }else{
            resourseslist = (ArrayList<ResourceBean>) rescourse_service.getResourceByDegree(SystemCommonValue.RESOURCE_TYPE_PRIMARY, courseid);
        }
        
        //封装数据
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        
        //当推荐资源不等于空，向页面传递资源json
        if(resourseslist!=null && resourseslist.size()!=0){
            JSONObject resourses = new JSONObject();
            resourses.element("resourses_json", JSONArray.fromObject(resourseslist));
            DebugUtils.showLog(resourses.toString());
            request.setAttribute("resourses_json",resourses.toString());
            data_array.add(resourses);
            
            PictureService pic_service = new PictureServiceImpl();
            ArrayList<PictureBean> pic_list = new ArrayList<PictureBean>();
            for(ResourceBean bean : resourseslist){
                PictureBean pic = pic_service.getPictureById(bean.getResource_picture_id());
                pic_list.add(pic);
            }
            JSONObject pic_json = new JSONObject();
            pic_json.element("pic", JSONArray.fromObject(pic_list));
            DebugUtils.showLog(pic_json.toString());
            request.setAttribute("pic_json",pic_json.toString());
            data_array.add(pic_json);
        }
        
        
        JSONObject score_json = new JSONObject();
        score_json.element("score",score);
        data_array.add(score_json);
        
        data.element("data", data_array);
        
        //传递数据
        request.setAttribute("user_answer_json",user_answer_map);
        request.setAttribute("question_json_text", user_dataJson.toString());
        request.setAttribute("user_score",score);
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public static boolean isNullOrEmpty(final String string) {
        return string == null || string.trim().length() == 0 || string.equalsIgnoreCase("null");
    }
    
    public ExaminationAction() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void init() throws ServletException {
    }
}
