<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region Detail</title>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=f05ff66bab3d2444d168e2e13e1ed414&autoload=false"></script>

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

/* 메인 컨테이너: 좌우 여백과 중앙 정렬 */
.container {
	max-width: 1200px; /* 전체 최대 너비 */
	margin: 0 auto;   /* 중앙 정렬 및 양쪽 여백 */
	padding: 20px;    /* 안쪽 여백 */
	box-sizing: border-box;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 전체에 그림자 효과 */
}

.header {
	background-color: #81d4fa;
	padding: 20px;
	text-align: center;
	color: #333;
}

.header h1 {
	margin: 0;
	font-size: 28px;
}

.header p {
	margin: 5px 0 0;
	font-size: 16px;
}

.nav-bar {
	display: flex;
	justify-content: center;
	background-color: #ddd;
	padding: 10px;
}

.nav-bar a {
	margin: 0 15px;
	text-decoration: none;
	color: #333;
	font-weight: bold;
}

.nav-bar a:hover {
	color: #007BFF;
}

.section {
	margin-bottom: 30px;
}

.section-title {
	font-size: 20px;
	margin-bottom: 10px;
	border-bottom: 2px solid #ddd;
	padding-bottom: 5px;
}

.map-container {
	width: 100%;
	height: 546.91px;
	background-color: #eaeaea;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 18px;
	color: #888;
	opacity: 1;
	margin-top: 20px;
	box-sizing: border-box;
}

.section img {
	display: block;
	margin: 0 auto;
	max-width: 100%;
	height: auto;
}

.buttons {
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
}

.buttons .left-buttons {
	display: flex;
	gap: 10px;
}

.buttons .right-button {
	margin-left: auto;
}

.buttons a {
	text-decoration: none;
	color: #fff;
	background-color: #007BFF;
	padding: 10px 15px;
	border-radius: 5px;
}

.buttons a:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
    <jsp:include page="./views/footer.jsp"></jsp:include>
	<!-- 메인 컨테이너: 모든 내용을 감싼다 -->
	<div class="container">
		<!-- Header -->
		<div class="header">
			<h1>${regionDetail.title}</h1>
			<p>${regionDetail.description}</p>
		</div>

		<!-- Navigation -->
		<div class="nav-bar">
			<a href="#photos">사진보기</a> 
			<a href="#details">상세정보</a> 
			<a href="#travelogue">여행톡</a> 
			<a href="#recommendations">추천여행</a>
		</div>

		<!-- Content -->
		<!-- 상세 정보 -->
		<div class="section" id="details">
			<h2 class="section-title">상세정보</h2>
			<p>${regionDetail.description}</p>
			<img src="${regionDetail.imageUrl}" alt="${regionDetail.title}" />
		</div>

		<!-- 지도 API -->
		<div class="section" id="map">
			<h2 class="section-title">지도</h2>
			<div class="map-container" id="map-api">지도 API 로드 중...</div>
		</div>

		<!-- 여행톡 -->
		<div class="section" id="travelogue">
			<h2 class="section-title">여행톡</h2>
			<p>여행톡 내용 표시 예정...</p>
		</div>

		<!-- 추천여행 -->
		<div class="section" id="recommendations">
			<h2 class="section-title">추천여행</h2>
			<p>추천여행 내용 표시 예정...</p>
		</div>

		<!-- Buttons (관리자 전용) -->
		<c:if test="${sessionScope.user != null}">
			<c:set var="user" value="${sessionScope.user}" />
			<c:if test="${user.grade == 'admin'}">
				<div class="buttons">
					<div class="left-buttons">
						<a href="./updateRegion.do?regionNumber=${regionDetail.regionNumber}">수정</a>
						<a href="./deleteRegion.do?regionNumber=${regionDetail.regionNumber}">삭제</a>
					</div>
					<a href="./region.do" class="right-button">목록으로 돌아가기</a>
				</div>
			</c:if>
		</c:if>
	</div>

	<script>
		window.onload = function() {
			// 카카오 지도 SDK 로드 후 실행되는 함수
			kakao.maps.load(function() {
				var container = document.getElementById('map-api');

				// regionDetail에서 latitude, longitude 값을 가져와서 설정
				var latitude = 37.5665; // 예시: 서울 위도
				var longitude = 126.9780; // 예시: 서울 경도

				// 지도 설정
				var options = {
					center: new kakao.maps.LatLng(latitude, longitude),
					level: 5
				};

				var map = new kakao.maps.Map(container, options); // 지도 객체 생성
			});
		};
	</script>
</body>
</html>
