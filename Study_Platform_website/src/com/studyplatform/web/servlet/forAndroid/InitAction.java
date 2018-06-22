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

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

import net.sf.json.JSONArray;
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
        String from = request.getParameter("from");
        String sign = request.getParameter("sign");
        DebugUtils.showLog("from = "+from+",sign"+sign);
        
        int visionCode = 1;
        
        JSONObject data = new JSONObject();
        JSONArray data_array = new JSONArray();
        
        //更新标识
        JSONObject updata_flag = new JSONObject();
        updata_flag.element("updata_flag", "false");
        data_array.add(updata_flag);
        
        //更新标题
        JSONObject title = new JSONObject();
        title.element("updata_title","我是更新标题");
        data_array.add(title);
        
        //更新版本号
        JSONObject vision = new JSONObject();
        vision.element("updata_visionCode",visionCode);
        data_array.add(vision);
        
        //更新日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject date = new JSONObject();
        date.element("updata_date",df.format(new Date()));
        data_array.add(date);
        
        //更新说明
        List<String> UpdataMessage = new ArrayList<String>();
        UpdataMessage.add("我是更新日志第一条。");
        UpdataMessage.add("我是更新日志第二条。");
        UpdataMessage.add("我是更新日志第三条。");
        UpdataMessage.add("我是更新日志第四条。");
        JSONArray messagearray = new JSONArray();
        for (int i = 0; i < UpdataMessage.size(); i++) {
            JSONObject single_message = new JSONObject();
            single_message.element("updata_Message"+i, UpdataMessage.get(i));
            messagearray.add(single_message);
        }
        JSONObject messages = new JSONObject();
        messages.element("Messages",messagearray);
        data_array.add(messages);
        
        data.element("data", data_array);
        
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
