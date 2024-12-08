<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
</head>
<style>
/* 게시글 상세 페이지 CSS */ /* 게시글 상세 페이지 CSS */
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f9f9f9;
}

table {
	width: 80%;
	margin: 20px auto;
	border-collapse: collapse;
	background-color: #fff;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 10px 15px;
	text-align: left;
	border: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
	text-align: center;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f1f1f1;
}

/* 공통 버튼 스타일 */
a {
	display: inline-block;
	margin: 10px 5px;
	padding: 10px 20px;
	background-color: #007BFF;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	text-align: center;
}

a:hover {
	background-color: #0056b3;
}

/* 링크 버튼 배치 */
.button-container {
	width: 80%;
	margin: 20px auto;
	display: flex;
	justify-content: space-between;
}

.button-container .left {
	display: flex;
}

.button-container .right {
	display: flex;
	gap: 10px; /* 버튼 간격 */
	margin-left: auto;
}
</style>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="header.html" />
	<c:if test="${not empty board}">
		<table border="1">
			<!-- 단일 게시글이므로 반복문을 제거하고, 바로 board 객체를 사용 -->
			<tr>
				<th>Post Number</th>
				<td>${board.postNumber}</td>
			</tr>
			<tr>
				<th>Nick Name</th>
				<td>${board.users.nickName}</td>
			</tr>
			<tr>
				<th>Title</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>Description</th>
				<td>${board.description}</td>
			</tr>
			<tr>
				<th>
					<c:choose>
						<c:when test="${board.updateTime != null}">수정일</c:when>
						<c:otherwise>작성일</c:otherwise>
					</c:choose>
				</th>
				<td>
					<c:choose>
						<c:when test="${board.updateTime != null}">${board.updateTime}</c:when>
						<c:otherwise>${board.createTime}</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</c:if>

	<div class="button-container">
		<!-- 왼쪽으로 배치할 버튼 -->
		<div class="left">
			<a href="./allBoard.do">목록으로 돌아가기</a>
		</div>

		<!-- 오른쪽으로 배치할 버튼 -->
		<div class="right">
			<c:if test="${writer}">
				<a href="./updateBoard.do?postNumber=${board.postNumber}">수정하기</a>
				<a href="./deleteBoard.do?postNumber=${board.postNumber}">글 삭제</a>
			</c:if>
		</div>
	</div>

</body>
</html>



