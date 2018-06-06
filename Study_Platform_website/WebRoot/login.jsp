<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<!-- IE8兼容性设置 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<!-- 移动设备兼容 -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- 设置jsp地址栏小图标 -->
		<link rel="icon" href="<%=basePath%>images/logo_icon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="<%=basePath%>images/logo_icon.ico" type="image/x-icon">
		<title>用户登录</title>

		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login_styles.css">
	</head>
  
	<body>
		<!-- <div style="height:30px;"/> -->
		<div class="login-wrap">
			<div class="login-html">

				<input id="tab-1" type="radio" name="tab" class="sign-in" checked /><label for="tab-1" class="tab">登录</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up" /><label for="tab-2" class="tab">注册</label>

				<div class="login-form">

					<!-- 用户登录 -->
					<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
						<div class="sign-in-htm">
							<div class="group">
								<label for="user" class="label">用户名</label>
								<input id="user" type="text" class="input" />
							</div>
							<div class="group">
								<label for="pass" class="label">密码</label>
								<input id="pass" type="password" class="input" data-type="password" />
							</div>
							<div class="group">
								<input id="check" type="checkbox" class="check" checked />
								<label for="check"><span class="icon"></span> 记住密码</label>
							</div>
							<div class="group">
								<input type="submit" class="button" value="登录" />
							</div>
							<div class="hr"></div>
							<div class="foot-lnk">
								<a href="#">忘记密码?</a>
							</div>	
						</div>
					</form>	

					<!-- 用户注册 -->
					<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post"> 
						<div class="sign-up-htm">
							<div class="group">
								<label for="user" class="label">用户名</label>
								<input id="user" type="text" class="input" />
							</div>

							<div class="group">
								<label for="pass" class="label">密码</label>
								<input id="pass" type="password" class="input" data-type="password" />
							</div>

							<div class="group">
								<label for="pass" class="label">确认密码</label>
								<input id="sec_regpassword" name="sec_regpassword" type="password" class="input" data-type="password" />
							</div>

							<div class="group">
								<label for="pass" class="label">昵称</label>
								<input id="nickname" name="nickname" type="text" class="input" />
							</div>

							<div class="group">
								<input type="submit" class="button" value="注册" />
							</div>
							<!-- 分割线 -->
							<div class="hr"></div>
							<div class="foot-lnk">
								<label for="tab-1">已注册登录?</label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
