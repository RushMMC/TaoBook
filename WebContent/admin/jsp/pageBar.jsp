<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cp"%>
<style>
#selectPage {
	text-align: center;
}

.pageLabel {
	display: inline-block;
	min-width: 30px;
	padding: 3px;
	height: 30px;
	color:#000;
	background-color: white;
	border: 1px solid blue;
	text-align: center;
	cursor: pointer;
}
</style>
<div id="selectPage">
	<cp:if test="${currentPage>1 }">
		<label class="pageLabel" onClick="search(${currentPage-1})">上一页</label>
	</cp:if>
	<cp:forEach var="i" items="${pageList}">
		<cp:choose>
			<cp:when test="${i==currentPage }">
				<label class="pageLabel" onclick="search(${currentPage})"
					style="color: red">${currentPage }</label>
			</cp:when>
			<cp:otherwise>
				<cp:if test="${i>=1 and i<=pageTotal}">
					<label class="pageLabel" onclick="search(${i})">${i}</label>
				</cp:if>
			</cp:otherwise>
		</cp:choose>
	</cp:forEach>
	<cp:if test="${currentPage+3<=pageTotal }">
		<label class="pageLabel">...</label>
	</cp:if>
	<cp:if test="${currentPage<pageTotal }">
		<label class="pageLabel" onclick="search(${currentPage+1})">下一页</label>
	</cp:if>
</div>
<script type="text/javascript">
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
	location.href="${queryUrl}?"+param;
}
</script>