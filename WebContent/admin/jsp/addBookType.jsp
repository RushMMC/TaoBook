<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
*{
	color:#fff;
}
input{
	height:30px;
	outline:0px;
}
input,select,option{
	color:#000;
}
td:nth-child(1){
	text-align: right;
}
table{
	margin: 30px auto;
}
input[type="submit"]{
	width:80px;
	border:1px solid #74ebfb;
	background-color: rgb(255 255 255 / 12%);
	color:#74ebfb;
}
</style>
<div>
<h4>添加图书类别</h4>
${message }
<hr>
	<form action="${pageContext.request.contextPath }/admin/addBookType.ado" method="post">
		<table>
			<tr>
				<td>类别名称：</td>
				<td><input type="text" name="typename"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="添加"/>				
				</td>
			</tr>
		</table>
	</form>
</div>
