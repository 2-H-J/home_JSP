<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" type="text/css" href="css/header.css">

</head>

<body>
	<header>
		<nav class="menu_container">
			<ul class="menu_bar">
				<li><a href="./index.jsp">홈</a></li>
				<li><a href="./region.do">지역소개</a></li>
				<li><a href="./allBoard.do">여행게시판</a></li>
			</ul>
		</nav>
		<!-- 로그인 / 비로그인 상태 -->
		<div class="user-info">
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<span class="user-nickname">👤 ${sessionScope.user.nickName}</span>
					<a href="./mypageView.do" class="mypage-btn">내정보</a>
					<a href="./logout.do" class="logout-btn">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href="./loginView.do" class="login-btn">로그인</a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<script src="./script/header.js"></script>
</body>

</html>
