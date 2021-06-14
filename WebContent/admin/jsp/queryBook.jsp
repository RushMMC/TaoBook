<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询图书</title>
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
input,select,option{
	color:#000;
}
input[type="submit"]{
	width:80px;
	border:1px solid #74ebfb;
	background-color: rgb(255 255 255 / 12%);
	color:#74ebfb;
}
input,select{
	height:30px;
	box-sizing: border-box;
	outline:0px;
}
</style>
</head>
<body>
	<div id="searchPanel">
		<form>
			<input type="text" name="title" placeholder="书名"/>
			<input type="text" name="author" placeholder="作者"/>
			<input type="text" name="isbn" placeholder="ISBN编号"/>
			<select id="bookselect" name="type" onclick="loadSelect()">
				<option value="">---请选择---</option>
			</select>
			<input type="submit" value="搜索"/>
		</form>
	</div>
	<script type="text/javascript">
		var select=document.getElementById("bookselect");
		var xmlHttp;
		var data=null;
		function loadSelect(){
			if(data==null){
				xmlHttp=createXMLHttpRequest();
				xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4){
						if(xmlHttp.status==200){
							data = JSON.parse(xmlHttp.responseText);
							for (var type of data) {
								console.log(type);
								select.options.add(new Option(type.typename,type.id));
							}
						}
					}
				};
				xmlHttp.open("GET","${pageContext.request.contextPath}/admin/queryBooktype.ado",true);
				xmlHttp.send(null);
			}
		}
	</script>
	<div id="showPanel">
		<table>
			<tr>
				<th>编号</th>
				<th>书名</th>
				<th>作者</th>
				<th>ISBN</th>
				<th>价格</th>
				<th>库存</th>
				<th>状态</th>
				<th>修改</th>
				<th>下架</th>
			</tr>
			<c:forEach var="book" items="${queryBooks }">
				<tr>
					<td>${book.id }</td>
					<td>${book.title }</td>
					<td>${book.author }</td>
					<td>${book.isbn }</td>
					<td>${book.price }</td>
					<td>${book.quantity }</td>
					<td>${book.isSell ?'在售' : '下架' }</td>
					<td><a href="${pageContext.request.contextPath }/admin/updateBook.ado?id=${book.id}">修改</a></td>
					<td><a href="${pageContext.request.contextPath }/admin/downBook.ado?id=${book.id}">下架</a></td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pageBar.jsp" %>
	</div>
</body>
</html>