<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css" href="css/loginView.css">
<script>
        window.onload = () => {
            //좋아요 링크 클릭 -> 경고창 띄우기
            
            //댓글 좋아요
        
        }
        
    
</script>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="header.jsp" />
	  <div class="login-container">
        <h1>로그인</h1>
        <!-- 로그인 처리 form -->
        <form action="./login.do" method="post">
            <input type="text" name="loginId" placeholder="아이디" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <div class="login-checkbox">
                <label for="rememberMe">
                	<input type="checkbox" name="rememberMe" id="rememberMe">아이디 저장
                </label>
                
                <label for="autologin">
                <input type="checkbox" name="autologin" id="autologin">자동 로그인
                </label>
            </div>
            <button type="submit">로그인</button>
        </form>

        <!-- 로그인 실패 메시지 -->
        <c:if test="${not empty param.error and param.error == 'invalid'}">
            <p style="color: red;">아이디 또는 비밀번호가 올바르지 않습니다.</p>
        </c:if>

        <div class="links">
            <a href="findId.jsp">아이디 찾기</a> <a href="findPassword.jsp">비밀번호 찾기</a> <a href="insertMember.jsp">회원가입</a>
        </div>
    </div>

    <div class="footer">
        <a href="#">이용약관</a> | <a href="#">개인정보처리방침</a> | <a href="#">책임의 한계와 법적고지</a> | <a href="#">회원정보 고객센터</a>
        <p>TRIP Copyright © ABCD Corp all right</p>
    </div>
</body>
</html>
