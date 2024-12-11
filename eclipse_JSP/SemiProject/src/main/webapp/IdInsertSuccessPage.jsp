<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 성공</title>
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin: 0;
	padding: 20px;
	background-color: #f0f9f9;
}

.success-container {
	margin-top: 50px;
	background-color: #e0ffe0;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	display: inline-block;
}

h1 {
	color: #28a745;
}

a {
	text-decoration: none;
	color: #fff;
	background-color: #28a745;
	padding: 10px 20px;
	border-radius: 5px;
	margin-top: 20px;
	display: inline-block;
}

a:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="success-container">
		<h1>회원가입이 성공적으로 완료되었습니다!</h1>
		<p>로그인 페이지로 이동하여 서비스를 이용해보세요.</p>
		<a href="signin.jsp">로그인 페이지로 이동</a>
	</div>
</body>
</html>
