package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyplatform.web.bean.BannerBean;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * app获取主页Banner信息
 * Title: BannerAction
 * @date 2018年7月12日
 * @author Freedom0013
 */
public class BannerAction extends HttpServlet {
    /**
     * 版本控制
     */
    private static final long serialVersionUID = 1L;

    public BannerAction() {
        super();
    }

    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 控制字符集
        WebUtils.setCharSet(request, response);
        PrintWriter out = response.getWriter();
        
        List<BannerBean> bannerlist = new ArrayList<BannerBean>();
        for (int i = 0; i <= 3; i++) {
            BannerBean bean = new BannerBean();
            bean.setBannner_id(i);
            bean.setBanner_title("欢迎来到知识深林"+(i+1));
            bean.setBanner_image("http://192.168.0.102:8080/Study_Platform_website/images/android_images/banner_"+(i+1)+".jpg");
            bean.setBanner_url("url");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            bean.setBanner_updata_date(df.format(new Date()));
            bannerlist.add(bean);
        }
        
        JSONObject data = new JSONObject();
        data.element("data", JSONArray.fromObject(bannerlist));
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public void init() throws ServletException {
    }
}
