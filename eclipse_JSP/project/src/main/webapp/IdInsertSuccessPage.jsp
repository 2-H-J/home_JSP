<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 성공</title>
<style>
/* 기본 페이지 스타일 */
body {
	font-family: Arial, sans-serif; /* 기본 글꼴 설정 */
	text-align: center; /* 텍스트를 중앙 정렬 */
	margin: 0;
	padding: 20px;
	background-color: #f0f9f9; /* 연한 파란 배경색 */
}

/* 성공 메시지 컨테이너 스타일 */
.success-container {
	margin-top: 50px;
	background-color: #e0ffe0; /* 연한 초록 배경색 */
	padding: 20px;
	border-radius: 10px; /* 모서리를 둥글게 설정 */
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	display: inline-block; /* 컨텐츠 크기에 맞게 블록 지정 */
}

/* 제목 스타일 */
h1 {
	color: #28a745; /* 초록색 텍스트 */
}

/* 버튼 스타일 */
a {
	text-decoration: none; /* 밑줄 제거 */
	color: #fff; /* 흰색 텍스트 */
	background-color: #28a745; /* 초록색 배경 */
	padding: 10px 20px; /* 내부 여백 */
	border-radius: 5px; /* 모서리를 둥글게 설정 */
	margin-top: 20px;
	display: inline-block; /* 블록처럼 동작하도록 설정 */
}

/* 버튼 호버 효과 */
a:hover {
	background-color: #218838; /* 호버 시 더 어두운 초록색 */
}
</style>
</head>
<body>
	<!-- 회원가입 성공 메시지 컨테이너 -->
	<div class="success-container">
		<h1>회원가입이 성공적으로 완료되었습니다!</h1>
		<!-- 성공 메시지 -->
		<p>로그인 페이지로 이동하여 서비스를 이용해보세요.</p>
		<!-- 안내 메시지 -->
		<a href="loginView.jsp">로그인 페이지로 이동</a>
		<!-- 로그인 페이지로 이동하는 링크 버튼 -->
	</div>
</body>
</html>
