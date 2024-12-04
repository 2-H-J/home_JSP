<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>쿠키 정보 확인</h2>
	<p>전체 쿠키 확인 : ${header.cookie }</p>
	<p>${cookie.age.value },${cookie.text.value }</p>
	<script>
		document.write(document.cookie);
	</script>
</body>
</html>