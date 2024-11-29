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
	<c:if test="${empty sessionScope.msg }">
		<script>
			alert('로그인 하셔야 이용하실수 있습니다.');
			location.href = './03_login.jsp';
		</script>
	</c:if>
	<c:if test="${not empty sessionScope.msg }">
		<h2>${sessionScope.msg }</h2>
		<p><a href="./logout.do">로그아웃</a></p>
	</c:if>
</body>
</html>




