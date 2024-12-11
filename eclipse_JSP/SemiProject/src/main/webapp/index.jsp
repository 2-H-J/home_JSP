<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.UsersDTO"%>
<%@ page import="service.UsersService"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="jakarta.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
    <!-- 헤더 CSS 포함 -->
    <link rel="stylesheet" type="text/css" href="./css/header.css">
</head>
<body>
    <!-- 헤더 JSP 포함 -->
    <jsp:include page="./header.jsp"></jsp:include>
    

    <!-- 헤더 JavaScript 포함 -->
    <script src="./script/header.js"></script>
</body>
</html>
