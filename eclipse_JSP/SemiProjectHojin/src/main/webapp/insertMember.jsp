<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
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

/* 상단 네비게이션 바 */
.navbar {
	width: 100%;
	background-color: #d0e7f9;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 20px 0;
	position: relative;
}

.menu {
	display: flex;
	gap: 30px;
}

.menu a {
	text-decoration: none;
	color: black;
	background-color: white;
	padding: 10px 30px;
	border-radius: 20px;
	font-size: 1em;
	font-weight: bold;
	text-align: center;
}

.menu a:hover {
	background-color: #e0e0e0;
}

.user-icon {
	position: absolute;
	right: 20px;
	top: 15px;
	width: 40px;
	height: 40px;
	background-color: white;
	border: 1px solid #ccc;
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* 폼 컨테이너 */
.container {
	max-width: 400px;
	margin: 50px auto;
	background-color: white;
	padding: 30px 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.container h1 {
	font-size: 1.5em;
	font-weight: bold;
	margin-bottom: 20px;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.form-group input[type="text"], .form-group input[type="password"],
	.form-group input[type="email"] {
	width: calc(100% - 100px);
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 0.9em;
}

.form-group button {
	padding: 5px 15px;
	background-color: #888;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 0.9em;
	cursor: pointer;
}

.form-group button:hover {
	background-color: #666;
}

.submit-btn {
	width: 100%;
	padding: 10px;
	background-color: #ccc;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 1em;
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

/* 푸터 */
.footer {
	text-align: center;
	margin-top: auto; /* 푸터를 항상 하단에 고정 */
	padding: 15px 0;
	background-color: #f7f7f7;
	font-size: 0.8em;
	color: #777;
	width: 100%;
}
</style>


<script>
//폼 검증
function validateForm() {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
    return true;
}

// 메시지 출력 재사용 함수
function showMessage(elementId, message, color) {
    const messageElement = document.getElementById(elementId);
    messageElement.textContent = message;
    messageElement.style.color = color;
}

// 버튼 활성화/비활성화 재사용 함수
function toggleButton(buttonId, isDisabled) {
    const button = document.getElementById(buttonId);
    button.disabled = isDisabled;
}

// 공통 fetch 요청 함수
async function fetchData(url, onSuccess, onError) {
    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error(`서버 오류: ${response.status}`);
        const data = await response.json();
        onSuccess(data);
    } catch (error) {
        console.error(error);
        onError(error);
    }
}

// 아이디 중복 확인
function checkLoginId() {
    const loginId = document.getElementById("loginId").value;
    const messageElement = document.getElementById("idCheckMessage");
    const submitButton = document.getElementById("submitButton");
    const loginIdRegex = /^[a-zA-Z0-9]{5,20}$/; // 5~20자의 영문, 숫자 조합 정규식

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
                submitButton.disabled = true;
            } else if (!loginIdRegex.test(loginId)) {
                messageElement.textContent = "아이디는 5~20자의 영문, 숫자 조합이어야 합니다.";
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


// 이메일 중복 확인
function checkEmail() {
    const email = document.getElementById("userEmail").value;

    if (!email) {
        showMessage("emailCheckMessage", "이메일을 입력하세요.", "red");
        return;
    }

    const url = "./checkEmail.do?email=" + encodeURIComponent(email);
    fetchData(
        url,
        (data) => {
            if (data.exists) {
                showMessage("emailCheckMessage", "이미 사용 중인 이메일입니다.", "red");
                toggleButton("submitButton", true);
            } else {
                showMessage("emailCheckMessage", "사용 가능한 이메일입니다.", "green");
                toggleButton("submitButton", false);
            }
        },
        () => {
            showMessage("emailCheckMessage", "이메일 확인 중 문제가 발생했습니다.", "red");
            toggleButton("submitButton", true);
        }
    );
}



// 비밀번호 확인
function checkPasswordMatch() {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (password && confirmPassword) {
        if (password === confirmPassword) {
            showMessage("passwordMatchMessage", "비밀번호가 일치합니다.", "green");
        } else {
            showMessage("passwordMatchMessage", "비밀번호가 일치하지 않습니다.", "red");
        }
    } else {
        showMessage("passwordMatchMessage", "", "");
    }
}
//닉네임 중복 확인 함수
function checkNickName() {
    const nickName = document.getElementById("nickName").value; // 입력된 닉네임 값
    const messageElement = document.getElementById("nickNameCheckMessage"); // 결과 메시지 출력 요소
    const submitButton = document.getElementById("submitButton"); // 회원가입 버튼

    // 닉네임이 비어 있는 경우
    if (!nickName) {
        messageElement.textContent = "닉네임을 입력해주세요.";
        messageElement.style.color = "red";
        return;
    }

    const url = "./checkNickName.do?nickName=" + encodeURIComponent(nickName); // 서버로 요청 보낼 URL

    // 서버 요청
    fetch(url)
        .then(response => response.json()) // JSON 응답 처리
        .then(data => {
            if (data.exists) {
                // 닉네임이 이미 존재하는 경우
                messageElement.textContent = "이미 사용 중인 닉네임입니다.";
                messageElement.style.color = "red";
                submitButton.disabled = true; // 버튼 비활성화
            } else {
                // 닉네임이 사용 가능한 경우
                messageElement.textContent = "사용 가능한 닉네임입니다.";
                messageElement.style.color = "green";
                submitButton.disabled = false; // 버튼 활성화
            }
        })
        .catch(error => {
            // 요청 중 오류 발생
            console.error("닉네임 확인 중 오류:", error);
            messageElement.textContent = "닉네임 확인 중 문제가 발생했습니다.";
            messageElement.style.color = "red";
            submitButton.disabled = true; // 버튼 비활성화
        });
}
//폼 검증
function validateForm() {
    const loginId = document.getElementById("loginId").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const nickName = document.getElementById("nickName").value;
    const userName = document.getElementById("userName").value;
    const userEmail = document.getElementById("userEmail").value;

    // 정규식 규칙
    const loginIdRegex = /^[a-zA-Z0-9]{5,20}$/; // 5~20자의 영문, 숫자 조합
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/; // 8~20자, 대소문자, 숫자, 특수문자 포함
    const nickNameRegex = /^.{2,10}$/; // 2~10자
    const userNameRegex = /^[가-힣a-zA-Z\s]{2,20}$/; // 한글 또는 영문, 2~20자
    const userEmailRegex = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/; // 유효한 이메일 형식

    // 아이디 검증
    if (!loginIdRegex.test(loginId)) {
        alert("아이디는 5~20자의 영문, 숫자 조합이어야 합니다.");
        return false;
    }

    // 비밀번호 검증
    if (!passwordRegex.test(password)) {
        alert("비밀번호는 8~20자, 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        return false;
    }

    // 비밀번호 확인 검증
    if (password !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }

    // 닉네임 검증
    if (!nickNameRegex.test(nickName)) {
        alert("닉네임은 2~10자여야 합니다.");
        return false;
    }

    // 이름 검증
    if (!userNameRegex.test(userName)) {
        alert("이름은 한글 또는 영문으로 2~20자여야 합니다.");
        return false;
    }

    // 모든 검증 통과
    return true;
}

// 메시지 출력 재사용 함수
function showMessage(elementId, message, color) {
    const messageElement = document.getElementById(elementId);
    messageElement.textContent = message;
    messageElement.style.color = color;
}

// 버튼 활성화/비활성화 재사용 함수
function toggleButton(buttonId, isDisabled) {
    const button = document.getElementById(buttonId);
    button.disabled = isDisabled;
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
				<label for="loginId">아이디</label> <input type="text" id="loginId"
					name="loginId" required>
				<button type="button" onclick="checkLoginId()">중복 확인</button>
				<small id="idCheckMessage"></small>
			</div>

			<!-- 비밀번호 입력 -->
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required
					oninput="checkPasswordMatch()">
			</div>

			<!-- 비밀번호 확인 -->
			<div class="form-group">
				<label for="confirmPassword">비밀번호 확인</label> <input type="password"
					id="confirmPassword" name="confirmPassword" required
					oninput="checkPasswordMatch()"> <small
					id="passwordMatchMessage"></small>
			</div>

			<!-- 이름 입력 -->
			<div class="form-group">
				<label for="userName">이름</label> <input type="text" id="userName"
					name="userName" required>
			</div>

			<!-- 이메일 입력 -->
			<div class="form-group">
				<label for="userEmail">이메일</label> <input type="email"
					id="userEmail" name="userEmail" required>
				<button type="button" onclick="checkEmail()">중복 확인</button>
				<small id="emailCheckMessage"></small>
			</div>

			<!-- 닉네임 입력 -->
			<div class="form-group">
				<label for="nickName">닉네임</label> <input type="text" id="nickName"
					name="nickName" />
				<button type="button" onclick="checkNickName()">중복 확인</button>
				<small id="nickNameCheckMessage"></small>
			</div>

			<!-- 회원가입 버튼 -->
			<button type="submit" class="submit-btn" id="submitButton" disabled>회원가입</button>
		</form>
	</div>
</body>
</html>
