<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	修改商品类别
	<form action="${pageContext.request.contextPath }/updatetype" method="post">
		<table border="1">
			<tr>
				<td>编号：</td>
				<td><input type="text" name="id" value="${requestScope.ptype.id }" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>名称：</td>
				<td><input type="text" name="tname" value="${requestScope.ptype.tname }"/></td>
			</tr>
			<tr>
				<td>描述：</td>
				<td><textarea row="3" cols="22" name="descp">${requestScope.ptype.descp }</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="修改"/> &nbsp;&nbsp;
				<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>
	</form>
</div>