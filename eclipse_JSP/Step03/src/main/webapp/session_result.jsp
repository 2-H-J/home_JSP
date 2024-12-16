<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>세션 값 조회</h2>
	<p>세션에 저장된 내용 : ${sessionScope.msg }</p>
	<p><a href="./invalidate.do">세션 무력화</a></p>
</body>
</html>