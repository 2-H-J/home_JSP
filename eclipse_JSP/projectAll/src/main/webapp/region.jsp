<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region List</title>
<style>
body {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	background-color: #f9f9f9; /* 배경색 추가 */
	font-family: Arial, sans-serif;
}

/* 중앙에 컨텐츠를 정렬 */
.container {
	max-width: 1200px; /* 전체 너비 제한 */
	margin: 0 auto; /* 양 옆에 공백을 줌 */
	padding: 20px;
}

/* 이미지 리스트 스타일 */
.image-container {
	display: flex;
	flex-wrap: wrap;
	gap: 30px; /* 이미지 간격 */
	justify-content: center; /* 중앙 정렬 */
}

.image-item {
	flex: 0 1 calc(23% - 20px); /* 한 줄에 4개 표시, 간격 고려 */
	text-align: center;
	background: #fff; /* 이미지 카드 배경 */
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	overflow: hidden;
	transition: transform 0.3s ease-in-out;
}

.image-item:hover {
	transform: scale(1.03); /* 마우스 오버 시 확대 효과 */
}

img {
	width: 100%;
	height: auto;
	display: block;
}

.title {
	margin: 10px 0;
	font-size: 1rem;
	font-weight: bold;
	color: #333;
}
/* pageNation CSS */

.pagination {
    display: flex;
    justify-content: center; /* 페이지 번호 중앙 정렬 */
    align-items: center;
    margin-top: 20px; /* 위로 공간 추가 */
    gap: 10px; /* 버튼 간 간격 */
}

.pagination a,
.pagination .current-page {
    display: inline-block;
    padding: 8px 12px; /* 버튼의 크기 */
    border-radius: 4px;
    text-decoration: none;
    font-size: 0.9rem;
    font-weight: bold;
    color: #555; /* 버튼 텍스트 색상 */
    background-color: #fff; /* 기본 버튼 배경 */
    border: 1px solid #ddd; /* 테두리 추가 */
    transition: background-color 0.2s, color 0.2s; /* 부드러운 효과 */
}

.pagination a:hover {
    background-color: #007bff; /* 링크 호버 시 배경색 */
    color: #fff; /* 링크 호버 시 텍스트 색상 */
    border-color: #007bff; /* 호버 시 테두리 색상 */
}

.pagination .current-page {
    background-color: #007bff; /* 현재 페이지 배경색 */
    color: #fff; /* 현재 페이지 텍스트 색상 */
    border: 1px solid #007bff; /* 현재 페이지 테두리 */
}
</style>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
    <jsp:include page="./views/footer.jsp"></jsp:include>

	<div class="container">
	
		<h1 style="text-align: center; color: #444;">Region List</h1>

		<c:if test="${sessionScope.user != null}">
			<c:set var="user" value="${sessionScope.user}" />
			<c:if test="${user.grade == 'admin'}">
				<!-- 관리자만 보이는 글쓰기 버튼 -->
				<a href="./RegionWriteView.do">
					<button style="margin-bottom: 20px;">글 쓰기</button>
					<c:if test="${user.grade == 'admin'}">
			<!-- 관리자가 아니면 버튼 표시 안됨 -->
			<a href="./admin.jsp"><button>admin</button></a>
		</c:if>
				</a>
			</c:if>
		</c:if>
	

		<!-- 이미지 리스트 -->
		<div class="image-container">
			<c:forEach var="region" items="${regionList}">
				<div class="image-item">
					<div class="title">${region.title}</div>
					<a href="./regionDetail.do?regionNumber=${region.regionNumber}">
						<img src="${region.imageUrl}" alt="${region.title}" />
					</a>
				</div>
			</c:forEach>
		</div>
		<!-- 페이징 처리 구간 -->
		<div class="pagination">
			<c:if test="${currentPage > 1 }">
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
		</div>
	</div>
</body>
</html>