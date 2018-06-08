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
		<title>知识森驿站</title>
		
		<!-- 移动端兼容设置 -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta content="" name="description" />
		<meta content="" name="author" />  
		<!-- 轮播插件 -->
		<link href="<%=basePath%>plugins/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css"/>
		<link href="<%=basePath%>plugins/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css"/>
		<!-- 地址栏插件 -->
		<link href="<%=basePath%>plugins/headereffects/css/component.css" rel="stylesheet" type="text/css"/>
		<link href="<%=basePath%>plugins/headereffects/css/normalize.css" rel="stylesheet" type="text/css"/>
		<link href="<%=basePath%>plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
		<!-- bootstrap支持 -->
		<link href="<%=basePath%>plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
		<!-- 图标字体库支持 -->
		<link href="<%=basePath%>plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<!-- 动画支持 -->
		<link href="<%=basePath%>css/animate.css" rel="stylesheet">
		<!-- css样式 -->
		<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/magic_space.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/responsive.css" rel="stylesheet" type="text/css" />    
		<!-- 大类item小图标（评论暂时隐藏）地址 -->
		<link href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css' rel='stylesheet prefetch' >
		<link href="<%=basePath%>plugins/slider-plugin/css/settings.css" rel="stylesheet" type="text/css" media="screen" />
		<!-- jquery支持 -->
	    <script type="text/javascript" src="<%=basePath%>js/jquery-2.1.3.min.js"></script>
	    <%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script> --%>
		<!-- 旋转滑动4.x脚本 -->
		
		<script type="text/javascript" src="<%=basePath%>plugins/slider-plugin/js/slider1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugins/slider-plugin/js/slider2.min.js"></script>
		<!-- 排行榜轮播函数 -->
		<script type="text/javascript"> 
			function autoScroll(obj){  
				$(obj).find("ul").animate({  
					marginTop : "-39px"  
				},500,function(){  
					$(this).css({marginTop : "0px"}).find("li:first").appendTo(this);  
				})  
			}  
			$(function(){  
				setInterval('autoScroll(".maquee")',3000);
			}) 
		</script>
	</head>

	<body>
		<div class="main-wrapper">
            <!--头部地址栏（下拉时显示黑灰）-->
            <header id="ha-header" class="ha-header ha-header-hide ">
                <div class="ha-header-perspective">
                    <div class="ha-header-front navbar navbar-default">
                        <div class="compressed">
                            <div class="navbar-header">
                                <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle"
                                    type="button">
                                    <span class="sr-only">T</span> <span class="icon-bar"></span><span
                                        class="icon-bar"></span><span class="icon-bar"></span>
                                </button>
                                <!-- logo -->
                                <a href="#" class="navbar-brand compressed">
                                    <img src="${pageContext.request.contextPath }/images/logo_black.png" alt="" data-src="${pageContext.request.contextPath }/images/logo_black.png"
                                        data-src-retina="${pageContext.request.contextPath }/images/logo_black.png" width="270" height="38" /></a>
                            </div>
                            <div class="navbar-collapse collapse">
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="${pageContext.request.contextPath }/index.jsp">首&nbsp;页</a></li>
                                    <li><a href="#">专&nbsp;业&nbsp;大&nbsp;类</a></li>
                                    <li><a href="#">推&nbsp;荐&nbsp;资&nbsp;源</a></li>
                                    <li><a href="${pageContext.request.contextPath }/app_download.jsp">移&nbsp;动&nbsp;课&nbsp;堂</a></li>
                                    <li><a href="${pageContext.request.contextPath }/login.jsp">登&nbsp;录&nbsp;/&nbsp;注&nbsp;册</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        
            <!--banner白色导航栏-->
            <div class="section ha-waypoint" data-animate-down="ha-header-hide" data-animate-up="ha-header-hide">
                <div role="navigation" class="navbar navbar-transparent navbar-top">
                    <div class="container">
                        <div class="compressed">
                            <div class="navbar-header">
                                <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle"
                                    type="button">
                                    <span class="sr-only">T</span> <span class="icon-bar"></span><span
                                        class="icon-bar"></span><span class="icon-bar"></span>
                                </button>
                                <!-- logo -->
                                <a href="#" class="navbar-brand">
                                    <img src="${pageContext.request.contextPath }/images/logo_white.png" data-src="${pageContext.request.contextPath }/images/logo_white.png" data-src-retina="${pageContext.request.contextPath }/images/logo_white.png"
                                        width="270" height="38" alt="" /></a>
                            </div>
                            <div class="navbar-collapse collapse">
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="${pageContext.request.contextPath }/index.jsp">首&nbsp;页</a></li>
                                    <li><a href="#">专&nbsp;业&nbsp;大&nbsp;类</a></li>
                                    <li><a href="#">推&nbsp;荐&nbsp;资&nbsp;源</a></li>
                                    <li><a href="${pageContext.request.contextPath }/app_download.jsp">移&nbsp;动&nbsp;课&nbsp;堂</a></li>
                                    <li><a href="${pageContext.request.contextPath }/login.jsp">登&nbsp;录/注&nbsp;册</a></li>
                                </ul>
                            </div>
                            <!--地址栏结束 -->
                        </div>
                    </div>
                </div>
            
                <!--banner容器 -->
                <div class="tp-banner-container">
                    <div class="tp-banner" id="home">
                        <ul>
                            <!-- banner1 -->
                            <li data-transition="fade" data-slotamount="5" data-masterspeed="700">
                                <!-- banner图片 -->
                                <img src="${pageContext.request.contextPath }/images/bg/slide_one.jpg" alt="slidebg1" data-bgfit="cover" data-bgposition="center center"
                                    data-bgrepeat="no-repeat">
                                <!-- banner配字 -->
                                <div class="tp-caption mediumlarge_light_white_center sft tp-resizeme slider" data-x="center"
                                    data-hoffset="0" data-y="80" data-speed="500" data-start="800" data-easing="Power4.easeOut"
                                    data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <h2 class="text-white custom-font title ">
                                    我们爱学习<br>
                                    12大类&nbsp;上百种细分知识之树!<br />
                                    任你遨游在知识之森!</h3>
                                </div>
                                <!--banner按钮-->
                                <div class="tp-caption sfb slider tp-resizeme slider" data-x="center" data-hoffset="0"
                                    data-y="320" data-speed="800" data-start="1000" data-easing="Power4.easeOut"
                                    data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <!--<a href="#" class="btn btn-info btn-lg  btn-large m-r-10">Download Template Now</a>-->
                                </div>
                            </li>
                            
                            <!-- banner2 -->
                            <li data-transition="fade" data-slotamount="5" data-masterspeed="700">
                                <!-- banner图片 -->
                                <img src="${pageContext.request.contextPath }/images/bg/picture-1.jpg" alt="slidebg2" data-bgfit="cover" data-bgposition="center center"
                                    data-bgrepeat="no-repeat">
                                <!-- banner配字 -->
                                <div class="tp-caption mediumlarge_light_white_center sft tp-resizeme slider" data-x="center"
                                    data-hoffset="0" data-y="120" data-speed="500" data-start="800" data-easing="Power4.easeOut"
                                    data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <h2 class="text-white custom-font title ">
                                        不是一人在战斗<br>
                                        和全国学生一起挖掘知识森林!</h2>
                                </div>
                                <!--banner按钮（常规）-->
                                <div class="tp-caption sfb slider tp-resizeme slider" data-x="center" data-hoffset="0"
                                    data-y="300" data-speed="800" data-start="1000" data-easing="Power4.easeOut"
                                    data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <!--<a href="#" class="btn btn-info btn-lg  btn-large m-r-10">Know More About Us</a>-->
                                </div>
                                <!--banner按钮（小字说明按钮）-->
                                <div class="tp-caption fade slider tp-resizeme slider" data-x="center" data-hoffset="0"
                                    data-y="360" data-speed="500" data-start="800" data-easing="Power4.easeOut" data-endspeed="300"
                                    data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <!--<a href="#" class="text-white m-r-10">or view our pricing</a>-->
                                </div>
                            </li>
                            
                            <!-- banner3 -->
                            <li data-transition="fade" data-slotamount="5" data-masterspeed="700">
                                <!-- banner图片 -->
                                <img src="${pageContext.request.contextPath }/images/bg/picture-2.jpg" alt="slidebg2" data-bgfit="cover" data-bgposition="center center"
                                    data-bgrepeat="no-repeat">
                                <!-- banner配字 -->
                                <div class="tp-caption mediumlarge_light_white_center sft tp-resizeme slider" data-x="center"
                                    data-hoffset="0" data-y="120" data-speed="500" data-start="800" data-easing="Power4.easeOut"
                                    data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <h2 class="text-white custom-font title ">
                                        个性化资源推荐<br>
                                        在遨游知识深林时怎能忘却事半功倍之法！</h2>
                                </div>
                                <!--banner按钮（常规）-->
                                <div class="tp-caption sfb slider tp-resizeme slider" data-x="center" data-hoffset="0"
                                    data-y="300" data-speed="800" data-start="1000" data-easing="Power4.easeOut"
                                    data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                                    <!--<a href="#" class="btn btn-info btn-lg  btn-large m-r-10">现在就去</a>-->
                                </div>
                            </li>
                        </ul>
                        
                        <!--banner切换时间计时器-->
                        <div class="tp-bannertimer"></div>
                        <!-- js在这里还会在这里加上banner的左右切换按钮 -->
                    </div>
                </div>
                <!--banner轮播结束 -->
            </div>
<!--app宣传div块-->
            <div class="section ">
                <div class="container">
                    <div class="p-t-40 p-b-40  text-center">
                        <h3 class="text-center">
                            配合移动端探索知识效率更高！更多精彩活动尽在知之森驿站app!</h3>
                        <a href="${pageContext.request.contextPath }/app_download.jsp" class="btn btn-primary btn-lg  btn-large m-r-10">现在下载</a>
                    </div>
                </div>
            </div>
        
            <!--特色介绍div块（6块）-->
            <div class="section white ha-waypoint" data-animate-down="ha-header-color" data-animate-up="ha-header-hide">
                <div class="container">
                    <div class="p-t-60">
						<!-- 大类列表 -->
                        <div class="row">
                            <div class="col-md-4 wow fadeInRight" data-wow-delay="200ms">
                            	<!-- 此处容器调整错误，加了很多空行以补全bug -->
                                </br></br></br></br></br></br></br></br></br></br></br></br>
                                </br></br></br></br></br></br></br></br></br></br>
                                <article class="card">
                                	<!-- item图片 -->
                                    <header class="card__thumb">
                                        <a href="${pageContext.request.contextPath }/professional_detail.jsp">
                                            <img src="${pageContext.request.contextPath }/images/department_tumu_icon.jpg"/ width="370" height="245">
                                        </a>
                                    </header>
            						<!-- item说明 -->
                                    <div class="card__body">
                                        <!-- <div class="card__category"><a href="#">小标签</a></div> -->
                                        <h2 class="card__title"><a href="${pageContext.request.contextPath }/professional_detail.jsp">土木交通工程大类</a></h2>
                                        <div class="card__subtitle">副标题</div> 
                                        <p class="card__description">正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。我们在通往地狱的高速公路上。</p>
                                    </div>
            						<!-- 被隐藏的评论 -->
                                    <!-- <footer class="card__footer">
                                        <span class="icon ion-clock"> 6分钟前</span>
                                        <span class="icon ion-chatbox"></span>
                                        <a href="#"> 42 评论</a>
                                    </footer> -->
                                </article>
                                <br/>
                            </div>
                            <div class="col-md-4 wow fadeInRight" data-wow-delay="300ms">
                                </br></br></br></br></br></br></br></br></br></br></br></br>
                                </br></br></br></br></br></br></br></br></br></br>
                                <article class="card">
                                    <header class="card__thumb">
                                        <a href="${pageContext.request.contextPath }/professional_detail.jsp">
                                            <img src="${pageContext.request.contextPath }/images/department_tumu_icon.jpg"/ width="370" height="245">
                                        </a>
                                    </header>
            
                                    <div class="card__body">
                                        <!-- <div class="card__category"><a href="#">小标签</a></div> -->
                                        <h2 class="card__title"><a href="${pageContext.request.contextPath }/professional_detail.jsp">土木交通工程大类</a></h2>
                                        <div class="card__subtitle">副标题</div> 
                                        <p class="card__description">正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。我们在通往地狱的高速公路上。</p>
                                    </div>
            
                                    <!-- <footer class="card__footer">
                                        <span class="icon ion-clock"> 6分钟前</span>
                                        <span class="icon ion-chatbox"></span>
                                        <a href="#"> 42 评论</a>
                                    </footer> -->
                                </article>
                                <br/>
                            </div>
            				
                            <div class="col-md-4 wow fadeInRight" data-wow-delay="400ms">
                                </br></br></br></br></br></br></br></br></br></br></br></br>
                                </br></br></br></br></br></br></br></br></br></br>
                                <article class="card">
                                    <header class="card__thumb">
                                        <a href="${pageContext.request.contextPath }/professional_detail.jsp">
                                            <img src="${pageContext.request.contextPath }/images/department_tumu_icon.jpg"/ width="370" height="245">
                                        </a>
                                    </header>
            
                                    <div class="card__body">
                                        <!-- <div class="card__category"><a href="#">小标签</a></div> -->
                                        <h2 class="card__title"><a href="${pageContext.request.contextPath }/professional_detail.jsp">土木交通工程大类</a></h2>
                                        <div class="card__subtitle">副标题</div> 
                                        <p class="card__description">正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。我们在通往地狱的高速公路上。</p>
                                    </div>
            
                                    <!-- <footer class="card__footer">
                                        <span class="icon ion-clock"> 6分钟前</span>
                                        <span class="icon ion-chatbox"></span>
                                        <a href="#"> 42 评论</a>
                                    </footer> -->
                                </article>
                                <br/>
                            </div>
                        </div>
            			
                        <div class="row">
                            <div class="col-md-4 wow fadeInRight" data-wow-delay="200ms">
                                </br></br></br></br></br></br></br></br></br></br></br></br>
                                </br></br></br></br></br></br></br></br></br></br>
                                <article class="card">
                                    <header class="card__thumb">
                                        <a href="#">
                                            <img src="${pageContext.request.contextPath }/images/department_tumu_icon.jpg"/ width="370" height="245">
                                        </a>
                                    </header>
            
                                    <div class="card__body">
                                        <!-- <div class="card__category"><a href="#">小标签</a></div> -->
                                        <h2 class="card__title"><a href="#">土木交通工程大类</a></h2>
                                        <div class="card__subtitle">副标题</div> 
                                        <p class="card__description">正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。我们在通往地狱的高速公路上。</p>
                                    </div>
            
                                    <!-- <footer class="card__footer">
                                        <span class="icon ion-clock"> 6分钟前</span>
                                        <span class="icon ion-chatbox"></span>
                                        <a href="#"> 42 评论</a>
                                    </footer> -->
                                </article>
                                <br/>
                            </div>
            
                            <div class="col-md-4 wow fadeInRight" data-wow-delay="300ms">
                                </br></br></br></br></br></br></br></br></br></br></br></br>
                                </br></br></br></br></br></br></br></br></br></br>
                                <article class="card">
                                    <header class="card__thumb">
                                        <a href="#">
                                            <img src="${pageContext.request.contextPath }/images/department_tumu_icon.jpg"/ width="370" height="245">
                                        </a>
                                    </header>
            
                                    <div class="card__body">
                                        <!-- <div class="card__category"><a href="#">小标签</a></div> -->
                                        <h2 class="card__title"><a href="#">土木交通工程大类</a></h2>
                                        <div class="card__subtitle">副标题</div> 
                                        <p class="card__description">正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。我们在通往地狱的高速公路上。</p>
                                    </div>
            
                                    <!-- <footer class="card__footer">
                                        <span class="icon ion-clock"> 6分钟前</span>
                                        <span class="icon ion-chatbox"></span>
                                        <a href="#"> 42 评论</a>
                                    </footer> -->
                                </article>
                                <br/>
                            </div>
            
                            <div class="col-md-4 wow fadeInRight" data-wow-delay="400ms">
                                </br></br></br></br></br></br></br></br></br></br></br></br>
                                </br></br></br></br></br></br></br></br></br></br>
                                <article class="card">
                                    <header class="card__thumb">
                                        <a href="#">
                                            <img src="${pageContext.request.contextPath }/images/department_tumu_icon.jpg"/ width="370" height="245">
                                        </a>
                                    </header>
            
                                    <div class="card__body">
                                        <!-- <div class="card__category"><a href="#">小标签</a></div> -->
                                        <h2 class="card__title"><a href="#">土木交通工程大类</a></h2>
                                        <div class="card__subtitle">副标题</div> 
                                        <p class="card__description">正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。正文的详细内容描述，我们在通往地狱的高速公路上，本德医生正在煎熬。我们在通往地狱的高速公路上。</p>
                                    </div>
            
                                    <!-- <footer class="card__footer">
                                        <span class="icon ion-clock"> 6分钟前</span>
                                        <span class="icon ion-chatbox"></span>
                                        <a href="#"> 42 评论</a>
                                    </footer> -->
                                </article>
                                <br/>
                            </div>
    					</div>

                    	<div class="clearfix">

                    	</br></br>
                    	</div>
                	</div>
            	</div>
        	</div>

	        <!--统计条-->
	        <div class="section white">
				<!-- 排行榜 -->
				<div class="Top_Record">
					<div class="record_Top"><p>知识之森探索排行榜</p></div>
				    <div class="topRec_List">
				  		<dl>
				        	<dd>排名</dd>
				        	<dd>昵称</dd>
				        	<dd>探索分</dd>
				        	<dd>加入时间</dd>
				        </dl>
				        <div class="maquee">
				            <ul>
				                <li>
				                    <div>1</div>
				                    <div>会飞的鱼</div>
				                    <div>457885</div>
				                    <div>2016/12/30 14:20</div>
				                </li> 
				                <li>
				                    <div>2</div>
				                    <div>我爱学习</div>
				                    <div>457800</div>
				                    <div>2017/12/30 14:20</div>
				                </li> 
				                <li>
				                    <div>3</div>
				                    <div>顺风耳</div>
				                    <div>457710</div>
				                    <div>2018/12/30 14:20</div>
				                </li> 
				                <li>
				                    <div>4</div>
				                    <div>学海无涯</div>
				                    <div>457610</div>
				                    <div>2017/12/30 14:20</div>
				                </li> 
				                <li>
				                    <div>5</div>
				                    <div>哇哈哈哈</div>
				                    <div>456660</div>
				                    <div>2014/12/30 14:20</div>
				                </li> 
				                <li>
				                    <div>6</div>
				                    <div>风口亮剑</div>
				                    <div>456610</div>
				                    <div>2014/12/30 14:20</div>
				                </li> 
				            </ul>
				        </div>
				    </div>
				</div> 
	        </div>
        
	        <!--使用者评论div块-->
	        <div class="section grey">
	            <div class="container">
	                <div class="p-t-60 p-b-50">
	                    <div id="testomonials" class="owl-carousel row">
	                    
	                    	<!--用户1号-->
	                        <div class="item">
	                            <div class="col-md-6  col-md-offset-3 text-center">
	                                <div class="testimonial-thumb">
	                                    <img src="${pageContext.request.contextPath }/images/testimonial_img1.png" alt="testimonal">
	                                </div>
	                                <div class="testimonial-user">
	                                    <span>马同学</span> 中国 上海
	                                </div>
	                                <h3 class="normal text-center">
	                                    I have always received good service from the Frittt Templates. Timing and quality
	                                    have always met my expectations and everything is communicated in a professional
	                                    and timely manner.
	                                </h3>
	                            </div>
	                        </div>
	                        
	                        <!--用户2号-->
	                        <div class="item">
	                            <div class="col-md-6   col-md-offset-3 text-center">
	                                <div class="testimonial-thumb">
	                                    <img src="${pageContext.request.contextPath }/images/testimonial_img2.png" alt="testimonal">
	                                </div>
	                                <div class="testimonial-user">
	                                    <span>王同学</span> 中国 北京
	                                </div>
	                                <h3 class="normal text-center">
	                                    We consider the Frittt Templates team a development partner who has proven to be
	                                    creative in problem resolution, reliable in time commitments, and overall consistent
	                                    in meeting our expectations.
	                                </h3>
	                            </div>
	                        </div>
	                        
	                        <!--用户3号-->
	                        <div class="item">
	                            <div class="col-md-6   col-md-offset-3 text-center">
	                                <div class="testimonial-thumb">
	                                    <img src="${pageContext.request.contextPath }/images/testimonial_img3.png" alt="testimonal">
	                                </div>
	                                <div class="testimonial-user">
	                                    <span>张同学</span> 中国 深圳
	                                </div>
	                                <h3 class="semi-bold text-center">
	                                    The work was above and beyond what I could have expected. Excellent service all
	                                    the way around from start to finish. Keep up the GREAT work! Great job!
	                                </h3>
	                            </div>
	                        </div>
	                        
	                    </div>
	                </div>
	            </div>
	        </div>
        
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
        
	        <!-- footer快 -->
	        <div class="section white footer">
	            <div class="container">
	                <div class="p-t-30 p-b-50">
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
    	
	    <!-- bootstrap js支持 -->
	    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
	    <!-- 动画支持 -->
	    <script type="text/javascript" src="<%=basePath%>plugins/pace/pace.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>plugins/jquery-unveil/jquery.unveil.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>plugins/owl-carousel/owl.carousel.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>plugins/waypoints.min.js"></script>
	    <!-- <script type="text/javascript" src="plugins/parrallax/js/jquery.parallax-1.1.3.js"></script> -->
	    <script type="text/javascript" src="<%=basePath%>plugins/jquery-nicescroll/jquery.nicescroll.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>plugins/jquery-appear/jquery.appear.js"></script>
	    <script type="text/javascript" src="<%=basePath%>plugins/jquery-numberAnimate/jquery.animateNumbers.js"></script>
	    <script type="text/javascript" src="<%=basePath%>js/core.js"></script>
	    <!-- wow页面滚动特效（大类item） -->
	    <script type="text/javascript" src="<%=basePath%>js/wow.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>js/site.js"></script>
	    <!-- 科技网络化背景特效（因性能问题暂时注释） -->
	    <!-- <script type="text/javascript" src="js/canvas-nest.min.js"></script> -->             		
	</body>
</html>
