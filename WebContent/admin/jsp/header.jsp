<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#logo{
	font-size: 50px;
    font-family: "华文行楷";
    margin: 15px 0px 0px 15px;
    display: inline-block;
    float: left;
}
.header-right{
	text-align: right;
	/* background-image: linear-gradient(rgb(134 255 199),rgb(43 117 165 / 90%)); */
	background-color: rgb(253 253 253 / 13%);
    height: 100%;
}
#welcome{
	min-width: 200px;
    margin: 25px 15px 0px 0px;
    display: inline-block;
    font-size: 25px;
}
</style>
<div class="header-right">
	<span id="logo">淘书后台系统</span>
	<span id="welcome">
	<c:choose>
		<c:when test="${sessionScope.adname==null }">
			<a href="${pageContext.request.contextPath }/admin/jsp/login.jsp">登录</a>
		</c:when>
		<c:otherwise>
			欢迎您，${sessionScope.adname }! &nbsp;&nbsp;<a href="${pageContext.request.contextPath }/admin/logout.ado">注销</a>
		</c:otherwise>
	</c:choose>
	</span>
</div>