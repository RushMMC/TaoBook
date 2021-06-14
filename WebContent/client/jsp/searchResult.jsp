<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/lib.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxRequest.js"></script>
<%-- <script src="${pageContext.request.contextPath }/js/dynamicLoadCard.js" type="text/javascript" charset="utf-8"></script> --%>
</head>
<style>
#selectPage{
	text-align: center;
}
.pageLabel{
    display: inline-block;
    min-width: 30px;
    padding: 3px;
    height: 30px;
    background-color: white;
    border: 1px solid blue;
    text-align: center;
    cursor: pointer;
}
ul{
	display: flow-root;
}
</style>
<body>
	<div id="container">
		<%@ include file="header.jsp"%>
		<nav>
		<div class="nav-row">
			<div class="nav-row-title">排序</div>
			<ul class="nav-row-detail">
				<li>最热</li>
				<li>好评</li>
				<li>知乎高分</li>
			</ul>
		</div>
		<div class="nav-row">
			<div class="nav-row-title">分类</div>
			<ul class="nav-row-detail">
				<li>全部</li>
				<c:forEach var="type" items="${bookTypes }">
					<li><a href="${pageContext.request.contextPath }/search.do?type=${type.id}">${type.typename }</a></li>
				</c:forEach>
			</ul>
		</div>
		</nav>
		<div id="content">
			<ul>
				<c:forEach var="book" items="${searchBooks }">
					<li class="card">
							<a href="${pageContext.request.contextPath }/detailPage.do?id=${book.id }">
								<div class="imgArea">
									<img src="${pageContext.request.contextPath }/${book.imgPath }">
								</div> 
								<span class="card-name">${book.title }</span>
								<span class="card-desc">${book.author }</span>
							</a>
					</li>
				</c:forEach>
			</ul>
			<div id="selectPage">
				<c:if test="${currentPage>1 }">
					<label class="pageLabel" onClick="search(${currentPage-1})">上一页</label>
				</c:if>
				<c:forEach var="i" items="${pageList}">
					<c:choose>
						<c:when test="${i==currentPage }">
							<label class="pageLabel" onclick="search(${currentPage})" style="color:red">${currentPage }</label>
						</c:when>
						<c:otherwise>
							<c:if test="${i>=1 and i<=pageTotal}">
								<label class="pageLabel" onclick="search(${i})">${i}</label>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage+3<=pageTotal }">
					<label class="pageLabel">...</label>					
				</c:if>
				<c:if test="${currentPage<pageTotal }">
				<label class="pageLabel" onclick="search(${currentPage+1})">下一页</label>
				</c:if>
			</div>
		</div>
		<br>
		<%@ include file="footer.jsp"%>
	</div>
	<script type="text/javascript">
		var quantity = document.getElementById("quantity");
		var tip = document.getElementById("tip");
		var xmlHttp;
		function search(page){
			var data={
				title:'${param.title}',
				author:'${param.author}',
				price:'${param.price}',
				type:'${param.type}',
				createDate:'${param.createDate}',
				updateDate:'${param.updateDate}',
				start:page,
				offset:'${offset}'
			};
			var param='';
			var isFirst=true;
			for(var k in data){
				if(data[k]!=''){
					if(isFirst){
						isFirst=false;
					}else{
						param+='&';
					}
					param+=k+'='+data[k];
				}
			}
			location.href="${pageContext.request.contextPath}/search.do?"+param;
		}
	</script>
</body>
</html>