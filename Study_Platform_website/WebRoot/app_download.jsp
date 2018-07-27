<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- 判断el表达式是否为空的标准函数声明 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!-- 判断el集合是否为空的声明 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 该 DTD 包含所有 HTML 元素和属性，但不包括展示性的和弃用的元素（比如 font）。不允许框架集（Framesets）。 -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- html5 -->
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
		<title>App下载</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- 轮播插件 -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/owl-carousel/owl.carousel.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/owl-carousel/owl.theme.css" />
        <!-- 地址栏插件 -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/headereffects/css/component.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/headereffects/css/normalize.css"/>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/pace/pace-theme-flash.css" media="screen"/>
        <!-- bootstrap支持 -->
        <link href="${pageContext.request.contextPath }/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath }/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <!-- 图标字体库支持 -->
        <link href="${pageContext.request.contextPath }/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- css样式 -->
        <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath }/css/magic_space.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath }/css/responsive.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath }/css/animate.css" rel="stylesheet" type="text/css" />
  </head>
  
<body>
        <div class="main-wrapper">
            <!-- 导航栏 -->
            <div role="navigation" class="navbar navbar-default navbar-static-top">
                <div class="container">
                    <div class="compressed">

                        <div class="navbar-header">
                            <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                                <span class="sr-only">T</span> <span class="icon-bar"></span>
                                <span class="icon-bar"></span><span class="icon-bar"></span>
                            </button>
                            <a href="#" class="navbar-brand compressed">
                                <img src="${pageContext.request.contextPath }/images/logo_black.png" alt="" data-src="${pageContext.request.contextPath }/images/logo_black.png"
                                data-src-retina="${pageContext.request.contextPath }/images/logo_black.png" width="270" height="38" />
                            </a>
                        </div>

                        <div class="navbar-collapse collapse">
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="${pageContext.request.contextPath }/index.jsp">首&nbsp;页</a></li>
                                    <li><a href="#">专&nbsp;业&nbsp;大&nbsp;类</a></li>
                                    <li><a href="#">推&nbsp;荐&nbsp;资&nbsp;源</a></li>
                                    <li><a href="${pageContext.request.contextPath }/app_download.jsp">移&nbsp;动&nbsp;课&nbsp;堂</a></li>
		                            <!-- 这里为el表达式的if-else判断，在用户登录成功后显示登录成功的用户，在用户未登录时，显示登录按钮 -->
		                            <!-- 下面的el表达式判断用户名是否过长，如果过长隐藏过长用户名 -->
		                            <c:choose>
		                                <c:when test="${!empty user.user_name}">
		                                    <c:if test="${fn:length(user.user_name)>6 }">
		                                        <li><a href="#"><span><font style="color:#ff0000;">${fn:substring(user.user_name, 0, 6)}...,欢迎你</font></span></a></li>
		                                        <li><a href="${pageContext.request.contextPath }/servlet/WrittenOffServlet">退出登录</a></li>
		                                    </c:if>
		                                    <c:if test="${fn:length(user.user_name)<=6}">
		                                        <li><a href="#"><span><font style="color:#ff0000;">${fn:substring(user.user_name, 0, 6)},欢迎你</font></span></a></li>
		                                        <li><a href="${pageContext.request.contextPath }/servlet/WrittenOffServlet">退出登录</a></li>
		                                    </c:if>
		                                </c:when>
		                                <c:otherwise>
		                                   <li><a href="${pageContext.request.contextPath }/login.jsp">登&nbsp;录&nbsp;/&nbsp;注&nbsp;册</a></li>
		                                </c:otherwise>
		                            </c:choose>
                                </ul>
                            </div>
                    </div>
                </div>
            </div>

            <!-- 大类内容 -->
            <div class="section first white">
                <div class=" p-b-60">
                    <header id="fh5co-header">
                        <div class="container">
                            <div class="col-md-6 col-sm-6">
                                <div class="intro">
                                    <h1 id="fh5co-logo"><a href="index.html">知识森移动App</a></h1>
                                    <h2>免费的在线学习平台，满足您对知识的渴求！</h2>
                                    <p>
                                        <a href="http://119.27.161.250:8080/Study_Platform_website/studytree.apk" class="btn btn-primary"><i class="icon-apple"></i>立即下载！</a>
                                    </p>
                                </div>
                            </div>
                            <div class="col-md-6 col-sm-6">
                                <figure class="fh5co-intro-img">
                                    <img class="img-1 animate-box" src="${pageContext.request.contextPath }/images/screenshot_1.jpg">
                                    <img class="img-2 animate-box hidden-xs" src="${pageContext.request.contextPath }/images/screenshot_2.jpg">
                                </figure>
                            </div>
                        </div>
                    </header>

                     <div id="main">
                        <div class="container">
                            <div class="row fh5co-feature">
                                <div class="col-md-6 col-md-push-6">
                                    <div class="fh5co-copy">
                                        <div class="fh5co-copy-inner">
                                            <!-- <h2>Keep It Simple</h2>
                                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-md-pull-6">
                                    <div class="fh5co-img">
                                        <figure class="fh5co-figure animate-box">
                                            <%-- <img class="img-2" src="${pageContext.request.contextPath }/images/screenshot_1.jpg">  --%>  
                                        </figure>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <%--
                            <div class="row fh5co-feature fh5co-reverse">
                                <div class="col-md-6">
                                    <div class="fh5co-copy">
                                        <div class="fh5co-copy-inner">
                                            <h2>Pixel Perfect</h2>
                                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="fh5co-img">
                                        <figure class="fh5co-figure animate-box">
                                            <img class="img-2" src="${pageContext.request.contextPath }/images/screenshot_2.jpg">   
                                        </figure>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> --%>
            <!--联系我们div块-->
            <div class="section black contact-details green-icons">
                <div class="container">
                    <div class="row">
                        <!--联系方式1-->
                        <div class="col-md-3 text-center">
                            <div class="services-box-3">
                                <!-- 图标库引用图标 -->
                                <i class="fa fa-phone"></i>
                                <div class="content">
                                    <p>
                                    驿站热线</p>
                                    <h3>
                                    +86 187 360 XXXXX</h3>
                                </div>
                            </div>
                        </div>

                        <!--联系方式2-->
                        <a href="https://mail.qq.com/" target="_blank">
                            <div class="col-md-3 text-center">
                                <div class="services-box-3">
                                    <i class="fa fa-envelope"></i>
                                    <div class="content">
                                        <p>
                                        飞鸽传书</p>
                                        <h3>
                                        846***723@qq.com</h3>
                                    </div>
                                </div>
                            </div>
                        </a>

                        <!--联系方式3-->
                        <a href="https://map.baidu.com/" target="_blank">
                            <div class="col-md-3 text-center">
                                <div class="services-box-3">
                                    <i class="fa fa-map-marker "></i>
                                    <div class="content">
                                        <p>
                                        线下交流</p>
                                        <h3>
                                        XX市，XX区，XX大厦</h3>
                                    </div>
                                </div>
                            </div>
                        </a>

                        <!--联系方式4-->
                        <a href="https://weibo.com/" target="_blank">
                            <div class="col-md-3 text-center">
                                <div class="services-box-3">
                                    <i class="fa fa-heart"></i>
                                    <div class="content">
                                        <p>
                                            新浪微博
                                        </p>
                                        <h3>
                                        知识森驿站</h3>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- footer块 -->
            <div class="section white footer">
                <div class="container">
                    <div class="p-t-30 p-b-50">
                        <div class="row">
                            <div class="col-md-2 col-lg-2 col-sm-2 col-xs-12 xs-m-b-20">
                                <img src="images/logo_black.png" alt="" data-src="images/logo_black.png"
                                data-src-retina="images/logo_black.png" width="119" height="22" />
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
                                        <a href="http://www.beian.gov.cn/portal/index?token=cc32e26d-5cfa-4df2-bb3c-93484830be8d">京公安网备005xxxXxxxxx774号</a></div>
                                        <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">京ICP备xxx0xxx2号</a>
                                    </div>
                                    <div class="clearfix">
                                    </div>
                                </div>
                                <div class="col-md-2 col-lg-2 col-sm-2  col-xs-12 xs-m-b-20">
                                    <div class="bold">
                                    违法和不良信息举报电话：185-0130-1238</div>
                                    <a href="javascript:">京网文[2018]XXX9-XXX9号 </a>
                                </div>
                                <div class="col-md-2 col-lg-2 col-sm-2  col-xs-12 ">
                                    <div class="bold">
                                    意见反馈</div>
                                    <br />
                                    <a href="#">联系我们</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- jquery支持 -->
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>
        <!-- bootstrap js支持 -->
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
        <!-- 动画支持 -->
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/pace/pace.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-unveil/jquery.unveil.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/modernizr.custom.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/waypoints.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-nicescroll/jquery.nicescroll.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jquery-isotope/jquery.isotope.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/core.js"></script>
    </body>
</html>
