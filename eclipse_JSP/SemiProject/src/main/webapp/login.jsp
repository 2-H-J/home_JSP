<%@page import="dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    // 세션에서 사용자 정보를 가져옵니다.
    UsersDTO user = (UsersDTO) session.getAttribute("user");

    // 콘솔에 사용자 정보 출력
    System.out.println("세션에서 가져온 사용자 정보: " + user);
    response.sendRedirect("./index.jsp");
    // 조건에 따라 메시지 처리
    if (user != null) {
        System.out.println("환영 메시지: " + user.getNickName() + " 님 환영합니다!");
    } else {
        System.out.println("로그인이 필요합니다.");
    }
    %>
</body>
</html>
