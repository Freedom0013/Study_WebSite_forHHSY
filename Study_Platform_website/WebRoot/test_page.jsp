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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
	    <base href="<%=basePath%>">
	    <link rel="icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
	    <link rel="shortcut icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
	    <title>测试</title>
	    
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
            <h3>测试</h3>
        </div>
        <div id="csbody_box">
            <%
                String question_json = (String)request.getAttribute("question_json");
                String course_id = (String)request.getAttribute("course_id");
                Gson gson = new Gson();
                JsonObject rootJson = new JsonParser().parse(question_json).getAsJsonObject();
                JsonArray question_list = rootJson.get("root").getAsJsonArray();
                ArrayList<QuestionBean> single_questions = new ArrayList<QuestionBean>();
                ArrayList<QuestionBean> multi_questions = new ArrayList<QuestionBean>();
                ArrayList<QuestionBean> judge_questions = new ArrayList<QuestionBean>();
                for(JsonElement jsonElement : question_list){
                    JsonObject jo = jsonElement.getAsJsonObject();
                    QuestionBean question = gson.fromJson(jo, QuestionBean.class);
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
             %>
             <%
	            if(single_questions.size()==0 && multi_questions.size()==0 && judge_questions.size()==0){
                    %>
                    <div>
                        <h2>没有该课程试题，正拼命完善中..</h2>
                    </div>
                    <%     
	            }else{
	               %>
		            <form action="${pageContext.request.contextPath }/servlet/ExaminationServlet" method="post">
		                <%  
		                    int num = 0;
		                    if(single_questions.size()!=0 && single_questions !=null){
		                    %>
		                    <p>单项选择</p>
		                    <%
			                    for(QuestionBean bean:single_questions){
			                       num++;
			                       OptionBean option = bean.getOption();
			                       String stem = "第" + num + "题：" + bean.getQuestion_stem();
		                            %>  
		                                <label><%=stem %>(&nbsp;&nbsp;)</label><br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" class="radio0" value="A">A、<%=option.getOption_a()%><br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" class="radio0" value="B">B、<%=option.getOption_b()%><br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" class="radio0" value="C">C、<%=option.getOption_c()%><br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" class="radio0" value="D">D、<%=option.getOption_d()%><br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" class="radio0" value="null" checked="checked">E、不知道<br>
		                                <br>
		                            <%                     	                       
			                    } 
		                    }else if(multi_questions.size()!=0 && multi_questions !=null){
		                    %>
		                    <p>多项选择</p>
		                    <%
		                        for(QuestionBean bean:multi_questions){
		                           num++;
		                           OptionBean option = bean.getOption();
		                           String stem = "第" + num + "题：" + bean.getQuestion_stem();
		                            %>  
		                                <label><%=stem %>(&nbsp;&nbsp;)</label><br>
		                                <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="A">A、 <%=option.getOption_a()%><br>
		                                <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="B">B、 <%=option.getOption_b()%><br>
		                                <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="C">C、 <%=option.getOption_c()%><br>
		                                <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="D">D、<%=option.getOption_d()%><br>
		                                <%  
		                                    if(option.getOption_e()!=null){
		                                        %>
		                                        <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="E">E、<%=option.getOption_e()%><br><br>
		                                        <%
		                                    }
		                                %>
		                                <%  
		                                    if(option.getOption_f()!=null){
		                                        %>
		                                        <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="F">F、<%=option.getOption_f()%><br><br>
		                                        <%
		                                    }
		                                %>
		                                <%  
		                                    if(option.getOption_g()!=null){
		                                        %>
		                                        <input type="checkbox" name="<%=bean.getQuestion_id()%>" class="radio0" value="G">G、<%=option.getOption_g()%><br><br>
		                                        <%
		                                    }
		                                %>
		                            <%                                             
		                        } 
		                    }else if(judge_questions.size()!=0 && judge_questions !=null){
		                    %>
		                    <p>判断题</p>
		                    <%
		                        for(QuestionBean bean:judge_questions){
		                           num++;
		                           String stem = "第" + num + "题：" + bean.getQuestion_stem();
		                            %>  
		                                <label><%=stem %>(&nbsp;&nbsp;)</label><br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" value="right" class="radio0">对<br>
		                                <input type="radio" name="<%=bean.getQuestion_id()%>" value="wrong" class="radio0">错<br>
		                            <%                                             
		                        } 
		                    }    
		                    String qu = question_json.replaceAll("\"", "\'");
		                 %>
		                 <input type="hidden" value="<%=qu %>" name="question_json_text" />
		                 <input type="hidden" value="<%=course_id %>" name="course_id" />
		                 <input type="hidden" value="${user.user_id}" name="user_id" />
		                 <div id="pagesubmit">
		                    <input id="button1111" type="submit" value="提交">
		                 </div>
		            </form>
	            <%
	                }
	              %>
        </div>
        
        <div id="footer">
            <p>Copyright © 2017 YRCTI. All Rights Reserved.</p>
            <p>黄河水利职业技术学院 版权所有 | <a href="#" target="_blank" rel="nofollow">博知课堂服务协议</a> | <a href="#">站点地图</a> | <a href="#" target="_blank">侵权投诉</a> | <a href="#" rel="nofollow" report-tdw="action=Feedback" target="_blank">问题反馈</a> | <a href="#" target="_blank">帮助</a></p>
        </div>
    </body>
</html>
