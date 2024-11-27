<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="studentRegister.do" method="post">
		학번 : <input type="text" name="studentNo"><br>
		이름 : <input type="text" name="studentName"><br>
		학과 : <input type="text" name="majorName"><br>
		학점 : <input type="text" name="studentScore"><br>
		<button>학생 정보 등록</button>
	</form>
</body>
</html>