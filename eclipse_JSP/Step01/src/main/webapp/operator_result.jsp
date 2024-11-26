<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int num1 = Integer.parseInt(request.getParameter("n1"));
	int num2 = Integer.parseInt(request.getParameter("n2"));
	String op = request.getParameter("op");

	int result = 0;

	switch (op) {
	case "+":
		result = num1 + num2;
	break;
	case "-":
		result = num1 - num2;
		break;
	case "*":
		result = num1 * num2;
		break;
	case "/":
		result = num1 / num2;
		break;
	case "%":
		result = num1 % num2;
		break;
	}
	%>
	num1<%=op%>num2=<%=result%>


	<!-- --------------------------GPT코드----------------------------- -->
	<!-- 조건까지 추가됨 -->
	<%-- 	<%
	// 1. 사용자 입력값 가져오기
	String n1Str = request.getParameter("n1"); // 첫 번째 숫자
	String n2Str = request.getParameter("n2"); // 두 번째 숫자
	String op = request.getParameter("op"); // 연산자

	// 2. 입력값이 null인지 확인 후 숫자로 변환
	if (n1Str != null && n2Str != null && op != null) {
		try {
			double n1 = Double.parseDouble(n1Str); // String을 double로 변환
			double n2 = Double.parseDouble(n2Str); // String을 double로 변환

			// 3. 연산 수행
			double result = 0.0;
			boolean isDivideByZero = false;

			switch (op) {
			case "+":
		result = n1 + n2; // 덧셈
		break;
			case "-":
		result = n1 - n2; // 뺄셈
		break;
			case "*":
		result = n1 * n2; // 곱셈
		break;
			case "/":
		if (n2 != 0) {
			result = n1 / n2; // 나눗셈
		} else {
			isDivideByZero = true; // 0으로 나눌 경우 오류 처리
		}
		break;
			case "%":
		if (n2 != 0) {
			result = n1 % n2; // 나머지 연산
		} else {
			isDivideByZero = true; // 0으로 나눌 경우 오류 처리
		}
		break;
			default:
		out.println("<h3>유효하지 않은 연산자입니다.</h3>");
		return; // 잘못된 연산자일 경우 더 이상 진행하지 않음
			}

			// 4. 결과 출력
			if (isDivideByZero) {
		out.println("<h3>0으로 나눌 수 없습니다.</h3>");
			} else {
		out.println("<h3>결과: " + n1 + " " + op + " " + n2 + " = " + result + "</h3>");
			}

		} catch (NumberFormatException e) {
			// 숫자가 아닌 값이 입력된 경우 처리
			out.println("<h3>잘못된 입력입니다. 숫자를 입력하세요.</h3>");
		}
	} else {
		// 입력값이 누락된 경우
		out.println("<h3>모든 값을 입력해 주세요.</h3>");
	}
	%> --%>
</body>
</html>