<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- ID 찾기 성공 메시지를 보여주는 페이지 -->
    
    <!-- 성공 메시지 제목 -->
	<h2>ID 찾기 성공</h2>
    
    <!-- ID 표시 -->
    <!-- 서버에서 찾은 ID 값을 출력합니다 -->
    <p>입력하신 이메일로 등록된 ID: ${foundLoginId}</p>
    
    <!-- 로그인 페이지로 이동하는 링크 -->
    <a href="./loginView.jsp">로그인 페이지로 이동</a>
</body>
</html>
