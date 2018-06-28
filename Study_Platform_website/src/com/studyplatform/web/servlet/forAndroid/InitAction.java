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

import com.studyplatform.web.bean.InitBean;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONObject;

/**
 * Android初始化接口
 * Title: InitAction
 * @date 2018年6月20日
 * @author Freedom0013
 */
public class InitAction extends HttpServlet {
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
        
//        String from = request.getParameter("from");
//        String sign = request.getParameter("sign");
//        DebugUtils.showLog("from = "+from+",sign"+sign);
        
        int visionCode = 1;
        //更新日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        //更新说明
        List<String> UpdataMessage = new ArrayList<String>();
        UpdataMessage.add("我是更新日志第一条。");
        UpdataMessage.add("我是更新日志第二条。");
        UpdataMessage.add("我是更新日志第三条。");
        UpdataMessage.add("我是更新日志第四条。");
        InitBean info = new InitBean(false, false,"我是更新标题", visionCode, "http://localhost:8080", df.format(new Date()), UpdataMessage);
        
        JSONObject data = new JSONObject();
        data.element("data", JSONObject.fromObject(info));
        
        DebugUtils.showLog(data.toString());
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public InitAction() {
        super();
    }

    public void destroy() {
        super.destroy(); 
    }

    public void init() throws ServletException {
    }
}
