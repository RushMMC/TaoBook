<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘书网站</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css"/>
<script src="${pageContext.request.contextPath }/js/index.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div id="container">
			<header>
				<%@ include file="header.jsp" %>
				<!-- 导航栏展示区 -->
				<div id="showPanel">
					<img class="showImg" src="${pageContext.request.contextPath }/img/banner/1.jpg" >
					<img class="showImg" src="${pageContext.request.contextPath }/img/banner/2.jpg" >
					<img class="showImg" src="${pageContext.request.contextPath }/img/banner/3.jpg" >
					<img class="showImg" src="${pageContext.request.contextPath }/img/banner/4.jpg" >
					<img class="showImg" src="${pageContext.request.contextPath }/img/banner/5.jpg" >
					<img class="showImg" src="${pageContext.request.contextPath }/img/banner/6.jpg" >
					<ul>
						<li class="imgTitle">618开门红</li>
						<li class="imgTitle">儿童节礼物季</li>
						<li class="imgTitle">年中庆开门红</li>
						<li class="imgTitle">满20减10元</li>
						<li class="imgTitle">新书抢先看</li>
						<li class="imgTitle">童书五折封顶</li>
					</ul>
				</div>
				<nav>
				<div class="navPanel">
					<div class="navPanel-col">
						<div class="navPanel-row">
							<div class="nav-item-name">
								<img class="nav-icon" src="${pageContext.request.contextPath }/img/icon/nav1.png" >
								<span>教育</span>
							</div><span class="nav-item-flag">|</span>
							<a href="${pageContext.request.contextPath }/search.do?type=2" class="nav-item-e">教材</a>
							<a href="${pageContext.request.contextPath }/search.do?type=3" class="nav-item-e">考试</a>
						</div>
						<div class="navPanel-row">
							<div class="nav-item-name">
								<img class="nav-icon" src="${pageContext.request.contextPath }/img/icon/nav2.png" >
								<span>文艺</span>
							</div><span class="nav-item-flag">|</span>
							<a href="${pageContext.request.contextPath }/search.do?type=1" class="nav-item-e">文学</a>
							<a href="${pageContext.request.contextPath }/search.do?type=4" class="nav-item-e">艺术</a>
						</div>
					</div>
					<div class="navPanel-col">
						<div class="navPanel-row">
							<div class="nav-item-name">
								<img class="nav-icon" src="${pageContext.request.contextPath }/img/icon/nav3.png" >
								<span>童书</span>
							</div><span class="nav-item-flag">|</span>
							<a href="${pageContext.request.contextPath }/search.do?type=5" class="nav-item-e">科普</a>
							<a href="${pageContext.request.contextPath }/search.do?type=6" class="nav-item-e">绘本</a>
						</div>
						<div class="navPanel-row">
							<div class="nav-item-name">
								<img class="nav-icon" src="${pageContext.request.contextPath }/img/icon/nav4.png" >
								<span>经管</span>
							</div><span class="nav-item-flag">|</span>
							<a href="${pageContext.request.contextPath }/search.do?type=8" class="nav-item-e">经济</a>
							<a href="${pageContext.request.contextPath }/search.do?type=7" class="nav-item-e">管理</a>
						</div>
					</div>
					<div class="navPanel-col">
						<div class="navPanel-row">
							<div class="nav-item-name">
								<img class="nav-icon" src="${pageContext.request.contextPath }/img/icon/nav5.png" >
								<span>生活</span>
							</div><span class="nav-item-flag">|</span>
							<a href="${pageContext.request.contextPath }/search.do?type=9" class="nav-item-e">运动</a>
							<a href="${pageContext.request.contextPath }/search.do?type=10" class="nav-item-e">旅游</a>
						</div>
						<div class="navPanel-row">
							<div class="nav-item-name">
								<img class="nav-icon" src="${pageContext.request.contextPath }/img/icon/nav6.png" >
								<span>科技</span>
							</div><span class="nav-item-flag">|</span>
							<a href="${pageContext.request.contextPath }/search.do?type=11" class="nav-item-e">计算机</a>
							<a href="${pageContext.request.contextPath }/search.do?type=12" class="nav-item-e">自然科学</a>
						</div>
					</div>
					<div class="navPanel-col-more">
						<div><a href="${pageContext.request.contextPath }/search.do?type=13">历史</a><a href="${pageContext.request.contextPath }/search.do?type=18">休闲</a></div>
						<div><a href="${pageContext.request.contextPath }/search.do?type=14">古籍</a><a href="${pageContext.request.contextPath }/search.do?type=19">手工</a></div>
						<div><a href="${pageContext.request.contextPath }/search.do?type=15">哲学</a><a href="${pageContext.request.contextPath }/search.do?type=20">美食</a></div>
						<div><a href="${pageContext.request.contextPath }/search.do?type=16">法律</a><a href="${pageContext.request.contextPath }/search.do?type=21">家具</a></div>
						<div><a href="${pageContext.request.contextPath }/search.do?type=17">育儿</a><a href="${pageContext.request.contextPath }/search.do">更多</a></div>
					</div>
				</div>
			</nav>
			</header>
			<div id="content">
				<div class="panel">
					<div class="leftSubPanel">
						<h1><a href="#">新书上架</a></h1>
						<ul>
						<c:forEach var="book" items="${newBooks }">
							<li>
								<a href="${pageContext.request.contextPath }/detailPage.do?id=${book.id }">
									<div class="card">
										<img src="${pageContext.request.contextPath }/${book.imgPath }" />
									</div>
									<p class="cardName">${book.title }</p>
									<span class="cardDescribe">${book.author }</span>
								</a>
							</li>
						</c:forEach>
						</ul>
					</div>
					<div class="rightSubPanel">
						<h1>新书热卖榜</h1>
						<div class="rank-bg"></div>
						<ul class="rank">
						<c:forEach var="book" items="${newBooksRank }" varStatus="status">
							<li>
								<a href="${pageContext.request.contextPath }/detailPage.do?id=${book.id }">
									<c:choose>
										<c:when test="${status.count<=3 }">
											<span class="rank-num-${status.count }">${status.count }</span>
										</c:when>
										<c:otherwise>
											<span class="rank-num">${status.count }</span>
										</c:otherwise>
									</c:choose>
									<span class="rank-title">${book.title }</span>
									<span class="rank-desc">${book.author }</span>
								</a>
							</li>
						</c:forEach>
						</ul>
					</div>
				</div>
				<div class="panel">
					<div class="leftSubPanel">
						<h1><a href="#">图书</a></h1>
						<ul>
						<c:forEach var="book" items="${books }">
							<li>
								<a href="${pageContext.request.contextPath }/detailPage.do?id=${book.id }">
									<div class="card">
										<img src="${pageContext.request.contextPath }/${book.imgPath }" />
									</div>
									<p class="cardName">${book.title }</p>
									<span class="cardDescribe">${book.author }</span>
								</a>
							</li>
						</c:forEach>
						</ul>
					</div>
					<div class="rightSubPanel">
						<h1>图书畅销榜</h1>
						<div class="rank-bg"></div>
						<ul class="rank">
							<c:forEach var="book" items="${booksRank }" varStatus="status">
								<li>
									<a href="${pageContext.request.contextPath }/detailPage.do?id=${book.id }">
										<c:choose>
											<c:when test="${status.count<=3 }">
												<span class="rank-num-${status.count }">${status.count }</span>
											</c:when>
											<c:otherwise>
												<span class="rank-num">${status.count }</span>
											</c:otherwise>
										</c:choose>
										<span class="rank-title">${book.title }</span>
										<span class="rank-desc">${book.author }</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
</body>
</html>