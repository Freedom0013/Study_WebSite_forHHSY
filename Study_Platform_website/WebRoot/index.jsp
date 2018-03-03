<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- 判断el表达式是否为空的标准函数声明 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!-- 判断el集合是否为空的声明 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>

<script type="text/javascript">
    function init() {
		$.ajax({
			url : "${pageContext.request.contextPath }/servlet/DepartmentServlet", //servlet文件的名称  
			type : "GET",
			success : function(msg) {
				var doms = eval("(" + msg + ")");
				var data = doms.root;
				var department_box_div = document.getElementById('department_box');
				if(data != null){
					for(var i in data){
						//表示遍历数组，而i表示的是数组的下标值，
						//data[i]表示获得第i个json对象即JSONObject
						//data[i]通过.字段名称即可获得指定字段的值
						
						data[i].department_addtime;
						data[i].department_caption;
						data[i].department_picture_id;
						
						var department_item_box = document.createElement('div');
						department_item_box.id = 'department_item_box' + i;
						department_item_box.className = "box";
						department_box_div.appendChild(department_item_box);
						
						var image_a = document.createElement("a");
						var url = '${pageContext.request.contextPath }/servlet/ProfessionalServlet?department_id=' +data[i].department_id;
						image_a.setAttribute('href',url);
						image_a.setAttribute('target','_blank');
						department_item_box.appendChild(image_a);
						
						var department_img = document.createElement("img");
						department_img.src = "${pageContext.request.contextPath }/images/box1.png";
						department_img.width = '291';
						department_img.height = '179';
						image_a.appendChild(department_img);
						
						var h2 = document.createElement("h2"); 
						h2.className = "title1";
						
						var title_a = document.createElement("a");
                        var url = '${pageContext.request.contextPath }/servlet/ProfessionalServlet?department_id=' + data[i].department_id;
                        title_a.setAttribute('href',url);
                        title_a.setAttribute('target','_blank');
                        var department_name = document.createTextNode(data[i].department_name);
                        title_a.appendChild(department_name);
                        
                        h2.appendChild(title_a); 
                        department_item_box.appendChild(h2);
			    	}
				}
			},error : function() {
				alert("院系数据获取失败!请稍后重试!");
			}
		});
	}
</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="utf-8">
        <base href="<%=basePath%>">
        <!-- 设置jsp地址栏小图标 -->
        <link rel="icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="<%=basePath%>images/system_image/logo_page_icon.ico" type="image/x-icon">
        
        <title>学习平台</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/test.css" type="text/css" />
        <link type="text/css" href="${pageContext.request.contextPath }/css/css.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/JQ.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/zzsc.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.XYTipsWindow.min.2.8.js"></script>
        <%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/index.js"></script> --%>
		<link type="text/css" href="${pageContext.request.contextPath }/css/box_style.css" rel="stylesheet" />
    </head>

	<body onload="init()">
	<div id="Top">
		<br> <img
			src="${pageContext.request.contextPath }/images/logo.png" width="315" height="60" />
		<div id="center">
			<div class="nav">
				<ul>
					<li class="cur"><a href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/zhuanye/zhuanye1.html" target="_blank">专业</a></li>
					<li><a href="${pageContext.request.contextPath }/资源.html" target="_blank">资源</a></li>
					<li><a href="${pageContext.request.contextPath }/推荐书籍.html" target="_blank">推荐书籍</a></li>
					<li><a href="#" target="_blank">移动课堂</a></li>
				</ul>
				<div class="curBg"></div>
				<div class="cls"></div>
			</div>
		</div>
		<div id="Top-login">
			<!-- 这里为el表达式的if-else判断，在用户登录成功后显示登录成功的用户，在用户未登录时，显示登录按钮 -->
			<!-- 下面的el表达式判断用户名是否过长，如果过长隐藏过长用户名 -->
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
	<div class="banner">
			<ul class="list">
				<li style="display: list-item;" class="bg2">
				    <a href="#" target="_blank"> 
				        <img src="${pageContext.request.contextPath }/images/bannerImg2.jpg" alt="" height="408" width="980">
						<p class="text1">
							<img src="${pageContext.request.contextPath }/images/banner2-1.png" alt="" height="511" width="543">
						</p>
						<p class="text2">
							<img src="${pageContext.request.contextPath }/images/banner2-2.png" alt="" height="511" width="543">
						</p>
						<p class="text3">
							<img src="${pageContext.request.contextPath }/images/banner2-3.png" alt="" height="164" width="401">
						</p>
						<p class="text4">
							<img src="${pageContext.request.contextPath }/images/banner2-4.png" alt="" height="72" width="144">
						</p>
						<p class="text5">
							<img src="${pageContext.request.contextPath }/images/banner2-5.png" alt="" height="66" width="378">
						</p>
						<p class="text6">
							<img src="${pageContext.request.contextPath }/images/banner2-6.png" alt="" height="33" width="321">
						</p>
						<p class="text7">
							<img src="${pageContext.request.contextPath }/images/banner2-7.png" alt="" width="390">
						</p>
				</a></li>
				<li style="display: none;" class="bg4"><a href="#"
					target="_blank"> <img src="${pageContext.request.contextPath }/images/bannerImg4.jpg" alt=""
						height="408" width="980">
						<p class="text1">我想</p>
						<p class="text2">
							<img src="${pageContext.request.contextPath }/images/banner4-1.png" alt="" height="18" width="66">
						</p>
						<p class="text3">
							<img src="${pageContext.request.contextPath }/images/banner4-4.png" alt="" height="94" width="167">
						</p>
						<p class="text4">
							<img src="${pageContext.request.contextPath }/images/banner4-6.png" alt="" height="106" width="432">
						</p>
						<p class="text5">
							<img src="${pageContext.request.contextPath }/images/banner4-5.png" alt="" height="57" width="237">
						</p>
						<p class="text6">
							<img src="${pageContext.request.contextPath }/images/banner4-2.png" alt="" height="62" width="304">
						</p>
						<p class="text7">
							<img src="${pageContext.request.contextPath }/images/banner4-3.png" alt="" height="240" width="197">
						</p>
				</a></li>
				<li style="display: none;" class="bg3"><a href="#"
					target="_blank">
						<p class="text1">
							<img src="${pageContext.request.contextPath }/images/banner3-1.png" alt="" height="59" width="980">
						</p>
						<p class="text2-1">
							<img src="${pageContext.request.contextPath }/images/banner3-2-1.png" alt="" height="277" width="359">
						</p>
						<p class="text2-2">
							<img src="${pageContext.request.contextPath }/images/banner3-2-2.png" alt="" height="408" width="437">
						</p>
						<p class="text3">
							<img src="${pageContext.request.contextPath }/images/banner3-3.png" alt="" height="408" width="871">
						</p>
						<p class="text4">选择博知，选择品质</p>
						<p class="text5">
							<img src="${pageContext.request.contextPath }/images/banner3-4.png" alt="" height="44" width="782">
						</p>
						<p class="text6">丰富的课程资源</p>
						<p class="text7">及时的专业解答</p>
						<p class="text8">自由的交流体验</p>
				</a></li>
				<li style="display: none;" class="bg1"><a href="#"
					target="_blank"> <img src="${pageContext.request.contextPath }/images/1383531804.jpg" alt=""
						height="408" width="980">
						<p class="text1 hover">
							<img src="${pageContext.request.contextPath }/images/1383539870.png" alt="" height="228" width="219">
						</p>
						<p class="text2 hover">
							<img src="${pageContext.request.contextPath }/images/1383535692.png" alt="" height="53" width="423">
						</p>
						<p class="text3 hover">
							<img src="${pageContext.request.contextPath }/images/1383533951.png" alt="" height="25" width="286">
						</p>
				</a></li>
			</ul>
			<ul class="btn"></ul>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/focus.js"></script>
	
		<div id="middle">
			<div id="middle-1">
				<div id="mid">
					<h2>学习平台</h2>
				</div>
	
				<div class="style_1">
					<form method="get" id="searchform" action="#">
						<!--搜索跳转网址 -->
						<fieldset>
							<input id="s" name="s" type="text" value="输入要搜索的内容"
								class="text_input"
								onblur="if(this.value=='输入要搜索的内容'){this.value='';}"
								onfocus="if(this.value =='输入要搜索的内容') {this.value=''; }" /> <input
								name="submit" type="submit" value />
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		
		
		<div id="content">
			<div class="biaoti">
				<h2>院 系</h2>
			</div>
        
            <div id="department_box"></div>
            
		</div>
		<div id="footer">
	
			<p>Copyright © 2017 YRCTI. All Rights Reserved.</p>
			<p>
				黄河水利职业技术学院 版权所有 | <a href="#" target="_blank" rel="nofollow">博知课堂服务协议</a>
				| <a href="#">站点地图</a> | <a href="#" target="_blank">侵权投诉</a> | <a
					href="#" rel="nofollow" report-tdw="action=Feedback" target="_blank">问题反馈</a>
				| <a href="#" target="_blank">帮助</a>
			</p>
		</div>
	</body>
</html>
