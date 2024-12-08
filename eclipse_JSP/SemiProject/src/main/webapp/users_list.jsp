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
	<!-- 공통 헤더 -->
	<jsp:include page="header.html" />
	<h2>전체 회원 정보</h2>
	<table border="1">
		<thead>
			<tr>
				<th>유저 번호</th>
				<th>아이디</th>
				<th>닉네임</th>
				<th>암호</th>
				<th>가입일</th>
				<th>정보수정</th>
				<th>암호수정</th>
				<th>이름</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="users" items="${requestScope.list }">
				<tr>
					<td>${users.userNumber }</td>
					<td>${users.loginId }</td>
					<td>${users.nickName }</td>
					<td>${users.password }</td>
					<td>${users.createTime }</td>
					<td>${users.updateTime }</td>
					<td>${users.pwUpdateTime }</td>
					<td>${users.userName }</td>
					<td>${users.userEmail }</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</body>
</html>