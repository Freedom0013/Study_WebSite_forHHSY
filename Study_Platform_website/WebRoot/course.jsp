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
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.google.gson.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <link rel="icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
        <title>课程页面</title>
    
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
	                    <li><a href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
	                    <li class="cur"><a href="zhuanye/zhuanye1.html">专业</a></li>
	                    <li><a href="资源.html">资源</a></li>
	                    <li><a href="推荐书籍.html">推荐书籍</a></li>
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
	        <% 
                String course_list_json = (String)request.getAttribute("course_json");
                Gson gson = new Gson();
                JsonObject rootJson = new JsonParser().parse(course_list_json).getAsJsonObject();
                JsonArray cou_list = rootJson.get("root").getAsJsonArray();
                ArrayList<CourseBean> courses_first = new ArrayList<CourseBean>();
                ArrayList<CourseBean> courses_second = new ArrayList<CourseBean>();
                ArrayList<CourseBean> courses_third = new ArrayList<CourseBean>();
                ArrayList<CourseBean> courses_fourth = new ArrayList<CourseBean>();
                
                for(JsonElement jsonElement : cou_list){
                    JsonObject jo = jsonElement.getAsJsonObject();
                    CourseBean course = gson.fromJson(jo, CourseBean.class);
                    switch(course.getCourse_degree()){
                        case 1:
                            courses_first.add(course);
                            break;
                        case 2:
                            courses_second.add(course);
                            break;
                        case 3:
                            courses_third.add(course);
                            break;
                        case 4:
                            courses_fourth.add(course);
                            break;
                        default:
                            courses_fourth.add(course);
                            break;
                    }
                }
                
                
                String pic_json = (String)request.getAttribute("pic_json");
                Gson gson_pic = new Gson();
                JsonObject rootJson_pic = new JsonParser().parse(pic_json).getAsJsonObject();
                JsonArray pic_list = rootJson_pic.get("pic").getAsJsonArray();
                ArrayList<PictureBean> piclist = new ArrayList<PictureBean>();
                for(JsonElement jsonElement : pic_list){
                    JsonObject jo = jsonElement.getAsJsonObject();
                    PictureBean picture = gson.fromJson(jo, PictureBean.class);
                    piclist.add(picture);
                }
            %>
            
            <% 
                if(courses_first.size()!=0){
            %>
            <div class="biaoti">
                <h2>大一课程</h2>
            </div>
			<div id="box1">
                <%
                    for(CourseBean bean : courses_first){
                        String url = basePath+"servlet/CourseDetailServlet?course_id="+bean.getCourse_id();
                        %>
                            <div class="box">
                                <a href="<%=url %>" target="_blank">
                                    <%for(PictureBean pics : piclist){
                                        int is = bean.getCourse_picture_id().compareTo(pics.getPicture_id());
                                        if(is == 0){
                                            String pic_urls = basePath+pics.getPicture_img();
                                            %>
                                                <img src="<%=pic_urls %>" width="291" height="179">
                                    <%      break;
                                        }
                                      } 
                                    %>
                                </a>
                                <h2>
                                    <a href="<%=url %>" target="_blank"><%=bean.getCourse_name() %></a>
                                </h2>
                            </div>
                        <%  	                            
                    }
                %> 
			    <p>&nbsp;</p>
			</div>
			<%
	           }
            %>
            
            <% 
                if(courses_second.size()!=0){
            %>
            <div class="biaoti">
                <h2>大二课程</h2>
            </div>
            <div id="box1">
                <%
                    for(CourseBean bean : courses_second){
                        String url = basePath+"servlet/CourseDetailServlet?course_id="+bean.getCourse_id();
                        %>
                            <div class="box">
                                <a href="<%=url %>" target="_blank">
                                    <%for(PictureBean pics : piclist){
                                        int is = bean.getCourse_picture_id().compareTo(pics.getPicture_id());
                                        if(is == 0){
                                            String pic_urls = basePath+pics.getPicture_img();
                                            %>
                                                <img src="<%=pic_urls %>" width="291" height="179">
                                    <%      break;
                                        }
                                      } 
                                    %>
                                </a>
                                <h2>
                                    <a href="<%=url %>" target="_blank"><%=bean.getCourse_name() %></a>
                                </h2>
                            </div>
                        <%                                  
                    }
                %> 
                <p>&nbsp;</p>
            </div>
            <%
               }
            %>
            
            <% 
                if(courses_third.size()!=0){
            %>
            <div class="biaoti">
                <h2>大三课程</h2>
            </div>
            <div id="box1">
                <%
                    for(CourseBean bean : courses_third){
                        String url = basePath+"servlet/CourseDetailServlet?course_id="+bean.getCourse_id();
                        %>
                            <div class="box">
                                <a href="<%=url %>" target="_blank">
                                    <%for(PictureBean pics : piclist){
                                        int is = bean.getCourse_picture_id().compareTo(pics.getPicture_id());
                                        if(is == 0){
                                            String pic_urls = basePath+pics.getPicture_img();
                                            %>
                                                <img src="<%=pic_urls %>" width="291" height="179">
                                    <%      break;
                                        }
                                      } 
                                    %>
                                </a>
                                <h2>
                                    <a href="<%=url %>" target="_blank"><%=bean.getCourse_name() %></a>
                                </h2>
                            </div>
                        <%                                  
                    }
                %> 
                <p>&nbsp;</p>
            </div>
            <%
               }
            %>
            
			<% 
                if(courses_fourth.size()!=0){
            %>
            <div class="biaoti">
                <h2>其他大四课程</h2>
            </div>
            <div id="box1">
                <%
                    for(CourseBean bean : courses_fourth){
                        String url = basePath+"servlet/CourseDetailServlet?course_id="+bean.getCourse_id();
                        %>
                            <div class="box">
                                <a href="<%=url %>" target="_blank">
                                    <%for(PictureBean pics : piclist){
                                        int is = bean.getCourse_picture_id().compareTo(pics.getPicture_id());
                                        if(is == 0){
                                            String pic_urls = basePath+pics.getPicture_img();
                                            %>
                                                <img src="<%=pic_urls %>" width="291" height="179">
                                    <%      break;
                                        }
                                      } 
                                    %>
                                </a>
                                <h2>
                                    <a href="<%=url %>" target="_blank"><%=bean.getCourse_name() %></a>
                                </h2>
                            </div>
                        <%                                  
                    }
                %> 
                <p>&nbsp;</p>
            </div>
            <%
               }
            %>
            
             <% 
                int allsize = courses_first.size()+courses_second.size() + courses_third.size() + courses_fourth.size();
                if(allsize == 0){
             %>
	            <div class="biaoti">
	                <h2>没有该专业课程，正拼命完善中..</h2>
	            </div>
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
