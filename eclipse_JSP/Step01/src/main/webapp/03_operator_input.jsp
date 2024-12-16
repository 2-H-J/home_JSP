<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>operator_input</title>
</head>
<body>
<!-- operator_result.jsp에 계산한 결과 출력-->
	<form action="operator_result.jsp">
		<input type="number" name="n1">
		<select name="op">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
			<option value="%">%</option>
		</select>
		<input type="number" name="n2">
		<button>계산하기</button>
	</form>
</body>
</html>




