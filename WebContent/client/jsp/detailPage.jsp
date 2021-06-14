<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.title }</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
<style>
#content {
	width: 1200px;
	height: 400px;
}

#left {
	width: 300px;
	height: 350px;
	float: left;
	margin: 10px 10px 10px 50px;
}

#left img {
	width: 80%;
	height: 80%;
}

#right {
	float: left;
	margin: 10px 0px 0px 10px;
}
tr{
	margin:10px 10px;
}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="header.jsp"%>
		<div id="content">
			<div id="left">
				<img alt="${book.title }"
					src="${pageContext.request.contextPath }/${book.imgPath }" />
			</div>
			<div id="right">
				<table>
					<tr>
						<td>书名：</td>
						<td>${book.title }</td>
					</tr>
					<tr>
						<td>作者：</td>
						<td>${book.author }</td>
					</tr>
					<tr>
						<td>ISBN编号：</td>
						<td>${book.isbn }</td>
					</tr>
					<tr>
						<td>价格：</td>
						<td>${book.price }元</td>
					</tr>
					<tr>
						<td>库存：</td>
						<td>${book.quantity }</td>
					</tr>
					<tr>
						<td>购买数量：</td>
						<td><input id="quantity" type="number" value="1" /></td>
					</tr>
				</table>
				<button onclick="addToCar()">加入购物车</button>
				<div id="tip" hidden="hidden"></div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	<script type="text/javascript">
		var quantity = document.getElementById("quantity");
		var tip = document.getElementById("tip");
		var xmlHttp;
		function addToCar(){
			xmlHttp=createXMLHttpRequest();
			xmlHttp.onreadystatechange=handleStateChange;
			xmlHttp.open("POST","${pageContext.request.contextPath}/addToCart.do?id=${book.id}&quantity="+quantity.value,true);
			xmlHttp.send(null);
		}

		function handleStateChange(){
			if(xmlHttp.readyState==4){
				if(xmlHttp.status==200){
					tip.innerText=xmlHttp.responseText;
					tip.hidden=false;
					tip.style.color='black';
					for(let i=10;i>0;i--){
						console.log(i*0.1);
						setTimeout(function(){
							tip.style.color='rgba(0,0,0,'+i*0.1+')';
						},100*(10-i));
					}
				}
			}
		}
	</script>
</body>
</html>