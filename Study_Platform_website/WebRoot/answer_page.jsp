<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="com.studyplatform.web.utils.*" %>
<%@ page import="com.studyplatform.web.bean.*" %>
<%@ page import="com.studyplatform.web.system.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="com.studyplatform.web.servlet.formbean.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
	    <base href="<%=basePath%>">
	    <link rel="icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
	    <link rel="shortcut icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
	    <title>查看答案</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/test1.css" type="text/css"/>
	    <link type="text/css" href="${pageContext.request.contextPath }/css/css1.css" rel="stylesheet">
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/JQ.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/zzsc.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.XYTipsWindow.min.2.8.js"></script>
	    <link type="text/css" href="${pageContext.request.contextPath }/css/box_style.css" rel="stylesheet" />
    </head>
  
    <body>
        <div id="Top">
            <br>
            <img src="${pageContext.request.contextPath }/images/logo.png" width="315" height="60" />
            <div id="center">
                <div class="nav">
                    <ul>
	                    <li class="cur"><a href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
	                    <li><a href="#" target="_blank">专业</a></li>
	                    <li><a href="#" target="_blank">资源</a></li>
	                    <li><a href="#" target="_blank">推荐书籍</a></li>
	                    <li><a href="#" target="_blank">移动课堂</a></li>
                    </ul> 
                    <div class="curBg"></div>
                    <div class="cls"></div>
                </div>  
            </div>
            <div id="Top-login">
                <c:choose>
                    <c:when test="${!empty user.user_name}">
                        <c:if test="${fn:length(user.user_name)>6 }">
                            <font color=red>${fn:substring(user.user_name, 0, 6)}...,欢迎你
                                &nbsp; <a href="${pageContext.request.contextPath }/servlet/WrittenOffServlet">退出登录</a>
                            </font>
                        </c:if>
                        <c:if test="${fn:length(user.user_name)<=6}">
                            <font color=red>${user.user_name},欢迎你 
                                &nbsp;&nbsp; 
                                <a href="${pageContext.request.contextPath }/servlet/WrittenOffServlet">退出登录</a>
                            </font>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath }/login.jsp" target="_blank">登录</a>
                        &nbsp; 
                        <a href="${pageContext.request.contextPath }/login.jsp#toregister" target="_blank">注册</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div id="middle">
            <div id="middle-1">
                <div id="mid"><h2>学习平台</h2></div>
                <div class="style_1">
                    <form method="get" id="searchform" action="#"><!--搜索跳转网址 -->
                        <fieldset>
                        <input id="s" name="s" type="text" value="输入要搜索的内容" class="text_input" onblur="if(this.value=='输入要搜索的内容'){this.value='';}" onfocus="if(this.value =='输入要搜索的内容') {this.value=''; }" />
                        <input name="submit" type="submit" value /> </fieldset>
                    </form>
                </div>
            </div>
        </div>
        
        <div id="csbody">
            <h3>答案详解</h3>
        </div>
        <div id="csbody_box">
            <%
                String user_answer_json = (String)request.getAttribute("user_answer_json");
                
                Gson gson = new Gson();
                JsonObject answer_json = new JsonParser().parse(user_answer_json).getAsJsonObject();
                JsonArray question_list = answer_json.get("root").getAsJsonArray();
                ArrayList<ReplayQuestion> single_questions = new ArrayList<ReplayQuestion>();
                ArrayList<ReplayQuestion> multi_questions = new ArrayList<ReplayQuestion>();
                ArrayList<ReplayQuestion> judge_questions = new ArrayList<ReplayQuestion>();
                for(JsonElement jsonElement : question_list){
                    JsonObject jo = jsonElement.getAsJsonObject();
                    ReplayQuestion question = gson.fromJson(jo, ReplayQuestion.class);
                    switch(question.getQuestion_type()){
                        case SystemCommonValue.EXAM_QUESTION_TYPE_SINGLE:
                            single_questions.add(question);
                            break;
                            
                        case SystemCommonValue.EXAM_QUESTION_TYPE_MULTI:
                            multi_questions.add(question);
                            break;
                            
                        case SystemCommonValue.EXAM_QUESTION_TYPE_JUDGE:
                            judge_questions.add(question);
                            break;
                            
                       default:
                            break;
                    }
                }
                
               /*  for(JsonElement jsonElement : user_answer_list){
                    JsonObject jo = jsonElement.getAsJsonObject();
                    String answer = gson.fromJson(jo, String.class);
                    user_answers.add(answer);
                } */
                
             %>
             <%-- ?question_json=<%=question_json %> --%>
                <%  
                    int num = 0;
                    if(single_questions.size()!=0){
                    %>
                    <p>单项选择</p>
                    <%
                        for(ReplayQuestion bean:single_questions){
                           num++;
                           OptionBean option = bean.getOption();
                           String stem = "第" + num + "题：" + bean.getQuestion_stem();
                            %>  
                                <label><%=stem %></label><br>
                                <label>A、<%=option.getOption_a()%></label><br>
                                <label>B、<%=option.getOption_b()%></label><br>
                                <label>C、<%=option.getOption_c()%></label><br>
                                <label>D、<%=option.getOption_d()%></label><br>
                                <label>您的答案为：<%=bean.getUser_answer()%></label><br>
                                <label>正确答案为：<%=bean.getQuestion_answer()%></label><br>
                                <label>试题解析：<%=bean.getQuestion_analysis()%></label><br>
                                <br>
                            <%                                             
                        } 
                    }
                %>
                
                <%  
                    if(multi_questions.size()!=0){
                    %>
                    <p>多项选择</p>
                    <%
                        for(ReplayQuestion bean:multi_questions){
                           num++;
                           OptionBean option = bean.getOption();
                           String stem = "第" + num + "题：" + bean.getQuestion_stem();
                            %>  
                                <label><%=stem %></label><br>
                                <label>A、<%=option.getOption_a()%></label><br>
                                <label>B、<%=option.getOption_b()%></label><br>
                                <label>C、<%=option.getOption_c()%></label><br>
                                <label>D、<%=option.getOption_d()%></label><br>
                                <%  
                                    if(option.getOption_e()!=null){
                                        %>
                                        <label>E、<%=option.getOption_e()%></label><br>
                                        <%
                                    }
                                %>
                                <%  
                                    if(option.getOption_f()!=null){
                                        %>
                                        <label>F、<%=option.getOption_f()%></label><br>
                                        <%
                                    }
                                %>
                                <%  
                                    if(option.getOption_g()!=null){
                                        %>
                                        <label>G、<%=option.getOption_g()%></label><br>
                                        <%
                                    }
                                %>
                                <label>您的答案为：<%=bean.getUser_answer()%></label><br>
                                <label>正确答案为：<%=bean.getQuestion_answer()%></label><br>
                                <label>试题解析：<%=bean.getQuestion_analysis()%></label><br>
                                <br>
                            <%                                             
                        } 
                    }
                %>
                
                <%  
                    if(judge_questions.size()!=0){
                    %>
                    <p>判断题</p>
                    <%
                        for(ReplayQuestion bean:judge_questions){
                           num++;
                           String stem = "第" + num + "题：" + bean.getQuestion_stem();
                            %>  
                                <label><%=stem %></label><br>
                                <label>您的答案为：<%=bean.getUser_answer()%></label><br>
                                <label>正确答案为：<%=bean.getQuestion_answer()%></label><br>
                                <label>试题解析：<%=bean.getQuestion_analysis()%></label><br>
                                <br>
                            <%        
                        } 
                    }    
                 %>
                 
        </div>
        
        <div id="footer">
            <p>Copyright © 2017 YRCTI. All Rights Reserved.</p>
            <p>黄河水利职业技术学院 版权所有 | <a href="#" target="_blank" rel="nofollow">博知课堂服务协议</a> | <a href="#">站点地图</a> | <a href="#" target="_blank">侵权投诉</a> | <a href="#" rel="nofollow" report-tdw="action=Feedback" target="_blank">问题反馈</a> | <a href="#" target="_blank">帮助</a></p>
        </div>
    </body>
</html>
