<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region List</title>
<link rel="stylesheet" type="text/css" href="css/region.css">
</head>
<body>
	<!-- 헤더 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>

	<div class="container">

		<!-- 관리자 글쓰기 버튼 -->
		<c:if test="${sessionScope.user != null}">
			<c:set var="user" value="${sessionScope.user}" />
			<c:if test="${user.grade == 'admin'}">
				<div class="admin-buttons">
					<a href="./RegionWriteView.do">
						<button>글 쓰기</button>
					</a> <a href="./admin.jsp">
						<button>관리자 페이지</button>
					</a>
				</div>
			</c:if>
		</c:if>

		<!-- 이미지 리스트 -->
		<div class="image-container">
			<c:forEach var="region" items="${regionList}">
				<div class="image-item">
					<div class="title">${region.title}</div>
					<a href="./regionDetail.do?regionNumber=${region.regionNumber}">
						<c:choose>
							<c:when test="${not empty region.imageUrl}">
								<img src="data:image/png;base64,${region.imageUrl}" alt="지역 이미지" />
							</c:when>
							<c:otherwise>
								<img src="sub.png" alt="기본 이미지" />
							</c:otherwise>
						</c:choose>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- 페이징 처리 -->
	<div class="footer-container">
		<div class="pagination">
			<c:if test="${currentPage == 1 }">
				<a href="./region.do?page=${currentPage}">prev</a>
			</c:if>
			<c:if test="${currentPage != 1 }">
				<a href="./region.do?page=${currentPage - 1 }">prev</a>
			</c:if>
			<c:forEach var="i" begin="1" end="${totalPage}">
				<c:if test="${i == currentPage}">
					<span class="current-page">${i}</span>
				</c:if>
				<c:if test="${i != currentPage}">
					<a href="./region.do?page=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<c:if test="${currentPage == totalPage}">
				<a href="./region.do?page=${currentPage}">next</a>
			</c:if>
			<c:if test="${currentPage < totalPage}">
				<a href="./region.do?page=${currentPage + 1}">next</a>
			</c:if>
		</div>
	</div>
	<jsp:include page="./views/footer.jsp"></jsp:include>
</body>

</html>
