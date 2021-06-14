<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理页面</title>
<style type="text/css">
#container {
	width: 1200px;
	margin: auto;
}

header {
	width: 100%;
	height: 100px;
}

nav {
	float: left;
	font-size: 20px;
	color: #ffffff;
	width: 20%;
	height: 600px;
	box-sizing: border-box;
	padding: 25px 0px 0px 10px;
	/* background-image: linear-gradient(rgb(43 117 165/ 90%),
		rgb(43 148 165/ 65%)); */
	background-color: rgb(253 253 253 / 13%);
}

a {
	text-decoration: none;
}

article {
	float: left;
	width: 80%;
	height: 600px;
}

footer {
	clear: both;
	width: 600px;
	height: 100px;
}

iframe {
	width: 100%;
	height: 100%;
	border:none;
}

video {
	width:100%;
	top:0px;
	position:absolute;
	height:100%;
	object-fit: fill;
    min-height: 800px;
    z-index: -999;
}
body{
	margin:0px
}
*{
	color:white;
	
}
</style>
</head>
<body>
	<video src="${pageContext.request.contextPath }/video/科技背景视屏.mp4" autoplay="autoplay" muted="muted" loop="muted"></video>
	<div id="container">
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<nav>
		<%@ include file="left.jsp"%>
	</nav>
	<article>
		<iframe name="panel"></iframe>
	</article>
	<%@ include file="footer.jsp"%>
	</div>
</body>
</html>
