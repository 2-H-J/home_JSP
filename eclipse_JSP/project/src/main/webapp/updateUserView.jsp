<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 
c	JSTL Core	제어문 (반복, 조건, 변수 설정)	조건문, 반복문, 데이터 출력
fn	JSTL Functions	문자열 처리 및 함수 제공	문자열 조작 및 검색
 -->

<!-- JSP 페이지 설정 -->
<!-- 1. JSP에서 Java 코드를 사용하기 위해 language="java"를 지정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보 수정</title>
<link rel="stylesheet" type="text/css" href="css/updateUser.css?v=2">
<!-- 페이지 제목 설정: "내정보 수정" -->
<!-- CSS 파일 연결: updateUser.css. ?v=2는 캐시를 방지하기 위한 버전 정보 -->
</head>
<body>
	<!-- 헤더 JSP 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<!-- header.jsp는 상단 네비게이션 바, 로고 등을 포함하는 헤더 레이아웃 파일 -->

	<!-- 마이페이지 전체를 감싸는 컨테이너 -->
	<div class="mypage-container">
		<!-- 페이지 제목 -->
		<h1>정보 수정</h1>

		<!-- 사용자 정보 수정 폼 -->
		<form action="./updateUser.do" method="post">
			<!-- 사용자 입력을 서버로 전송하기 위한 POST 메서드 폼 -->

			<!-- 아이디와 이름 (읽기 전용) -->
			<div class="form-group inline-group">
				<label>아이디 <input type="text" name="loginId"
					value="${user.loginId}" readonly>
				</label> <label>이름 <input type="text" name="userName"
					value="${user.userName}" readonly>
				</label>
				<!-- readonly: 읽기 전용 필드로 수정 불가능 -->
				<!-- ${user.loginId}와 ${user.userName}은 컨트롤러에서 전달된 user 객체의 속성 -->
			</div>

			<!-- 닉네임 수정 및 중복 확인 -->
			<div class="form-group">
				<label>닉네임</label>
				<div class="inline-input">
					<input type="hidden" id="originalNickname" value="${user.nickName}">
					<!-- 닉네임의 기존 값을 저장 (비교용) -->
					<input type="text" id="nickname" name="nickName"
						value="${user.nickName}">
					<!-- 닉네임 수정 필드 -->
					<button type="button" id="checkNicknameBtn" class="blue-button">중복
						확인</button>
					<!-- 닉네임 중복 확인 버튼 -->
				</div>
				<p id="nicknameMessage" class="message"></p>
				<!-- 닉네임 중복 확인 결과 메시지를 표시 -->
			</div>

			<!-- 비밀번호 변경 -->
			<div class="form-group">
				<label>비밀번호</label> <input type="password" name="currentPassword"
					placeholder="현재 비밀번호" style="margin-bottom: 2px;">
				<!-- 현재 비밀번호 입력 -->
				<c:if test="${not empty error}">
					<span class="message error">${error}</span>
				</c:if>
				<input type="password" id="newPassword" name="newPassword"
					placeholder="새 비밀번호"> <input type="password"
					id="confirmPassword" name="confirmNewPassword"
					placeholder="새 비밀번호 확인">
				<p id="passwordMatchMessage" class="message"></p>
				<!-- 새 비밀번호와 확인 비밀번호의 일치 여부를 표시 -->
			</div>


			<!-- 이메일 수정 및 중복 확인 -->
			<div class="form-group">
				<label>이메일</label>
				<div class="inline-input email-container">
					<!-- 기존 이메일 값을 저장 -->
					<input type="hidden" id="originalEmail" value="${user.userEmail}">
					<!-- 이메일 입력 필드: @ 앞부분 -->
					<input type="text" id="emailLocal" name="emailLocal"
						value="${fn:substringBefore(user.userEmail, '@')}"
						autocomplete="off"> <span>@</span>
					<!-- 이메일 도메인 선택 필드 -->
					<select name="emailDomain" id="emailDomain">
						<option value="${fn:substringAfter(user.userEmail, '@')}" selected>
							${fn:substringAfter(user.userEmail, '@')}</option>
						<c:set var="emailDomains"
							value="gmail.com,naver.com,daum.net,nate.com,yahoo.com,icloud.com" />
						<c:forEach var="domain" items="${fn:split(emailDomains, ',')}">
							<c:if test="${domain != fn:substringAfter(user.userEmail, '@')}">
								<option value="${domain}">${domain}</option>
							</c:if>
						</c:forEach>
						<option value="custom">직접 입력</option>
					</select>

					<!-- 이메일 중복 확인 버튼 -->
					<button type="button" id="checkEmailBtn" class="blue-button">중복
						확인</button>
				</div>
				<!-- 직접 입력 도메인 필드 -->
				<div id="customDomainContainer" class="custom-domain"
					style="display: none;">
					<input type="text" id="customDomain" name="customDomain"
						placeholder="도메인 입력">
				</div>
				<p id="emailMessage" class="message"></p>
				<!-- 이메일 중복 확인 결과 메시지를 표시 -->
			</div>

			<!-- 수정 및 취소 버튼 -->
			<div class="form-buttons">
				<button type="submit" class="blue-button">수정</button>
				<!-- 서버로 폼 데이터 전송 -->
				<button type="button" onclick="location.href='mypageView.do';"
					class="cancel-button">취소</button>
				<!-- 취소 버튼 클릭 시 마이페이지로 이동 -->
			</div>
		</form>
	</div>

	<!-- 하단 푸터 JSP 포함 -->
	<jsp:include page="./views/footer.jsp"></jsp:include>
	<!-- footer.jsp는 사이트 하단 레이아웃을 표시 -->

	<script src="script/updateUser.js"></script>
	<!-- updateUser.js는 닉네임 및 이메일 중복 확인과 같은 동작을 JavaScript로 처리 -->
</body>
</html>
