<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 실패</title>
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin: 0;
	padding: 20px;
	background-color: #fff3f3;
}

.error-container {
	margin-top: 50px;
	background-color: #ffe0e0;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	display: inline-block;
}

h1 {
	color: #dc3545;
}

a {
	text-decoration: none;
	color: #fff;
	background-color: #dc3545;
	padding: 10px 20px;
	border-radius: 5px;
	margin-top: 20px;
	display: inline-block;
}

a:hover {
	background-color: #c82333;
}
</style>
</head>
<body>
	<div class="error-container">
		<h1>회원가입에 실패했습니다.</h1>
		<p>다시 시도하거나 관리자에게 문의하세요.</p>
		<a href="./insertMember.jsp">회원가입 페이지로 돌아가기</a>
	</div>
</body>
</html>
