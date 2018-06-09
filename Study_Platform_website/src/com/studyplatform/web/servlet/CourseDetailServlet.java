package com.studyplatform.web.servlet;

import java.io.IOException;

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
 * 课程详情页面
 * Title: CourseDetailServlet
 * @date 2018年3月4日
 * @author Freedom0013
 */
public class CourseDetailServlet extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        WebUtils.setCharSet(request, response);
        
        //获取上级页面传入页面id
        String course_id = (String) request.getParameter("course_id");
        int courseid = Integer.parseInt(course_id);
        
        CourseService service = new CourseServiceImpl();
        
        //封装课程详情Bean
        CourseBean bean = service.getCourseDetailByCouId(courseid);
        JSONObject course_detail_json = new JSONObject();
        course_detail_json.element("root", JSONObject.fromObject(bean));
        
        //封装课程对应图片信息
        PictureService pic_service = new PictureServiceImpl();
        PictureBean pic = pic_service.getPictureById(bean.getCourse_picture_id());
        JSONObject pic_json = new JSONObject();
        pic_json.element("pic", JSONArray.fromObject(pic));
        DebugUtils.showLog(bean.toString());
        DebugUtils.showLog(pic_json.toString());
        
        //响应添加
        request.setAttribute("course_detail_json",course_detail_json.toString());
        request.setAttribute("pic_json",pic_json.toString());
        
        //页面跳转
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course_detail.jsp");
        dispatcher.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void init() throws ServletException {
    
    }
    
    public CourseDetailServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }
}
