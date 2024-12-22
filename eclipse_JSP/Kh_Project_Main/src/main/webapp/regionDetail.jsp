<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region Detail</title>


<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f05ff66bab3d2444d168e2e13e1ed414"></script>
<link rel="stylesheet" type="text/css" href="css/regionDetail.css">

<style>

</style>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>

	<!-- 메인 컨테이너 -->
	<div class="container">
		<!-- Header -->
		<div class="header">
			<h1>${regionDetail.title}</h1>
		</div>

		<!-- Navigation -->
		<div class="nav-bar">
			<a href="#photos">사진보기</a> <a href="#details">상세정보</a> <a
				href="#travelogue">여행톡</a> <a href="#recommendations">추천여행</a>
		</div>

		<!-- Content -->
		<div class="section" id="details">
			<h2 class="section-title">상세정보</h2>
			<p>${regionDetail.description}</p>
		</div>

		<h2 class="section-title">지도</h2>
		<p>
			<span>* 상세 정보 지도 클릭</span>
		</p>
		<div class="map-container" id="staticMap"></div>

		<div class="section" id="travelogue">
			<h2 class="section-title">여행톡</h2>
			<p>여행톡 내용 표시 예정...</p>
		</div>

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
						<a
							href="./updateRegion.do?regionNumber=${regionDetail.regionNumber}">수정</a>
						<a
							href="./deleteRegion.do?regionNumber=${regionDetail.regionNumber}">삭제</a>
						<a href="./region.do" class="right-button">목록으로 돌아가기</a>
					</div>
				</div>
			</c:if>
		</c:if>
	</div>

	<!-- 공통 Footer -->
	<footer>
		<jsp:include page="./views/footer.jsp"></jsp:include>
	</footer>

	<script>
		window.onload = function() {
			const latitude = parseFloat('<c:out value="${regionDetail.latitude}" />');
			const longitude = parseFloat('<c:out value="${regionDetail.longitude}" />');

			var markerPosition = new kakao.maps.LatLng(latitude, longitude);
			var marker = {
				position : markerPosition
			};

			var staticMapContainer = document.getElementById('staticMap'), staticMapOption = {
				center : new kakao.maps.LatLng(latitude, longitude),
				level : 3,
				marker : marker,
			};

			var staticMap = new kakao.maps.StaticMap(staticMapContainer,
					staticMapOption);
		};
	</script>
</body>
</html>
