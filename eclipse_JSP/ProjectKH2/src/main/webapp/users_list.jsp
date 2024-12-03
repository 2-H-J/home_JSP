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
	<table>
		<thead>
			<tr>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
				<th>학과 번호</th>
			</tr>
		</thead>
		<tbody>
			<!-- majorList의 각 항목을 순회하면서 학과 정보를 표시 -->
			<c:forEach var="users" items="${list}">
				<tr>
					<td>${users.userNumber}</td>
					<td>${users.loginId}</td>
					<td>${users.nickName}</td>
					<td>${users.password}</td>
					<td>${users.createTime}</td>
					<td>${users.updateTime}</td>
					<td>${users.pwUpdateTime}</td>
					<td>${users.userName}</td>
					<td>${users.userEmail}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>