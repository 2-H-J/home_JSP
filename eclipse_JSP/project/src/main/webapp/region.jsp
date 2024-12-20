<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region List</title>
<style>
/* 전체 레이아웃 설정 */
body {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	background-color: #f9f9f9;
	font-family: Arial, sans-serif;
}

/* 컨테이너 중앙 정렬 */
.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}

/* 이미지 카드 레이아웃 */
.image-container {
	display: flex;
	flex-wrap: wrap;
	gap: 30px;
	justify-content: center;
}

.image-item {
	flex: 0 1 calc(23% - 20px); /* 4개씩 한 줄에 표시 */
	text-align: center;
	background: #fff;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	transition: transform 0.3s ease-in-out;
}

.image-item:hover {
	transform: scale(1.03);
}

.image-item img {
	width: 100%;
	height: 200px; /* 고정 높이 적용 */
	object-fit: cover; /* 비율 유지하면서 넘치는 부분 자름 */
	display: block;
}

.title {
	margin: 10px 0;
	font-size: 1rem;
	font-weight: bold;
	color: #333;
}

/* 페이징 버튼 스타일 */
.pagination {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 20px;
	gap: 10px;
}

.pagination a, .pagination .current-page {
	display: inline-block;
	padding: 8px 12px;
	border-radius: 4px;
	text-decoration: none;
	font-size: 0.9rem;
	font-weight: bold;
	color: #555;
	background-color: #fff;
	border: 1px solid #ddd;
	transition: background-color 0.2s, color 0.2s;
}

.pagination a:hover {
	background-color: #007bff;
	color: #fff;
	border-color: #007bff;
}

.pagination .current-page {
	background-color: #007bff;
	color: #fff;
	border: 1px solid #007bff;
}

.footer-container {
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	background-color: #f9f9f9;
	padding: 10px 0;
	text-align: center;
	z-index: 10; /* 다른 요소 위에 배치 */
}
</style>
</head>
<body>
	<!-- 헤더 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>

	<div class="container">

		<!-- 관리자 글쓰기 버튼 -->
		<c:if test="${sessionScope.user != null}">
			<c:set var="user" value="${sessionScope.user}" />
			<c:if test="${user.grade == 'admin'}">
				<a href="./RegionWriteView.do">
					<button style="margin-bottom: 20px;">글 쓰기</button>
				</a>
				<a href="./admin.jsp">
					<button>관리자 페이지</button>
				</a>
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
		<jsp:include page="./views/footer.jsp"></jsp:include>
	</div>
</body>
</html>
