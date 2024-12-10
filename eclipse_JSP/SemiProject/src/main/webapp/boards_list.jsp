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
	<!-- 공통 헤더 -->
	<jsp:include page="header.jsp" />

	<h1>게시글 목록</h1>

	<!-- 검색 폼 -->
	<form action="boardsList.do" method="get">
		<input type="text" name="keyword" placeholder="검색어 입력"
			value="<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>" />
		<select name="type">
			<option value="title"
				<%="title".equals(request.getParameter("type")) ? "selected" : ""%>>제목</option>
			<option value="writer"
				<%="writer".equals(request.getParameter("type")) ? "selected" : ""%>>작성자</option>
		</select>

		<!-- 정렬 기준 선택 -->
		<select name="sort">
			<option value="title"
				<%="title".equals(request.getParameter("sort")) ? "selected" : ""%>>제목</option>
			<option value="createTime"
				<%="createTime".equals(request.getParameter("sort")) ? "selected" : ""%>>작성일</option>
			<option value="userNumber"
				<%="userNumber".equals(request.getParameter("sort")) ? "selected" : ""%>>작성자</option>
		</select>

		<!-- 오름차순/내림차순 선택 -->
		<select name="order">
			<option value="asc"
				<%="asc".equals(request.getParameter("order")) ? "selected" : ""%>>오름차순</option>
			<option value="desc"
				<%="desc".equals(request.getParameter("order")) ? "selected" : ""%>>내림차순</option>
		</select>

		<button type="submit">검색</button>
	</form>

	<!-- 검색 결과 출력 -->
	<c:if test="${not empty list}">
		<table border="1">
			<thead>
				<tr>
					<th>Post Number</th>
					<th>tag</th>
					<th>nickName</th>
					<th>Title</th>
					<th>Created/Updated</th>
					<th>view count</th>
					<th>like count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.postNumber}</td>
						<!-- tag 값이 null일 경우 빈 문자열로 출력 -->
						<td>${board.tag != null ? board.tag : ''}</td>
						<td>${board.nickName}</td>
						<td><a href="./boardDetail.do?postNumber=${board.postNumber}">${board.title}</a></td>
						<td><c:choose>
								<c:when test="${board.updateTime != null}">
                                    ${board.formattedUpdateTime}
                                </c:when>
								<c:otherwise>
                                    ${board.formattedCreateTime}
                                </c:otherwise>
							</c:choose></td>
							<td>${board.bcount }</td>
							<td>${board.blike }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<!-- 검색 결과가 없을 경우 -->
	<c:if test="${empty list}">
		<p>검색 결과가 없습니다.</p>
	</c:if>

	<!-- 글쓰기 링크 -->
	<a href="./boardWriteView.do">글쓰기</a>

	<!-- 목록으로 돌아가기 링크 -->
	<br>
	<a href="./allBoard.do"><button>목록으로 돌아가기</button></a>
</body>
</html>