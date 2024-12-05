<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>수정</title>
</head>
<body>
	<h1>수정</h1>
	<form action="./syncBoard.do" method="post">
		<c:if test="${not empty boardList}">
			<c:forEach var="board" items="${boardList}">
				<div class="form-group">
					<label for="title">제목</label>
					<!-- 하나의 게시글 객체에서 제목을 가져와서 입력 필드에 표시 -->
					<input type="text" id="title" name="title" value="${board.title}"
						required>
				</div>
				<div class="form-group">
					<label for="description">내용</label>
					<!-- 하나의 게시글 객체에서 내용을 가져와서 입력 필드에 표시 -->
					<input type="text" id="description" name="description"
						value="${board.description}" required>
				</div>
				<div class="form-group">
					<input type="hidden" name="postNumber" value="${board.postNumber}">
					<!-- 게시글 번호 숨겨서 전달 -->
					<button type="submit">수정</button>
				</div>
			</c:forEach>
		</c:if>
	</form>
</body>
</html>

