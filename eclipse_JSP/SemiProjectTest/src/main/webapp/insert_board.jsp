<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="./insertBoard.do" method="post">
		<div class="form-group">
			<label for="title">제목</label> <input type="text" id="title"
				name="title" required>
		</div>
		<div class="form-group">
			<label for="content">내용</label> <input type="text" id="description"
				name="description" required>
		</div>
		<div class="form-group">
			<button type="submit">작성</button>
		</div>
	</form>
</body>
</html>