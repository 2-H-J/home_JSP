<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form</title>
</head>
<body>
	<!-- 
		action="데이터를 받을 서버페이지 경로 지정"
		method="get" : url에 포함시켜서 전송
	-->
	<form action="data_result.jsp" method="get">
		<!-- name="속성 값은 보내는 데이터명(파라미터명)" -->
		<input type="text" name="data">
		<button>전송</button>
	</form>
	<hr>
	<form action="data_number.jsp" method="post">
		<input type="number" name="number">
		<button>전송</button>
	</form>
</body>
</html>