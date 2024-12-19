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

<!-- 스타일 정의 -->
<style>
/* 전체 페이지 스타일 */
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background-color: #fff;
	display: flex;
	flex-direction: column;
	min-height: 100vh; /* 화면 전체 높이를 기준으로 설정 */
}

/* 네비게이션 바 */
.navbar {
	width: 100%;
	background-color: #d0e7f9;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 20px 0;
}

/* 메뉴 스타일 */
.menu a {
	text-decoration: none;
	color: black;
	background-color: white;
	padding: 10px 30px;
	border-radius: 20px;
	font-weight: bold;
	text-align: center;
}

.menu a:hover {
	background-color: #e0e0e0;
}

/* 폼 스타일 */
.container {
	max-width: 400px;
	margin: 50px auto;
	background-color: white;
	padding: 30px 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.container h1 {
	font-size: 1.5em;
	font-weight: bold;
	margin-bottom: 20px;
}

/* 버튼 스타일 */
.submit-btn {
	width: 100%;
	padding: 10px;
	background-color: #ccc;
	color: white;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: not-allowed;
}

.submit-btn:enabled {
	background-color: #888;
	cursor: pointer;
}

.submit-btn:hover:enabled {
	background-color: #666;
}
</style>

<!-- 자바스크립트 -->
<script>
// 비밀번호 일치 여부 확인
function checkPasswordMatch() {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    const messageElement = document.getElementById("passwordMatchMessage");

    if (password && confirmPassword) {
        if (password === confirmPassword) {
            messageElement.textContent = "비밀번호가 일치합니다.";
            messageElement.style.color = "green";
        } else {
            messageElement.textContent = "비밀번호가 일치하지 않습니다.";
            messageElement.style.color = "red";
        }
    } else {
        messageElement.textContent = "";
    }
}

// 아이디 중복 확인
function checkLoginId() {
    const loginId = document.getElementById("loginId").value;
    const messageElement = document.getElementById("idCheckMessage");

    if (!loginId) {
        messageElement.textContent = "아이디를 입력하세요.";
        messageElement.style.color = "red";
        return;
    }

    const url = "./checkLoginId.do?loginId=" + encodeURIComponent(loginId);

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                messageElement.textContent = "이미 사용 중인 아이디입니다.";
                messageElement.style.color = "red";
            } else {
                messageElement.textContent = "사용 가능한 아이디입니다.";
                messageElement.style.color = "green";
            }
        })
        .catch(error => {
            console.error("아이디 확인 오류:", error);
            messageElement.textContent = "아이디 확인 중 문제가 발생했습니다.";
            messageElement.style.color = "red";
        });
}

// 이메일 중복 확인
function checkEmail() {
    const email = document.getElementById("userEmail").value;
    const messageElement = document.getElementById("emailCheckMessage");

    if (!email) {
        messageElement.textContent = "이메일을 입력하세요.";
        messageElement.style.color = "red";
        return;
    }

    const url = "./checkEmail.do?email=" + encodeURIComponent(email);

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                messageElement.textContent = "이미 사용 중인 이메일입니다.";
                messageElement.style.color = "red";
            } else {
                messageElement.textContent = "사용 가능한 이메일입니다.";
                messageElement.style.color = "green";
            }
        })
        .catch(error => {
            console.error("이메일 확인 오류:", error);
            messageElement.textContent = "이메일 확인 중 문제가 발생했습니다.";
            messageElement.style.color = "red";
        });
}

// 닉네임 중복 확인
function checkNickName() {
    const nickName = document.getElementById("nickName").value;
    const messageElement = document.getElementById("nickNameCheckMessage");

    if (!nickName) {
        messageElement.textContent = "닉네임을 입력하세요.";
        messageElement.style.color = "red";
        return;
    }

    const url = "./checkNickName.do?nickName=" + encodeURIComponent(nickName);

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                messageElement.textContent = "이미 사용 중인 닉네임입니다.";
                messageElement.style.color = "red";
            } else {
                messageElement.textContent = "사용 가능한 닉네임입니다.";
                messageElement.style.color = "green";
            }
        })
        .catch(error => {
            console.error("닉네임 확인 오류:", error);
            messageElement.textContent = "닉네임 확인 중 문제가 발생했습니다.";
            messageElement.style.color = "red";
        });
}
</script>
</head>
<body>
	<div class="insert_member_container">
		<h1>회원가입</h1>
		<form id="registerForm" action="./insertMember.do" method="post"
			onsubmit="return validateForm()">
			<!-- 아이디 입력 -->
			<div class="form-group">
				<label for="loginId">아이디</label>
				<input type="text" id="loginId" name="loginId" required>
				<button type="button" onclick="checkLoginId()">중복 확인</button>
				<small id="idCheckMessage"></small>
			</div>

			<!-- 비밀번호 입력 -->
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password" required oninput="checkPasswordMatch()">
			</div>

			<!-- 비밀번호 확인 -->
			<div class="form-group">
				<label for="confirmPassword">비밀번호 확인</label>
				<input type="password" id="confirmPassword" name="confirmPassword" required oninput="checkPasswordMatch()">
				<small id="passwordMatchMessage"></small>
			</div>

			<!-- 이름 입력 -->
			<div class="form-group">
				<label for="userName">이름</label>
				<input type="text" id="userName" name="userName" required>
			</div>

			<!-- 이메일 입력 -->
			<div class="form-group">
				<label for="userEmail">이메일</label>
				<input type="email" id="userEmail" name="userEmail" required>
				<button type="button" onclick="checkEmail()">중복 확인</button>
				<small id="emailCheckMessage"></small>
			</div>

			<!-- 닉네임 입력 -->
			<div class="form-group">
				<label for="nickName">닉네임</label>
				<input type="text" id="nickName" name="nickName">
				<button type="button" onclick="checkNickName()">중복 확인</button>
				<small id="nickNameCheckMessage"></small>
			</div>

			<!-- 회원가입 버튼 -->
			<button type="submit" class="submit-btn" id="submitButton">회원가입</button>
		</form>
	</div>
</body>
</html>
