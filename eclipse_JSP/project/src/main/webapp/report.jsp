<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고</title>
</head>
<body>
	<div class="container">
		<h1>신고</h1>
		<form action="./reportWrite.do" method="post">
			<div class="form-group">
				<label for="title">신고 사유</label>
				<input type="text" id=""reason"" name="reason" required>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn submit-btn">등록</button>
			</div>
		</form>
	</div>
</body>
</html>