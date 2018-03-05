<%@page import="com.studyplatform.web.utils.DebugUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="com.studyplatform.web.utils.*" %>
<%@ page import="com.studyplatform.web.bean.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>
<%@ page import="org.apache.commons.lang3.math.*" %>
<%@ page import="com.google.gson.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
  
    <base href="<%=basePath%>">
    <link rel="icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
    
    <title>专业列表</title>
    
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
	
  </head>
  
  <body>
    <div id="Top">
        <br>
        <img src="${pageContext.request.contextPath }/images/logo.png" width="315" height="60" />
        <div id="center">
            <div class="nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
                    <li class="cur"><a href="zhuanye1.html">专业</a></li>
                    <li><a href="${pageContext.request.contextPath }/资源.html">资源</a></li>
                    <li><a href="${pageContext.request.contextPath }/推荐书籍.html">推荐书籍</a></li>
                    <li><a href="#">移动课堂</a></li>
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
                            |<a href="${pageContext.request.contextPath }/servlet/WrittenOffServlet">退出登录</a>
                        </font>
                    </c:if>
                    <c:if test="${fn:length(user.user_name)<=6}">
                        <font color=red>${user.user_name},欢迎你 
                            |
                            <a href="${pageContext.request.contextPath }/servlet/WrittenOffServlet">退出登录</a>
                        </font>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath }/login.jsp" target="_blank">登录</a>
                    <a href="${pageContext.request.contextPath }/login.jsp#toregister" target="_blank">注册</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    <div id="middle">
        <div id="middle-1">
            <div id="mid">
                <h2>学习平台</h2>
            </div>
	            
	        <div class="style_1">
                <form method="get" id="searchform" action="#"><!--搜索跳转网址 -->
                    <fieldset>
                    <input id="s" name="s" type="text" value="输入要搜索的内容" class="text_input" onblur="if(this.value=='输入要搜索的内容'){this.value='';}" onfocus="if(this.value =='输入要搜索的内容') {this.value=''; }" />
                    <input name="submit" type="submit" value /> </fieldset>
                </form>
            </div>
        </div>
    </div>
        
        <div id="content">
            <div class="biaoti">
                <h2>专 业</h2>
            </div>
            <div id="chose">院系选择
				<select name="选择" onchange='document.location.href=this.options[this.selectedIndex].value;'>
					<% 
		                int selected_dep_id = (int)request.getAttribute("department_id");
		                String all_department_list = (String)request.getAttribute("all_department_list");
					    Gson gson1 = new Gson();
                        JsonObject rootJson1 = new JsonParser().parse(all_department_list).getAsJsonObject();
                        JsonArray dep_list = rootJson1.get("root").getAsJsonArray();
                        ArrayList<DepartmentBean> departments = new ArrayList<DepartmentBean>();
                        for(JsonElement jsonElement : dep_list){
                            JsonObject jo = jsonElement.getAsJsonObject();
                            DepartmentBean department = gson1.fromJson(jo, DepartmentBean.class);
                            departments.add(department);
                        }
                        if(departments.size()!=0){
                            for(DepartmentBean bean : departments){ 
                                if(bean.getDepartment_id() == selected_dep_id){ %>
                                      <option value="${pageContext.request.contextPath }/servlet/ProfessionalServlet?department_id=<%=bean.getDepartment_id() %>" selected = "selected"><%=bean.getDepartment_name() %></option> 
                            <%  }else{  %>
                                <option value="${pageContext.request.contextPath }/servlet/ProfessionalServlet?department_id=<%=bean.getDepartment_id() %>"><%=bean.getDepartment_name() %></option>
                           <%   }
                            }
                        } 
                    %>
				</select>
            </div>
            
            <% 
                String profession_list_json = (String)request.getAttribute("profession_list_json");
                Gson gson = new Gson();
                JsonObject rootJson = new JsonParser().parse(profession_list_json).getAsJsonObject();
                JsonArray pro_list = rootJson.get("root").getAsJsonArray();
                ArrayList<ProfessionBean> professions = new ArrayList<ProfessionBean>();
                for(JsonElement jsonElement : pro_list){
                    JsonObject jo = jsonElement.getAsJsonObject();
                    ProfessionBean profession = gson.fromJson(jo, ProfessionBean.class);
                    professions.add(profession);
                }
            %>
            
            <div id="box1">
                <%
	                 if(professions.size()!=0){
	                    for(ProfessionBean bean : professions){ %>
                            <div class="box">
                                <% String url = basePath+"servlet/CourseServlet?professions_id="+bean.getProfession_id(); %>
			                    <a href="<%=url%>" target="_blank">
			                        <img src="${pageContext.request.contextPath }/images/xxzz_2.jpg" width="291" height="179">
			                    </a>
			                    <h2>
			                        <a href="<%=url%>" target="_blank"><%=bean.getProfession_name()%></a>
			                    </h2>
			                </div> 
	            <%      }
	                }else{
	                %> 
		                <div class="biaoti">
	                        <h2>没有该院系专业，正拼命完善中..</h2>
	                    </div> 
                    <%
	                }
                %>
				<p>&nbsp;</p>
            </div>
        </div>

        <div id="footer">
			<p>Copyright © 2017 YRCTI. All Rights Reserved.</p>
			<p>黄河水利职业技术学院 版权所有 | <a href="#" target="_blank" rel="nofollow">博知课堂服务协议</a> | <a href="#">站点地图</a> | <a href="#" target="_blank">侵权投诉</a> | <a href="#" rel="nofollow" report-tdw="action=Feedback" target="_blank">问题反馈</a> | <a href="#" target="_blank">帮助</a></p>
        </div>
    </body>
</html>
