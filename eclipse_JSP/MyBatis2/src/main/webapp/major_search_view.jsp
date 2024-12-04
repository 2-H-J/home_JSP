<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 50%;
	border-collapse: collapse;
	margin: 20px auto;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f4f4f4;
}
</style>
</head>
<body>
	<form action="./searchMajor.do" method="get">
		<select name="type">
			<option value="1">학과번호</option>
			<option value="2">학과명</option>
		</select> 
		<input type="text" name="search">
		<button>검색</button>
	</form>
	<hr>
	<c:if test="${requestScope.majorList != null }">
		<table>
			<thead>
				<tr>
					<th>학과 번호</th>
					<!-- 학과 번호를 표시할 열 헤더 -->
					<th>학과 이름</th>
					<!-- 학과 이름을 표시할 열 헤더 -->
				</tr>
			</thead>
			<tbody>
				<!-- majorList의 각 항목을 순회하면서 학과 정보를 표시 -->
				<c:forEach var="major" items="${majorList}">
					<tr>
						<td>${major.majorNo}</td>
						<!-- MajorDTO 객체의 majorNo 속성을 출력하여 학과 번호를 표시 -->
						<td>${major.majorName}</td>
						<!-- MajorDTO 객체의 majorName 속성을 출력하여 학과 이름을 표시 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>