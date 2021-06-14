<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,pers.mmc.bookmarket.entity.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>用户购物车信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/cart.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
</head>
<style>
button{
	width: 100px;
    height: 30px;
    background-color: rgb(46 171 99 / 50%);
}
</style>
<body>
	<div id="container">
		<%@ include file="header.jsp"%>
		<table>
			<tr>
				<th style='width: 80px'>商品</th>
				<th style='width: 80px'>价格</th>
				<th style='width: 50px'>数量</th>
				<th style='width: 80px'>小计</th>
				<th style='width: 80px'>是否删除</th>
			</tr>
			<c:forEach var="item" items="${cart.getItems() }">
				<tr>
					<td>${item.book.title }</td>
					<td>${item.book.price }</td>
					<td>${item.quantity }</td>
					<td>
					<fmt:formatNumber value="${(item.book.price*item.quantity*100+0.5)/100.0 }"
						type="currency" currencySymbol="￥" maxFractionDigits="2" pattern="##.##"/>
					</td>
					<td><a href="${pageContext.request.contextPath }/deleteItem.do?id=${item.book.id }">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan='5' style='text-align: right'>
					<button id="clearStock">结算</button>
					<label>
						总计:${cart.getTotal() }
					</label>
				</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath }/jsp/index.jsp">返回继续购物</a>
		<%@ include file="footer.jsp" %>
	</div>
	<script type="text/javascript">
		var clearStock = document.getElementById("clearStock");
		var xmlHttp;
		clearStock.onclick=function(){
			var trs=document.getElementsByTagName("tr");
			console.log(trs);
			if(trs.length>2){
				xmlHttp=createXMLHttpRequest();
				xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4){
						if(xmlHttp.status==200){
							alert(xmlHttp.responseText);
							location.href="${pageContext.request.contextPath}/showShoppingCart.do";
						}
					}
				};
				xmlHttp.open("GET","${pageContext.request.contextPath}/clearShoppingCart.do",true);
				xmlHttp.send(null);
			}else{
				alert("购物车中暂时没有商品，快去购买吧");
			}
		}
	</script>
</body>
</html>
