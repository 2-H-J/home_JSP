<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region Detail</title>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="header.html" />
	<h1>Region Detail</h1>

	<!-- 단일 지역의 상세 정보 출력 -->
	<div class="region-item">
		<h1>${regionDetail.title}</h1>
		<p>${regionDetail.description}</p>
		<img src="${regionDetail.imageUrl}" alt="${regionDetail.title}" />
	</div>

	<!-- 여기에 필요에 따라 추가적인 UI 요소를 넣을 수 있습니다. -->
</body>
</html>
