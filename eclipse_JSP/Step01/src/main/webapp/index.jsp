<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- JSP : java server page -->
	<h2>첫번째 JSP 페이지</h2>
	<p><%=new Date().toLocaleString()%></p>
</body>
</html>