<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- prefix=""는 원하는 단어를 넣어도됨 / prefix : 접두사-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>el 태그 테스트</h2>
	<p>el 태그는 아래와 같이 영역이 존재</p>
	<ul>
		<li>pageScope : 현재 JSP 페이지에 있는 객체</li>
		<li>requestScope : 현재 request 객체에 접근</li>
		<li>sessionScope : 현재 sessionScope 객체에 접근</li>
		<li>applicationScope : 어플리케이션 전체에서 공유되는 객체</li>
	</ul>
	<hr>
	<p>1. request 영역에 저장된 내용은 requestScope를 사용해서 뽑음</p>
	<p>나이 : ${requestScope.age }</p>

	<hr>
	<p>2. request 영역에 저장된 StudentVO 객체를 뽑음</p>
	<!-- 실제로는 필드로 직접 접근이 아니라 get 메서드를 호출한 것 -->
	<p>${requestScope.vo.getStudentNo()}/
		${requestScope.vo.studentName} / ${requestScope.vo.majorName} /
		${requestScope.vo.studentScore}</p>
	<!-- studentNo -> getStudentNo() : VO에 있는 변수가 private이기에 필드접근이 안되지만 get메서드를 만들어 둬서 메서드를 호출 하는 것 getStudentNo() -->

	<hr>
	<h2>조건문</h2>
	<!-- if문, test="조건식", else랑 else if가 없음 -->
	<c:if test="${requestScope.age < 20 }">
		<p>미성년자 입니다.</p>
	</c:if>
	<c:if test="${requestScope.age > 20 }">
		<p>성인 입니다.</p>
	</c:if>

	<!-- 여러 조건문 : choose -->
	<c:choose>
		<c:when test="${requestScope.age > 20 && requestScope.age < 30 }">
			<p>20대 입니다.</p>
		</c:when>
		<c:when test="${requestScope.age > 30 && requestScope.age < 40 }">
			<p>30대 입니다.</p>
		</c:when>
		<c:otherwise>
			<!-- otherwise : 마지막 조건문 지정 -->
			<p>40대 이상이거나 미성년자 입니다.</p>
		</c:otherwise>
	</c:choose>

	<hr>
	<!-- 반복문 -->
	<!-- 몇부터 몇까지 꺼내는 경우 -->
	<h2>반복문</h2>
	<ul>
		<c:forEach var="i" begin="1" end="10" step="1">
			<!-- var="반복문 제어변수", begin="시작값", end="마지막 값", step="증가값"  -->
			<li>${i }번째반복</li>
		</c:forEach>
	</ul>
	<hr>
	<ul>
		<c:forEach var="i" begin="1" end="10" step="1" varStatus="status">
			<!-- var="반복문 제어변수", begin="시작값", end="마지막 값", step="증가값", varStatus="status" : 상태변수-->
			<li>${i},${status.current},${status.begin}, ${status.end}</li>
			<!-- current : 현재 아이템 값, begin : 시작값, end : 마지막값 -->
		</c:forEach>
	</ul>

	<p>-------------------------------------------------------------</p>
	<!-- list를 꺼내는 경우 -->
	<ul>
		<c:forEach var="student" items="${requestScope.list }"
			varStatus="status">
			<li>${student.toString()},${status.count }</li>
		</c:forEach>
	</ul>

	<!--
			상태변수
				current : 현재 아이템 값
				index : 인덱스 값 - 0부터 시작
				count : 1부터 순서값 시작
				first : 해당 순서가 첫번째인지 확인
				last : 해당 순서가 마지막인지 확인
				begin : 시작값
				end : 마지막값
				step : 증가값
	-->

</body>
</html>