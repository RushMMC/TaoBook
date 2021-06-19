<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
*{
	color:#fff;
}
input,select,option{
	height:30px;
	outline:0px;
	color:black;
}
input{
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
input[type="file"]{
	color:white;
}
img{
	width:200px;
	height:200px;
}
</style>
<div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
<h4>修改图书</h4>
	${message }
	<hr>
	<form action="${pageContext.request.contextPath }/admin/updateBook.ado" enctype="multipart/form-data" method="post">
		<img src="${pageContext.request.contextPath }/${book.imgPath }" align="left"/>
		<input type="text" name="id" value="${book.id}" hidden />
		<table>
			<tr>
				<td>书名：</td>
				<td><input type="text" value="${book.id}" disabled /></td>
			</tr>
			<tr>
				<td>书名：</td>
				<td><input type="text" name="title" value="${book.title }"/></td>
			</tr>
			<tr>
				<td>作者：</td>
				<td><input type="text" name="author" value="${book.author }"/></td>
			</tr>
			<tr>
				<td>ISBN编号：</td>
				<td><input type="text" name="isbn" value="${book.isbn }"/></td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><input type="number" name="price" value="${book.price }"/></td>
			<tr>
				<td>分类：</td>
				<td>
					<select id="bookselect" name="type" onclick="loadSelect()" value=${book.type }>
						<option>---请选择---</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>库存：</td>
				<td><input type="number" name="quantity" value="${book.quantity }"/></td>
			</tr>
			<tr>
				<td>图片：</td>
				<td><input type="file" name="picture"/>
				图标大小要小于1Mb</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="添加" onclick="return check(this.form)"/></td>
			</tr>
		</table>
	</form>
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
								var option=new Option(type.typename,type.id);
								if(type.id==${book.type}){
									option.selected=true;
								}
								select.options.add(option);
							}
						}
					}
				};
				xmlHttp.open("GET","${pageContext.request.contextPath}/admin/queryBookType.ado",true);
				xmlHttp.send(null);
			}
		}
		loadSelect();
		function check(form) {
			console.log(form.type.value);
			if(form.type.value=='---请选择---') {
				alert("类型不能为空!");
				form.type.focus();
				return false;
			}
			return true;
		}
	</script>
</div>
