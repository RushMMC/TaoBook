<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询订单</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
<style>
#searchPanel{
	display:flex;
}
*{
	color:white;
}
table{
    width: 100%;
    margin: 10px auto;
    border: 1px solid #ccc;
    border-collapse: collapse;
    background-color: rgb(253 253 253 / 13%);
}

th,td{
    text-align: center;
    padding: 2px 0;
}
th{
	color:#74ebfb;
}

table tr{
    text-align:center;
    border-bottom: 2px solid #74ebfb;
}

table tr:nth-child(odd){
    background-color: rgb(253 253 253 / 13%);
}

table tr:hover{
    background-color: rgb(253 253 253 / 53%);
}
table tr th{
	text-align: center;
}

table tr td:nth-child(1){
    text-align:center;
    width: 20px;
}

table tr td:nth-child(2){
    text-align:center;
    width:200px;
	margin-left: 5px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	max-height: 40px;
	min-height:40px;
}

table tr td{
    text-align: center;
    padding-right: 20px;
}

input[type="submit"]{
	width:80px;
	border:1px solid #74ebfb;
	background-color: rgb(255 255 255 / 12%);
	color:#74ebfb;
}
input{
	color:black;
	height:30px;
	box-sizing: border-box;
	outline:0px;
}
</style>
</head>
<body>
<h4>订单查询</h4>
<hr>
	<div id="searchPanel">
		<form action="${pageContext.request.contextPath }/admin/queryOrder.ado" method="post">
			<input type="text" name="userId" placeholder="用户ID"/>
			<input type="submit" value="搜索"/>
		</form>
	</div>

	<div id="showPanel">
		<table>
			<tr>
				<th>订单编号</th>
				<th>用户ID</th>
				<th>书本编号</th>
				<th>书本名</th>
				<th>总量</th>
				<th>是否支付</th>
				<th>删除</th>
			</tr>
			<c:forEach var="order" items="${orders }">
				<tr>
					<td>${order.id }</td>
					<td>${order.user.id }</td>
					<td>${order.book.id }</td>
					<td>${order.book.title}</td>
					<td>${order.quantity }</td>
					<td>${order.isPaid?'已支付':'未支付'}</td>
					<td><a href="${pageContext.request.contextPath }/admin/deleteOrder.ado?id=${order.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pageBar.jsp" %>
	</div>
</body>
</html>