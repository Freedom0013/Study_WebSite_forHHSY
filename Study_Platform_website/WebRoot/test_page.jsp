<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- 判断el表达式是否为空的标准函数声明 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!-- 判断el集合是否为空的声明 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
		<title>测试</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/owl-carousel/owl.carousel.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/owl-carousel/owl.theme.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/onescroll/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/onescroll/css/component.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/headereffects/css/component.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/headereffects/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/plugins/pace/pace-theme-flash.css" media="screen" />
		<!-- bootstrap支持 -->
		<link href="${pageContext.request.contextPath }/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
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
							<button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle"
							type="button">
							<span class="sr-only">T</span> <span class="icon-bar"></span><span
							class="icon-bar"></span><span class="icon-bar"></span>
						</button>
						<a href="#" class="navbar-brand compressed">
							<img src="images/logo_black.png" alt="" data-src="images/logo_black.png"
							data-src-retina="images/logo_black.png" width="270" height="38"/></a>
						</div>
						<div class="navbar-collapse collapse">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="${pageContext.request.contextPath }/index.jsp">首&nbsp;页</a></li>
								<li><a href="#">专&nbsp;业&nbsp;大&nbsp;类</a></li>
								<li><a href="#">推&nbsp;荐&nbsp;资&nbsp;源</a></li>
								<li><a href="#">移&nbsp;动&nbsp;课&nbsp;堂</a></li>
								<li><a href="${pageContext.request.contextPath }/login.jsp">登&nbsp;录/注&nbsp;册</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="section first white">
				<!-- 大标题 -->
				<div class="section grey p-t-20  p-b-20">
					<div class="container">
						<div class="row">
							<div class="col-md-6">
								<h2 class="semi-bold">
								课程测试</h2>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="white bg-white padding-30">
               <!-- <div>
                    <h2>没有该课程试题，正拼命完善中..</h2>
                </div>  --> 

                <div id="testomonials" class="owl-carousel row">
                	<!--题目-->
                	<div class="item">
                		<h4><span class="semi-bold">第1题</span>：关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？</h4>
                		<div class="middle">
                			<input type="radio" name="1" class="regular-radio-test" id="radio1" value="A" /><label for="radio1"></label>
                			A、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="1" class="regular-radio-test" id="radio2" value="B" /><label for="radio2"></label>
                			B、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="1" class="regular-radio-test" id="radio3" value="C" /><label for="radio3"></label>
                			C、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="1" class="regular-radio-test" id="radio4" value="D" /><label for="radio4"></label>
                			D、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="1" class="regular-radio-test" id="radio5" value="E" checked/><label for="radio5"></label>
                			E、不知道；<br>
                		</div>
                	</div>

                	<!--题目-->
                	<div class="item">
                		<h4><span class="semi-bold">第2题</span>：关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？</h4>
                		<div class="middle">
                			<input type="radio" name="2" class="regular-radio-test" id="radio6" value="A" /><label for="radio6"></label>
                			A、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="2" class="regular-radio-test" id="radio7" value="B" /><label for="radio7"></label>
                			B、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="2" class="regular-radio-test" id="radio8" value="C" /><label for="radio8"></label>
                			C、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="2" class="regular-radio-test" id="radio9" value="D" /><label for="radio9"></label>
                			D、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="2" class="regular-radio-test" id="radio10" value="E" checked/><label for="radio10"></label>
                			E、不知道；<br>
                		</div>
                	</div>

                	<!--题目-->
                	<div class="item">
                		<h4><span class="semi-bold">第3题</span>：关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？</h4>
                		<div class="middle">
                			<input type="radio" name="3" class="regular-radio-test" id="radio11" value="A" /><label for="radio11"></label>
                			A、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="3" class="regular-radio-test" id="radio12" value="B" /><label for="radio12"></label>
                			B、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="3" class="regular-radio-test" id="radio13" value="C" /><label for="radio13"></label>
                			C、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="3" class="regular-radio-test" id="radio14" value="D" /><label for="radio14"></label>
                			D、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="3" class="regular-radio-test" id="radio15" value="E" checked/><label for="radio15"></label>
                			E、不知道；<br>
                		</div>
                	</div>

                	<!--题目-->
                	<div class="item">
                		<h4><span class="semi-bold">第4题</span>：关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？关于sleep()和wait()，以下描述错误的一项是？</h4>
                		<div class="middle">
                			<input type="radio" name="4" class="regular-radio-test" id="radio16" value="A" /><label for="radio16"></label>
                			A、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="4" class="regular-radio-test" id="radio17" value="B" /><label for="radio17"></label>
                			B、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="4" class="regular-radio-test" id="radio18" value="C" /><label for="radio18"></label>
                			C、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="4" class="regular-radio-test" id="radio19" value="D" /><label for="radio19"></label>
                			D、sleep是线程类（Thread）的方法，wait是Object类的方法；sleep不释放对象锁，wait放弃对象锁；<br>
                		</div>
                		<div class="middle">
                			<input type="radio" name="4" class="regular-radio-test" id="radio20" value="E" checked/><label for="radio20"></label>
                			E、不知道；<br>
                		</div>
                	</div>
                </div>
            </div>
        </div>

        <div class="p-t-10 p-b-20 text-center">
        	<a href="#" class="btn btn-primary btn-lg  btn-large m-r-10">现在交卷！</a>
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
        <!-- jquery支持 -->
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/boostrapv3/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath }/plugins/pace/pace.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/plugins/jquery-unveil/jquery.unveil.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/plugins/owl-carousel/owl.carousel.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/plugins/modernizr.custom.js"></script>
        <script src="${pageContext.request.contextPath }/plugins/waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath }/plugins/onescroll/js/classie.js"></script>
        <script src="${pageContext.request.contextPath }/plugins/onescroll/js/cbpScroller.js"></script>
        <script src="${pageContext.request.contextPath }/plugins/jquery-nicescroll/jquery.nicescroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/js/jquery.parallax-1.1.3.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/js/core.js" type="text/javascript"></script>
    </body>
</html>
