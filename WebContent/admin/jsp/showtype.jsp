<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<h4>商品类别显示</h4>
<a href="${pageContext.request.contextPath }/admin/homeaddtype.jsp">添加类别</a><br><br>
	<table border="1">
		<tr>
			<td>编号</td>
			<td>名称</td>
			<td>描述</td>
			<td>操作</td>
		</tr> 
		<c:forEach var="ptype" items="${sessionScope.typelist }" varStatus="status">
			<tr>
				<td>${ptype.id }</td>
				<td>${ptype.tname }</td>
				<td>${ptype.descp }</td>
				<td><a href="${pageContext.request.contextPath }/updatetype?id=${ptype.id }">修改</a> &nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/deletetype?id=${ptype.id }">删除</a>
				</td>
			</tr>
		</c:forEach>	
	</table>
</div>
