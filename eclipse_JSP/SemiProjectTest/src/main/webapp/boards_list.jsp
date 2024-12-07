<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boards List</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Post Number</th>
				<th>Title</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${list}">
				<tr>
					<td>${board.postNumber}</td>
					<td><a href="./boardDetail.do?postNumber=${board.postNumber}">${board.title}</a></td>
					<td><c:choose>
							<c:when test="${board.updateTime != null}">
                        ${board.updateTime}
                    </c:when>
							<c:otherwise>
                        ${board.createTime}
                    </c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="insert_board.jsp">글쓰기</a>
</body>
</html>
