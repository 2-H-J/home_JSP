<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int dan = Integer.parseInt(request.getParameter("dan"));
	// name : 파라미터(Parameter)명
	%>

	<%
	for (int i = 1; i <= 9; i++) {
	%>
	<%=dan%>*<%=i%>=<%=dan * i%><br>
	<%
	}
	%>

<!-- 강사님 진행 풀이 -->
	<h2>-- <%=dan%>단 --</h2>
	<%
	for (int i = 1; i < 10; i++) {
	%>
	<p><%=dan%>*<%=i%>=<%=dan * i%></p>
	<%
	}
	%>
</body>
</html>