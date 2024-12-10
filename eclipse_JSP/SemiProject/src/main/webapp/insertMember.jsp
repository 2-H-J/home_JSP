<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<jsp:include page="header.jsp" />
<style>
/* 기본 스타일 */
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background-color: #d0e7f9;
}

h1 {
	text-align: center;
	font-size: 1.8em;
	margin: 20px 0;
	color: #333;
}

/* 메인 컨테이너 */
div {
	max-width: 400px;
	margin: 50px auto;
	background-color: #fff;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	}
	
/* 폼 스타일 */
form {
	display: flex;
	flex-direction: column;
}

form div {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-size: 0.9em;
	color: #333;
}

input[type="text"], input[type="password"], input[type="email"] {
	width: calc(100% - 10px);
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 0.9em;
}

button {
	padding: 10px;
	background-color: #888;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 0.9em;
	cursor: pointer;
	text-align: center;
}

button:hover {
	background-color: #666;
}

#checkIdButton {
	float: right;
	background-color: #444;
	font-size: 0.8em;
	padding: 5px 10px;
	border-radius: 4px;
	cursor: pointer;
	color: #fff;
}

#checkIdButton:hover {
	background-color: #222;
}

small {
	display: block;
	margin-top: 5px;
	font-size: 0.8em;
}

small[style="color: red;"] {
	color: #e74c3c;
}

#submitButton:disabled {
	background-color: #bbb;
	cursor: not-allowed;
}

#submitButton:enabled {
	background-color: #888;
}
</style>

<script>
    function validateForm() {
        const loginId = document.getElementById("loginId").value;
        const password = document.getElementById("password").value;
        const nickName = document.getElementById("nickName").value;
        const userName = document.getElementById("userName").value;
        const userEmail = document.getElementById("userEmail").value;

        // 아이디 검증
        if (!/^[a-zA-Z0-9]{5,20}$/.test(loginId)) {
            alert("아이디는 5~20자의 영문, 숫자 조합이어야 합니다.");
            return false;
        }

        // 비밀번호 검증
        if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(password)) {
            alert("비밀번호는 8~20자이며, 대소문자, 숫자, 특수문자를 포함해야 합니다.");
            return false;
        }

        // 닉네임 검증
        if (!/^.{2,10}$/.test(nickName)) {
            alert("닉네임은 2~10자이어야 합니다.");
            return false;
        }

        // 이름 검증
        if (!/^[가-힣a-zA-Z\s]{2,20}$/.test(userName)) {
            alert("이름은 한글 또는 영문 2~20자이어야 합니다.");
            return false;
        }

        // 이메일 검증
        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(userEmail)) {
            alert("유효한 이메일 형식이어야 합니다.");
            return false;
        }

        return true; // 모든 검증 통과
    }

    function checkLoginId() {
        const loginId = document.getElementById("loginId").value;
        const messageElement = document.getElementById("idCheckMessage");
        const submitButton = document.getElementById("submitButton");

        if (!loginId) {
            messageElement.textContent = "아이디를 입력하세요.";
            messageElement.style.color = "red";
            return;
        }

        // JSP EL 충돌 방지
        const url = "./checkLoginId.do?loginId=" + encodeURIComponent(loginId);

        fetch(url)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    messageElement.textContent = "이미 사용 중인 아이디입니다.";
                    messageElement.style.color = "red";
                    submitButton.disabled = true;
                } else {
                    messageElement.textContent = "사용 가능한 아이디입니다.";
                    messageElement.style.color = "green";
                    submitButton.disabled = false;
                }
            })
            .catch(error => {
                console.error("아이디 중복 체크 오류:", error);
                messageElement.textContent = "아이디 확인 중 문제가 발생했습니다.";
                messageElement.style.color = "red";
                submitButton.disabled = true;
            });
    }
    </script>
</head>
<body>
	<div class="insert_member_container">
		<h1>회원가입</h1>
		<form id="registerForm" action="./insertMember.do" method="post"
			onsubmit="return validateForm()">
			<div>
				<label for="loginId">아이디</label> <input type="text" id="loginId"
					name="loginId" required>
				<button type="button" id="checkIdButton" onclick="checkLoginId()">중복
					확인</button>
				<small id="idCheckMessage" style="color: red;"></small>
			</div>
			<div>
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div>
				<label for="userName">이름</label> <input type="text" id="userName"
					name="userName" required>
			</div>
			<div>
				<label for="userEmail">이메일</label> <input type="email"
					id="userEmail" name="userEmail" required>
			</div>
			<div>
				<label for="nickName">닉네임</label> <input type="text" id="nickName"
					name="nickName" required>
			</div>
			<button type="submit" id="submitButton" disabled>회원가입</button>
		</form>
	</div>
</body>
</html>
