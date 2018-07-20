<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- 判断el表达式是否为空的标准函数声明 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!-- 判断el集合是否为空的声明 -->
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

<!DOCTYPE HTML>
<html>
<head>
		<!-- 编码设置 -->
		<meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
		<meta charset="utf-8"/>
		<base href="<%=basePath%>">

		<!-- 设置jsp地址栏小图标 -->
		<link rel="icon" href="<%=basePath%>images/logo_icon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="<%=basePath%>images/logo_icon.ico" type="image/x-icon">
		<title>知识森驿站</title>
		
		<!-- 移动端兼容设置 -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta content="" name="description" />
		<meta content="" name="author" />  
		<!-- 轮播插件 -->
		<link href="${pageContext.request.contextPath }/plugins/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css"/>
		<link href="${pageContext.request.contextPath }/plugins/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css"/>
		<!-- 地址栏插件 -->
		<link href="${pageContext.request.contextPath }/plugins/headereffects/css/component.css" rel="stylesheet" type="text/css"/>
		<link href="${pageContext.request.contextPath }/plugins/headereffects/css/normalize.css" rel="stylesheet" type="text/css"/>
		<link href="${pageContext.request.contextPath }/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
		<!-- bootstrap支持 -->
		<link href="${pageContext.request.contextPath }/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
		<!-- 图标字体库支持 -->
		<link href="${pageContext.request.contextPath }/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<!-- 动画支持 -->
		<link href="${pageContext.request.contextPath }/css/animate.css" rel="stylesheet">
		<!-- css样式 -->
		<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/magic_space.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/responsive.css" rel="stylesheet" type="text/css" />    
		<!-- 大类item小图标（评论暂时隐藏）地址 -->
		<link href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css' rel='stylesheet prefetch' >
		<link href="${pageContext.request.contextPath }/plugins/slider-plugin/css/settings.css" rel="stylesheet" type="text/css" media="screen" />
		<!-- jquery支持 -->
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>
	    <%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script> --%>
		<!-- 旋转滑动4.x脚本 -->
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/slider-plugin/js/slider1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/slider-plugin/js/slider2.min.js"></script>
</head>

<body>
	<div class="main-wrapper">
		<!-- footer块 -->
		<!-- <div class="section white footer"> -->
			<div class="container">
				<div class="p-t-60 p-b-60">
					<div class="row">
						<div class="col-md-2 col-lg-2 col-sm-2 col-xs-12 xs-m-b-20">
							<img src="${pageContext.request.contextPath }/images/logo_black.png" alt="" data-src="${pageContext.request.contextPath }/images/logo_black.png"
							data-src-retina="${pageContext.request.contextPath }/images/logo_black.png" width="119" height="22" />
							<br />
							<br />
							© 知识森林网络事业部
							<br />
						</div>
						<div class="col-md-4 col-lg-3 col-sm-4  col-xs-12 xs-m-b-20">
							<address class="xs-no-padding  col-md-6 col-lg-6 col-sm-6  col-xs-12">
								北京市 海淀区<br>
								中关村<br>
								中国(China)
							</address>
							<div class="xs-no-padding col-md-6 col-lg-6 col-sm-6">
								<div>
									<a href="http://www.beian.gov.cn/portal/index?token=cc32e26d-5cfa-4df2-bb3c-93484830be8d">京公安网备005xxxXxxxxx774号</a>
								</div>
								<a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">京ICP备xxx0xxx2号</a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-2 col-lg-2 col-sm-2  col-xs-12 xs-m-b-20">
							<div class="bold">违法和不良信息举报电话：185-0130-1238</div>
							<a href="javascript:">京网文[2018]XXX9-XXX9号 </a>
						</div>
						<div class="col-md-2 col-lg-2 col-sm-2  col-xs-12 ">
							<div class="bold">意见反馈</div>
							<br />
							<a href="#">联系我们</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- </div> -->
	<!-- bootstrap js支持 -->
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	    <!-- 动画支持 -->
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/pace/pace.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-unveil/jquery.unveil.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/owl-carousel/owl.carousel.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/waypoints.min.js"></script>
	    <!-- <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/parrallax/js/jquery.parallax-1.1.3.js"></script> -->
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-nicescroll/jquery.nicescroll.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-appear/jquery.appear.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-numberAnimate/jquery.animateNumbers.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/core.js"></script>
	    <!-- wow页面滚动特效（大类item） -->
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/wow.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/site.js"></script>
</body>
</html>
