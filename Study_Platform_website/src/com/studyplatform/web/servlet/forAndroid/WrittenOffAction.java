package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

/**
 * 
 * Title: WrittenOffAction
 * @date 2018年6月19日
 * @author Freedom0013
 */
public class WrittenOffAction extends HttpServlet {
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
        

        //false代表：不创建session对象，只是从request中获取。  
        HttpSession session = request.getSession(false);  
        if(session==null){  
            return;  
        }  
        session.removeAttribute("user");  
        
//        out.write(question_answer_json.toString());  
//        out.flush();  
//        out.close();  
//        DebugUtils.showLog(question_answer_json.toString());
    }

    public WrittenOffAction() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void init() throws ServletException {
    }
}
