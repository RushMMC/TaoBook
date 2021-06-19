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
input[type="file"]{
	color:white;
}
</style>
<div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
<h4>添加图书</h4>
	${message }
	<hr>
	<form action="${pageContext.request.contextPath }/admin/addBook.ado" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td>书名：</td>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>作者：</td>
				<td><input type="text" name="author"/></td>
			</tr>
			<tr>
				<td>ISBN编号：</td>
				<td><input type="text" name="isbn"/></td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><input type="number" name="price"/></td>
			</tr>
			<tr>
				<td>分类：</td>
				<td>
					<select id="bookselect" name="type" onclick="loadSelect()">
						<option>---请选择---</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>库存：</td>
				<td><input type="number" name="quantity"/></td>
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
								console.log(type);
								select.options.add(new Option(type.typename,type.id));
							}
						}
					}
				};
				xmlHttp.open("GET","${pageContext.request.contextPath}/admin/queryBookType.ado",true);
				xmlHttp.send(null);
			}
		}
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
