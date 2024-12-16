<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./findLoginId.do" method="post">
    <label for="userEmail">이메일</label>
    <input type="email" id="userEmail" name="userEmail" required>
    <button type="submit">ID 찾기</button>
	</form>
	
</body>
</html>