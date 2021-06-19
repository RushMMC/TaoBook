<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
*{
	color:white;
}
table{
    width: 70%;
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

table tr td:nth-child(2){
    text-align:center;
	margin-left: 5px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	max-height: 40px;
	min-height:40px;
}
</style>
<div>
<h4>类别显示</h4>
<hr>
<div id="showPanel">
		<table>
			<tr>
				<th>编号</th>
				<th>类别</th>
				<th>修改</th>
			</tr>
			<c:forEach var="type" items="${booktypes }">
				<tr>
					<td>${type.id }</td>
					<td>${type.typename }</td>
					<td><a href="${pageContext.request.contextPath }/admin/updateBookType.ado?id=${type.id}">修改</a></td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pageBar.jsp" %>
	</div>
</div>
