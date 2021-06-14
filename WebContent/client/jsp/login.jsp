<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录界面</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css"/>
		<script src="${pageContext.request.contextPath }/js/login.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div id="container">
			<form action="${pageContext.request.contextPath }/login" method="post">
				<h2>登录淘书网站</h2>
				<hr >
				<div class="form-row">
					<input type="text" name="username" placeholder="用户名" autocomplete/>
				</div>
				<div class="form-row">
					<input type="password" name="password" placeholder="密码" autocomplete/>
				</div>
				<div id="errorTip">
					${message }
				</div>
				<div id="autoLogin">
					<input type="checkbox" name="autologin" value="true" checked="checked"/>
					<span>自动登录</span>
					<a href="${pageContext.request.contextPath }/forgetPassword">忘记密码</a>
				</div>
				<div class="form-row">
				</div>
				<input type="submit" value="立即登录"/>
			</form>
		</div>
	</body>
</html>