<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

        .login-container input[type="text"],
        .login-container input[type="password"] {
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
            <a href="index.jsp">홈</a>
            <a href="#">지역소개</a>
            <a href="#">게시판</a>
        </div>
        <div class="user-icon">👤</div>
        <!-- 사용자 아이콘 -->
    </div>

    <div class="login-container">
        <h1>로그인</h1>
        <form action="./login.do" method="post">
            <input name="loginId" id="loginId" type="text" placeholder="아이디" required>
            <input name="password" id="password" type="password" placeholder="비밀번호" required>
            <div class="remember-me">
                <input type="checkbox" id="remember">
                <label for="remember">로그인 상태 유지</label>
            </div>
            <button type="submit">로그인</button>
        </form>
        <div class="links">
            <a href="#">아이디 찾기</a>
            <a href="#">비밀번호 찾기</a>
            <a href="#">회원가입</a>
        </div>
    </div>

    <div class="footer">
        <a href="#">이용약관</a> |
        <a href="#">개인정보처리방침</a> |
        <a href="#">책임의 한계와 법적고지</a> |
        <a href="#">회원정보 고객센터</a>
        <p>TRIP Copyright © ABCD Corp all night</p>
    </div>
</body>
</html>
