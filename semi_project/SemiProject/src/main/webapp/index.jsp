<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.UsersDTO" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="./all.do">유저정보 전체 조회</a>
	<hr>
	
    <%
        // 기본 제공 session 객체를 그대로 사용합니다.
        UsersDTO user = (UsersDTO) session.getAttribute("user");
        if (user != null) {
    %>
    <h2>환영합니다, <%= user.getNickName() %> 님!</h2>
    <a href="logout.jsp">로그아웃</a>
    <% } else { %>
    <h2>로그인이 필요합니다.</h2>
    <a href="login.jsp">로그인하기</a>
    <% } %>
</body>
</html>