<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#firstLine{
	width: 1200px;
	height: 60px;
	background-color: rgba(224, 237, 211, 0.3);
}
#firstLine>div{
	height: 60px;
	display:flex;
	margin:0px 50px;
	justify-content: space-between;
	align-items: center;
	z-index: 100;
}
#logo{
	font-size: 30px;
	font-size: 50px;
	font-family: "华文行楷";
	margin:5px 0px 0px 5px;
	display: inline-block;
}
#search{
	width: 300px;
	height: 40px;
	line-height: 35px;
	display: inline-block;
}
#search-keyword{
	float: left;
	display: inline-block;
	height: 40px;
	width: 200px;
	background-color: rgba(0,0,0,0.3);
	border-radius: 20px 0px 0px 20px;
}
#keyword{
	color:#fff;
	width: 180px;
	height: 38px;
	margin-left: 20px;
	border-width: 0px;
	outline: 0px;
	background: 0px 0px;
	border-radius: 25%;
	display: inline-block;
	background-color: rgba(0,0,0,0.0);
}
/*搜索输入设置提示文本*/
input::-webkit-input-placeholder {
    color: #ffffff;
}
#searchBtn{
	width: 60px;
	height: 40px;
	display: inline-block;
	color: green;
	font-size: 18px;
	border: none;
	font-weight: bold;
	background-color: #c1eda3;
	border-radius: 0px 20px 20px 0px;
	outline: 0px;
	cursor: pointer;
}
#searchBtn:hover{
	background-color: #96cc72;
}
#loginAndRegister{
	display: inline-block;
	font-size: 25px;
}
#loginAndRegister a{
	color: black;
}
</Style>
<div id="firstLine">
	<div>
		<a href="${pageContext.request.contextPath }/client/jsp/index.jsp"><span id="logo">淘书</span></a>
		<form action="${pageContext.request.contextPath }/search.do" method="post"
			id="search">
			<div id="search-keyword">
				<input type="text" id="keyword" name="title" placeholder="输入书名"
					autocomplete="off">
			</div>
			<input type="submit" id="searchBtn" value="搜索" />
		</form>
		<c:choose>
			<c:when test="${username==null }">
				<div id="loginAndRegister">
					<a href="${pageContext.request.contextPath }/client/jsp/login.jsp">登录</a>|
					<a href="${pageContext.request.contextPath }/client/jsp/register.jsp">注册</a>
				</div>
			</c:when>
			<c:otherwise>
				<div id="loginAndRegister">
					<span>${username }！</span> <a
						href="${pageContext.request.contextPath }/showShoppingCart.do">购物车</a>
					<a href="${pageContext.request.contextPath }/logout.do">注销</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>