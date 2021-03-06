package com.studyplatform.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.CourseBean;
import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.service.CourseService;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.impl.CourseServiceImpl;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 请求课程列表
 * Title: CourseServlet
 * @date 2018年3月3日
 * @author Freedom0013
 */
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        WebUtils.setCharSet(request, response);
        
        //获取上级页面传入的专业id
        String professions_id = (String) request.getParameter("professions_id");
        int professionsid = Integer.parseInt(professions_id);
        
        CourseService service = new CourseServiceImpl();
        
        //加载课程列表
        ArrayList<CourseBean> courseList = (ArrayList<CourseBean>) service.getAllCourseByProId(professionsid);
        
        //加载课程图片列表
        PictureService pic_service = new PictureServiceImpl();
        ArrayList<PictureBean> pic_list = new ArrayList<PictureBean>();
        for(CourseBean bean : courseList){
            PictureBean pic = pic_service.getPictureById(bean.getCourse_picture_id());
            pic_list.add(pic);
        }
        
        //封装课程信息json
        JSONObject course_json = new JSONObject();
        course_json.element("root", JSONArray.fromObject(courseList));
        DebugUtils.showLog(course_json.toString());
        
        //封装课程图片json
        JSONObject pic_json = new JSONObject();
        pic_json.element("pic", JSONArray.fromObject(pic_list));
        DebugUtils.showLog(pic_json.toString());
        
        //响应数据
        request.setAttribute("course_json",course_json.toString());
        request.setAttribute("pic_json",pic_json.toString());
        
        //页面跳转
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course.jsp");
        dispatcher.forward(request, response);
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
