package com.studyplatform.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.service.PictureService;
import com.studyplatform.web.service.impl.PictureServiceImpl;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 图片操作Servlet
 * Title: PictureServlet
 * @date 2018年3月7日
 * @author Freedom0013
 */
public class PictureServlet extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制字符集
        WebUtils.setCharSet(request, response);
        
        PictureService service = new PictureServiceImpl();
        
        String pic_id = (String) request.getParameter("pic_id");
        int picid = Integer.parseInt(pic_id);
        BigDecimal big_pic_id = new BigDecimal(picid);
        
        PictureBean picture = service.getPictureById(big_pic_id);
        
        DebugUtils.showLog(picture.toString());
        JSONObject picture_json = new JSONObject();
        picture_json.element("root", JSONArray.fromObject(picture));
        DebugUtils.showLog(picture_json.toString());
        
        response.getWriter().write(picture_json.toString()); 
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void init() throws ServletException {
    
    }

    public PictureServlet() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }
}
