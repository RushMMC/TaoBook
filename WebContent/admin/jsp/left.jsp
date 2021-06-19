<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
ul{
list-style-type: none;
}
</style>
<div>
	<details>
		<summary tabindex="3">用户管理</summary>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/admin/jsp/addAdmin.jsp" target="panel">添加管理员</a></li>
    	</ul>
	</details>
	<details>
		<summary tabindex="1">商品管理</summary>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/admin/jsp/addBook.jsp" target="panel" >添加商品</a></li>
    		<li><a href="${pageContext.request.contextPath }/admin/queryBook.ado" target="panel">查询商品</a></li>
    	</ul>
	</details>
	<details>
		<summary tabindex="2">类别管理</summary>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/admin/jsp/addBookType.jsp" target="panel" >添加类别</a></li>
    		<li><a href="${pageContext.request.contextPath }/admin/showBookType.ado" target="panel">查询类别</a></li>
    	</ul>
	</details>
	<details>
		<summary tabindex="2">订单管理</summary>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/admin/jsp/queryOrder.jsp" target="panel" >查询订单</a></li>
    	</ul>
	</details>
</div>