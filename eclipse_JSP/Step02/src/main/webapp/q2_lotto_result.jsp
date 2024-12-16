<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로또 번호 결과</title>
</head>
<body>
	<h2>로또 번호 결과</h2>
	<!-- 로또 번호 결과를 표 형태로 출력 -->
	<table border="1">
		<!-- border="1": 테이블에 테두리를 지정합니다. -->
		<thead>
			<tr>
				<th>셋트 번호</th>
				<!-- 셋트 번호를 나타내는 헤더 셀 -->
				<th>로또 번호</th>
				<!-- 로또 번호를 나타내는 헤더 셀 -->
			</tr>
		</thead>
		<tbody>
			<!-- request 영역에 저장된 "lottoSets" 속성을 사용하여 로또 번호 목록을 출력합니다. -->
			<c:forEach var="lottoSet" items="${lottoSets}" varStatus="status">
				<!-- items="${lottoSets}": request에 저장된 로또 번호 목록을 반복합니다. -->
				<!-- var="lottoSet": 반복 중 현재 항목을 lottoSet 변수에 저장합니다. -->
				<!-- varStatus="status": 반복 상태를 관리하는 status 변수를 사용합니다. -->
				<tr>
					<!-- 각 셋트의 번호를 출력합니다. status.count는 1부터 시작하여 현재 반복의 순서를 표시합니다. -->
					<td>셋트 ${status.count}</td>
					<!-- 현재 셋트의 번호 출력 -->
					<td>
						<!-- 각 로또 셋트에 포함된 숫자들을 출력합니다. --> <c:forEach var="number"
							items="${lottoSet}">
							<!-- items="${lottoSet}": 현재 셋트에 포함된 로또 번호들을 반복합니다. -->
                            ${number} &nbsp; <!-- 로또 번호 사이에 공백을 추가하여 가독성을 높입니다. -->
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>