<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>입력한 숫자: ${number}</h2>
	<h3>약수 목록:</h3>
	<ul>
		<c:forEach var="list" items="${list}">
			<li>${list}</li>
		</c:forEach>
	</ul>
</body>
</html>