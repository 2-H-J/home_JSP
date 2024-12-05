<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 페이지</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.navbar {
	width: 100%;
	background-color: #8ED1FC;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px 50px;
}

.menu {
	display: flex;
	gap: 10px; /* 메뉴 항목 간의 간격 */
	width: 60%; /* 메뉴 전체의 너비 */
	justify-content: center; /* 메뉴를 가운데 정렬 */
}

.menu a {
	text-decoration: none;
	color: black;
	background-color: white;
	padding: 5px 5px;
	border-radius: 20px;
	font-size: 1em;
	text-align: center;
	flex: 1; /* 모든 메뉴 항목의 너비를 동일하게 설정 */
	display: flex; /* 메뉴 항목 내용을 가운데 정렬 */
	align-items: center;
	justify-content: center;
}

.menu a:hover {
	background-color: #D3D3D3;
}

.user-icon {
	width: 30px;
	height: 30px;
	background-color: white;
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 1px solid black;
	cursor: pointer;
	margin-right: 50px;
}

.login-container {
	margin-top: 50px;
	width: 360px;
	background-color: #FFFFFF;
	padding: 40px 30px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	text-align: center;
}

.login-container h1 {
	font-size: 1.8em;
	margin-bottom: 20px;
	color: #333;
}

.login-container input[type="text"], .login-container input[type="password"]
	{
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #E0E0E0;
	border-radius: 5px;
	background-color: #F9F9F9;
}

.login-container .remember-me {
	display: flex;
	align-items: center;
	justify-content: start;
	font-size: 0.9em;
	color: #777;
	margin-bottom: 20px;
}

.login-container .remember-me input {
	margin-right: 5px;
}

.login-container button {
	width: 100%;
	padding: 10px;
	background-color: #D3D3D3;
	border: none;
	border-radius: 5px;
	font-size: 1em;
	color: #555;
	cursor: pointer;
}

.login-container button:hover {
	background-color: #C0C0C0;
}

.login-container .links {
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
	font-size: 0.9em;
}

.login-container .links a {
	text-decoration: none;
	color: #888;
	background-color: #E0E0E0;
	padding: 5px 10px;
	border-radius: 20px;
}

.login-container .links a:hover {
	background-color: #D3D3D3;
}

.footer {
	margin-top: 20px;
	text-align: center;
	font-size: 0.9em;
	color: #555;
	background-color: #E0E0E0;
	width: 100%;
	padding: 20px 10px;
}

.footer a {
	text-decoration: none;
	color: #555;
	margin: 0 5px;
}
</style>
</head>
<body>
    <div class="navbar">
        <!-- 메뉴 네비게이션 -->
        <div class="menu">
            <a href="index.jsp">홈</a> <a href="./allBoard.do">게시판</a> <a href="./allUser.do">회원 목록</a>
        </div>
        <div class="user-icon">👤</div>
        <!-- 사용자 아이콘 -->
    </div>

    <div class="login-container">
        <h1>로그인</h1>
        <!-- 로그인 처리 form -->
        <form action="login.do" method="post">
            <input type="text" name="loginId" placeholder="아이디" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <div>
                <input type="checkbox" name="rememberMe" id="rememberMe"> 
                <label for="rememberMe">로그인 상태 유지</label>
            </div>
            <button type="submit">로그인</button>
        </form>

        <!-- 로그인 실패 메시지 -->
        <c:if test="${not empty param.error and param.error == 'invalid'}">
            <p style="color: red;">아이디 또는 비밀번호가 올바르지 않습니다.</p>
        </c:if>

        <div class="links">
            <a href="#">아이디 찾기</a> <a href="#">비밀번호 찾기</a> <a href="#">회원가입</a>
        </div>
    </div>

    <div class="footer">
        <a href="#">이용약관</a> | <a href="#">개인정보처리방침</a> | <a href="#">책임의 한계와 법적고지</a> | <a href="#">회원정보 고객센터</a>
        <p>TRIP Copyright © ABCD Corp all right</p>
    </div>
</body>

<!-- 이 JSP 페이지는 사용자 로그인 화면을 제공하는 페이지입니다. -->
<!-- 사용자는 로그인 ID와 비밀번호를 입력하고, "로그인 상태 유지" 옵션을 선택할 수 있습니다. -->
<!-- 로그인 실패 시 적절한 오류 메시지가 표시되며, "아이디 찾기", "비밀번호 찾기", "회원가입" 링크도 제공합니다. -->
<!-- 페이지 하단에는 이용약관, 개인정보처리방침, 책임의 한계와 법적고지에 대한 링크와 저작권 정보가 포함되어 있습니다. -->

</html>
