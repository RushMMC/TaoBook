<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
</head>
<body>
	<div id="container">
		<form action="${pageContext.request.contextPath}/register" method="post">
			<h2>欢迎注册拼书书网站账号</h2>
			<hr>
			<div class="form-row">
				<input id="nameInput" type="text" name="username" placeholder="用户名" onblur="validateName()" autocomplete/>
			</div>
			<div id="usernameTip" hidden>${registerMessage }</div>
			<div class="form-row">
				<input type="password" name="password" placeholder="密码" autocomplete/>
			</div>
			<div class="form-row">
				<input type="text" name="telephone" placeholder="手机号" autocomplete/>
			</div>
			<div class="form-row">
				<input type="checkbox" value="true" /> <span>我已阅读并同意相关服务条款和隐私政策</span>
				<img src="${pageContext.request.contextPath}/img/icon/down.png">
			</div>
			<input type="submit" value="立即注册" />
		</form>
	</div>
	<script type="text/javascript">
	var xmlHttp;
	var tip=document.getElementById('usernameTip');
	var nameInput = document.getElementById('nameInput');
	
	function validateName(){
		createXMLHttpRequest();
		xmlHttp.onreadystatechange=handleStateChange;
		xmlHttp.open("GET","${pageContext.request.contextPath}/validateUsername?username="+nameInput.value,true);
		xmlHttp.send(null);
	}
	
	nameInput.onFocus=function (){
		tip.hidden=true;
	}
	
	function handleStateChange(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				tip.innerText=xmlHttp.responseText;
				tip.hidden=false;
			}
		}
	}
</script>
</body>
</html>