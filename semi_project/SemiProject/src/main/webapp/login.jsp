<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>로그인</h2>
    <form action="login" method="post">
        <label for="loginId">아이디:</label>
        <input type="text" name="loginId" id="loginId" required><br>
        <label for="password">비밀번호:</label>
        <input type="password" name="password" id="password" required><br>
        <button type="submit">로그인</button>
    </form>
    <%
        String error = request.getParameter("error");
        if ("true".equals(error)) {
    %>
    <p style="color: red;">아이디 또는 비밀번호가 잘못되었습니다.</p>
    <% } %>
</body>
</html>