<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 실패</title>
<style>
/* 전체 페이지 스타일 */
body {
	font-family: Arial, sans-serif; /* 기본 글꼴 설정 */
	text-align: center; /* 텍스트를 중앙 정렬 */
	margin: 0;
	padding: 20px;
	background-color: #fff3f3; /* 연한 빨간 배경색 */
}

/* 오류 메시지 컨테이너 스타일 */
.error-container {
	margin-top: 50px; /* 위쪽 여백 */
	background-color: #ffe0e0; /* 연한 빨간 배경 */
	padding: 20px; /* 내부 여백 */
	border-radius: 10px; /* 모서리를 둥글게 설정 */
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	display: inline-block; /* 컨텐츠 크기에 맞게 블록 지정 */
}

/* 제목 스타일 */
h1 {
	color: #dc3545; /* 붉은 텍스트 색상 */
}

/* 버튼 스타일 */
a {
	text-decoration: none; /* 밑줄 제거 */
	color: #fff; /* 흰색 텍스트 */
	background-color: #dc3545; /* 붉은 배경색 */
	padding: 10px 20px; /* 내부 여백 */
	border-radius: 5px; /* 둥근 모서리 */
	margin-top: 20px; /* 위쪽 여백 */
	display: inline-block; /* 블록처럼 동작하도록 설정 */
}

/* 버튼 호버 효과 */
a:hover {
	background-color: #c82333; /* 더 어두운 붉은색 배경 */
}
</style>
</head>
<body>
	<!-- 회원가입 실패 메시지 컨테이너 -->
	<div class="error-container">
		<h1>회원가입에 실패했습니다.</h1>
		<!-- 실패 메시지 출력 -->
		<p>다시 시도하거나 관리자에게 문의하세요.</p>
		<!-- 추가 안내 메시지 -->
		<a href="./insertMember.jsp">회원가입 페이지로 돌아가기</a>
		<!-- 회원가입 페이지로 돌아가는 링크 -->
	</div>
</body>
</html>
