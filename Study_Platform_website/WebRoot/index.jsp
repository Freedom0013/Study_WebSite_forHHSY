<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="utf-8">
        <title>学习平台</title>
        <link rel="stylesheet" href="css/test.css" type="text/css" />
        <link type="text/css" href="css/css.css" rel="stylesheet">
		<script type="text/javascript" src="js/JQ.js"></script>
		<script type="text/javascript" src="js/zzsc.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/jquery.XYTipsWindow.min.2.8.js"></script>
		<link href="css/box_style.css" type="text/css" rel="stylesheet" />
    </head>

	<body>
		<div id="Top">
			<br> <img src="images/logo.png" width="315" height="60" />
			<div id="center">
				<div class="nav">
					<ul>
						<li class="cur"><a href="index.html">首页</a></li>
						<li><a href="zhuanye/zhuanye1.html" target="_blank">专业</a></li>
						<li><a href="资源.html" target="_blank">资源</a></li>
						<li><a href="推荐书籍.html" target="_blank">推荐书籍</a></li>
						<li><a href="#" target="_blank">移动课堂</a></li>
					</ul>
					<div class="curBg"></div>
					<div class="cls"></div>
				</div>
			</div>
			<div id="Top-login">
				<a href="Login.jsp" target="_blank">登录</a>
				&nbsp; 
				<a href="Login.jsp#toregister" target="_blank">注册</a>
			</div>
		</div>
		<div class="banner">
			<ul class="list">
				<li style="display: list-item;" class="bg2"><a href="#"
					target="_blank"> <img src="images/bannerImg2.jpg" alt=""
						height="408" width="980">
						<p class="text1">
							<img src="images/banner2-1.png" alt="" height="511" width="543">
						</p>
						<p class="text2">
							<img src="images/banner2-2.png" alt="" height="511" width="543">
						</p>
						<p class="text3">
							<img src="images/banner2-3.png" alt="" height="164" width="401">
						</p>
						<p class="text4">
							<img src="images/banner2-4.png" alt="" height="72" width="144">
						</p>
						<p class="text5">
							<img src="images/banner2-5.png" alt="" height="66" width="378">
						</p>
						<p class="text6">
							<img src="images/banner2-6.png" alt="" height="33" width="321">
						</p>
						<p class="text7">
							<img src="images/banner2-7.png" alt="" width="390">
						</p>
				</a></li>
				<li style="display: none;" class="bg4"><a href="#"
					target="_blank"> <img src="images/bannerImg4.jpg" alt=""
						height="408" width="980">
						<p class="text1">我想</p>
						<p class="text2">
							<img src="images/banner4-1.png" alt="" height="18" width="66">
						</p>
						<p class="text3">
							<img src="images/banner4-4.png" alt="" height="94" width="167">
						</p>
						<p class="text4">
							<img src="images/banner4-6.png" alt="" height="106" width="432">
						</p>
						<p class="text5">
							<img src="images/banner4-5.png" alt="" height="57" width="237">
						</p>
						<p class="text6">
							<img src="images/banner4-2.png" alt="" height="62" width="304">
						</p>
						<p class="text7">
							<img src="images/banner4-3.png" alt="" height="240" width="197">
						</p>
				</a></li>
				<li style="display: none;" class="bg3"><a href="#"
					target="_blank">
						<p class="text1">
							<img src="images/banner3-1.png" alt="" height="59" width="980">
						</p>
						<p class="text2-1">
							<img src="images/banner3-2-1.png" alt="" height="277" width="359">
						</p>
						<p class="text2-2">
							<img src="images/banner3-2-2.png" alt="" height="408" width="437">
						</p>
						<p class="text3">
							<img src="images/banner3-3.png" alt="" height="408" width="871">
						</p>
						<p class="text4">选择博知，选择品质</p>
						<p class="text5">
							<img src="images/banner3-4.png" alt="" height="44" width="782">
						</p>
						<p class="text6">丰富的课程资源</p>
						<p class="text7">及时的专业解答</p>
						<p class="text8">自由的交流体验</p>
				</a></li>
				<li style="display: none;" class="bg1"><a href="#"
					target="_blank"> <img src="images/1383531804.jpg" alt=""
						height="408" width="980">
						<p class="text1 hover">
							<img src="images/1383539870.png" alt="" height="228" width="219">
						</p>
						<p class="text2 hover">
							<img src="images/1383535692.png" alt="" height="53" width="423">
						</p>
						<p class="text3 hover">
							<img src="images/1383533951.png" alt="" height="25" width="286">
						</p>
				</a></li>
			</ul>
			<ul class="btn"></ul>
		</div>
		<script type="text/javascript" src="js/focus.js"></script>
	
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
			<div id="box1">
				<div class="box">
					<a href="zhuanye/zhuanye2.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye2.html" target="_blank">水利工程学院</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye3.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye3.html" target="_blank">土木与交通工程学院</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye10.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye10.html" target="_blank">测绘工程学院</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye11.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye11.html" target="_blank">机械工程学院</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye7.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye7.html" target="_blank">国际教育学院</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye9.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye9.html" target="_blank">环境与化学工程系</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye1.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye1.html" target="_blank">信息工程系</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye8.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye8.html" target="_blank">自动化工程系</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye5.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye5.html" target="_blank">财经系</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye6.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye6.html" target="_blank">管理系</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye12.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye12.html" target="_blank">旅游系</a>
					</h2>
				</div>
				<div class="box">
					<a href="zhuanye/zhuanye4.html" target="_blank"><img
						src="images/box1.png" width="291" height="179"></a>
					<h2>
						<a href="zhuanye/zhuanye4.html" target="_blank">艺术系 （艺术教育中心） </a>
					</h2>
				</div>
			</div>
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
