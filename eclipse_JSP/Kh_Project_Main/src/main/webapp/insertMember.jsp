<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<!-- JSP 페이지 설정 -->
<!-- UTF-8 인코딩으로 설정 -->
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="./css/insertMember.css">
<!-- 스타일 정의 -->
</head>
<body>
<jsp:include page="./views/header.jsp"></jsp:include>
<div class="insert_member_container">
    <h1>회원가입</h1>
    <form id="registerForm" action="./insertMember.do" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="loginId">아이디</label>
            <input type="text" id="loginId" name="loginId" required>
            <button type="button" class="inline" onclick="checkLoginId()">중복 확인</button>
            <small id="idCheckMessage">아이디는 3~20자의 영문 대소문자와 숫자로만 구성되어야 합니다.</small>
        </div>

        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required oninput="checkPasswordMatch()">
            <small>비밀번호는 8~20자이며, 대소문자, 숫자, 특수문자(@$!%*?&)를 포함해야 합니다.</small>
        </div>

        <div class="form-group">
            <label for="confirmPassword">비밀번호 확인</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required oninput="checkPasswordMatch()">
            <small id="passwordMatchMessage">위 비밀번호와 동일하게 입력하세요.</small>
        </div>
        
		<div class="form-group">
            <label for="userName">이름</label>
            <input type="text" id="userName" name="userName" required>
            <small>이름은 한글 또는 영문으로 2~20자여야 합니다.</small>
        </div>
        
        <div class="form-group">
            <label for="userEmail">이메일</label>
            <input type="email" id="userEmail" name="userEmail" required>
            <button type="button" class="inline" onclick="checkEmail()">중복 확인</button>
            <small id="emailCheckMessage">예: example@domain.com</small>
        </div>

        <div class="form-group">
            <label for="nickName">닉네임</label>
            <input type="text" id="nickName" name="nickName">
            <button type="button" class="inline" onclick="checkNickName()">중복 확인</button>
            <small id="nickNameCheckMessage">닉네임은 2~10자여야 합니다.</small>
        </div>

        <button type="submit" class="submit-btn">회원가입</button>
    </form>
</div>
<jsp:include page="./views/footer.jsp"></jsp:include>
<script src="./script/insertMember.js"></script>
</body>
</html>