<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f7f7f7;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	background-color: white;
	width: 400px;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h1 {
	margin-bottom: 20px;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
	text-align: left;
}

label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
	color: #555;
}

input[type="text"], input[type="password"], input[type="email"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #5cb85c;
	color: white;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background-color: #4cae4c;
}

.button-secondary {
	background-color: #d9534f;
}

.button-secondary:hover {
	background-color: #c9302c;
}

.form-footer {
	margin-top: 10px;
	font-size: 14px;
	color: #777;
}
</style>
<script>
function checkNickname() {
    const nickName = document.getElementById("nickName").value;
    const nicknamePattern = /^[a-zA-Z0-9가-힣_-!]{1,32}$/;

    if (!nickName) {
        alert("닉네임을 입력하세요.");
        return;
    }

    if (!nicknamePattern.test(nickName)) {
        alert("닉네임은 한글, 영어, 숫자, '_', '-', '!'만 가능하며, 최대 32자까지 가능합니다.");
        return;
    }

    // AJAX 요청으로 닉네임 중복 확인
    fetch(`checkNickname.do?nickName=${nickName}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                alert("이미 사용 중인 닉네임입니다.");
            } else {
                alert("사용 가능한 닉네임입니다.");
            }
        })
        .catch(error => {
            console.error("닉네임 중복 확인 오류:", error);
        });
}


function validatePasswords() {
    const newPassword = document.getElementById("newPassword").value.trim();
    const confirmPassword = document.getElementById("confirmPassword").value.trim();

    if (!newPassword || !confirmPassword) {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

    if (newPassword !== confirmPassword) {
        alert("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        return false;
    }

    return true;
}

    </script>
</head>
<body>
	<div class="container">
		<h1>내 정보 수정</h1>
		<form action="./updateUser.do" method="post"
			onsubmit="return validatePasswords();">
			<div class="form-group">
				<label for="loginId">아이디</label> <input type="text" id="loginId"
					name="loginId" value="${user.loginId}" readonly>
			</div>
			<div class="form-group">
				<label for="nickName">닉네임</label> <input type="text" id="nickName"
					name="nickName" value="${user.nickName}">
				<button type="button" onclick="checkNickname()">중복 확인</button>
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" id="email"
					name="email" value="${user.userEmail}">
			</div>
			<div class="form-group">
				<label for="currentPassword">현재 비밀번호</label> <input type="password"
					id="currentPassword" name="currentPassword">
			</div>
			<div class="form-group">
				<label for="newPassword">새 비밀번호</label> <input type="password"
					id="newPassword" name="newPassword">
			</div>
			<div class="form-group">
				<label for="confirmPassword">새 비밀번호 확인</label> <input
					type="password" id="confirmPassword" name="confirmPassword">
			</div>
			<button type="submit">정보 수정</button>
		</form>
		<div class="form-footer">
			<a href="./index.jsp" class="button-secondary">취소</a>
		</div>
	</div>
</body>
</html>
