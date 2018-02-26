<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
    <head lang="en" class="no-js">
	    <meta charset="UTF-8" />
	    <base href="<%=basePath%>">
	    <title>用户登录</title>
	    <link rel="stylesheet" type="text/css" href="css/demo.css" />
	    <link rel="stylesheet" type="text/css" href="css/style.css" />
	    <link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
    </head>
	
	<body>
		<div class="container">
		
			<section>
				<div id="logo"></div>
                
				<div id="container_demo">
					<a class="hiddenanchor" id="toregister"></a> 
					<a class="hiddenanchor" id="tologin"></a>
                    
					<div id="wrapper">
						<div id="login" class="animate form">
							<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
								<h1>登录</h1>
								<p>
									<label for="username" class="uname" data-icon="u">用户名：</label> 
									<input id="username" name="username" required type="text" placeholder="用户名" />
								</p>
								<p>
									<label for="password" class="youpasswd" data-icon="p">密码：</label>
									<input id="password" name="password" required type="password" placeholder="密码" />
								</p>
								<p class="keeplogin">
									<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
									<label for="loginkeeping">记住登录状态</label>
									<p><font color=red>${errormessage}</font></p>
								</p>
								<p class="login button">
									<input type="submit" value="登录" />
								</p>
								<p class="change_link">
									还未注册？ <a href="#toregister" class="to_register">点击注册</a>
								</p>
							</form>
						</div> 
		
						<div id="register" class="animate form">
							<form action="mysuperscript.php" autocomplete="on">
								<h1>注册</h1>
								<p>
									<label for="usernamesignup" class="uname" data-icon="u">你的账号</label>
									<input id="usernamesignup" name="usernamesignup" required type="text" placeholder="账号" />
								</p>
								<p>
									<label for="emailsignup" class="youmail" data-icon="e">你的邮箱</label> 
                                    <input id="emailsignup" name="emailsignup" required type="email" placeholder="邮箱" />
								</p>
								<p>
									<label for="passwordsignup" class="youpasswd" data-icon="p">你的密码</label>
									<input id="passwordsignup" name="passwordsignup" required
										type="password" placeholder="密码" />
								</p>
								<p>
									<label for="passwordsignup_confirm" class="youpasswd" data-icon="p">确认你的密码</label> 
                                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required type="password"
										placeholder="确认密码" />
								</p>
								<p class="signin button">
									<input type="submit" value="注册" />
								</p>
								<p class="change_link">
									已经注册? <a href="#tologin" class="to_register">点击登录</a>
								</p>
							</form>
						</div>
					</div>
                    
				</div>
			</section>
			
        </div>
    </body>
</html>
