<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JSP 페이지 설정 -->
<!-- 1. Java 언어 사용 설정 -->
<!-- 2. UTF-8 인코딩 지정으로 한글을 정상적으로 표시 -->
<!-- 3. JSTL 코어 태그 라이브러리 선언 -->
<!DOCTYPE html>
<html lang="ko">
<!-- HTML5 문서 선언 -->
<!-- lang="ko": 한국어 문서임을 명시 -->

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 반응형 웹을 위한 meta 태그 -->

<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css" href="css/loginView.css">
<!-- 외부 CSS 파일 연결 -->

<!-- 헤더 CSS 포함 (주석 처리) -->
<!-- <link rel="stylesheet" type="text/css" href="./css/header.css"> -->
</head>
<body>
	<!-- 헤더 JSP 포함 -->
    <jsp:include page="./views/header.jsp"></jsp:include>
	<!-- header.jsp를 현재 페이지에 포함 -->
	<!-- 헤더는 상단 메뉴와 로고 등을 포함 -->

	<!-- 로그인 전체를 감싸는 컨테이너 -->
	<div class="login-container">
        <h1>로그인</h1>
        <!-- 페이지 제목 -->

        <!-- 로그인 처리 form -->
        <form action="./login.do" method="post">
        <!-- POST 방식으로 ./login.do로 데이터를 전송 -->
            <input type="text" name="loginId" placeholder="아이디" required>
            <!-- 로그인 아이디 입력 필드 -->
            <!-- required: 필수 입력 필드로 설정 -->

            <input type="password" name="password" placeholder="비밀번호" required>
            <!-- 비밀번호 입력 필드 -->
            <!-- required: 필수 입력 필드로 설정 -->

            <div class="login-checkbox">
            <!-- 아이디 저장 및 자동 로그인 체크박스 -->
                <label for="rememberId">
                	<input type="checkbox" name="rememberId" id="rememberId">아이디 저장
                </label>
                
                <label for="autologin">
                	<input type="checkbox" name="autologin" id="autologin">로그인 상태 유지
                </label>
            </div>

            <button type="submit">로그인</button>
            <!-- 로그인 버튼 -->
        </form>

        <!-- 로그인 실패 메시지 -->
		<c:if test="${not empty param.error and param.error == 'invalid'}">
    		<p style="color: red;">아이디 또는 비밀번호가 올바르지 않습니다.</p>
		</c:if>
		<!-- URL 파라미터 error가 'invalid'인 경우 -->
		<!-- 아이디 또는 비밀번호가 잘못되었음을 알리는 메시지 출력 -->

        <div class="links">
        <!-- 로그인 관련 추가 링크 -->
            <a href="#">아이디 찾기</a>
            <!-- 아이디 찾기 기능 -->
            <a href="#">비밀번호 찾기</a>
            <!-- 비밀번호 찾기 기능 -->
            <a href="insertMember.jsp">회원가입</a>
            <!-- 회원가입 페이지로 이동 -->
        </div>
    </div>

    <jsp:include page="./views/footer.jsp"></jsp:include>
    <!-- footer.jsp 포함 -->
    <!-- 하단 레이아웃을 표시 -->
</body>
</html>
