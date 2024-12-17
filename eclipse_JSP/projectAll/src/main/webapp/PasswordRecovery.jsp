<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 찾기</title>
<style>
/* 스타일 설정 */
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	padding: 20px;
}

.container {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	background-color: #ffffff;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.popup {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: white;
	border: 1px solid #ddd;
	padding: 20px;
	width: 300px;
	border-radius: 8px;
	display: none; /* 초기 상태는 숨김 */
	z-index: 10;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
}

.popup h2 {
	font-size: 1.2em;
	margin-bottom: 10px;
	text-align: center;
}

.popup input[type="password"] {
	width: 100%;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.popup button {
	width: 100%;
	padding: 10px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.popup button:hover {
	background-color: #0056b3;
}

.overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	display: none; /* 초기 상태는 숨김 */
	z-index: 5;
}
</style>
<script>
/* 비밀번호 찾기: 사용자 인증 */
function handlePasswordRecovery(event) {
    event.preventDefault(); // 폼 기본 제출 방지

    const userName = document.getElementById("userName").value.trim();
    const loginId = document.getElementById("loginId").value.trim();
    const userEmail = document.getElementById("userEmail").value.trim();

    // 서버에 사용자 정보를 POST로 전송
    fetch("recoverPassword.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: "userName=" + encodeURIComponent(userName) +
              "&loginId=" + encodeURIComponent(loginId) +
              "&userEmail=" + encodeURIComponent(userEmail),
    })
        .then(response => response.json()) // JSON 응답 처리
        .then(data => {
            console.log("서버 응답:", data);
            if (data.status === "success") { // 인증 성공
                console.log("인증 성공");
                alert("인증 성공되었습니다. 비밀번호를 변경해주시기 바랍니다.");

                // 비밀번호 변경 팝업 표시
                document.querySelector(".overlay").style.display = "block";
                document.querySelector(".popup").style.display = "block";
            } else { // 인증 실패
                console.log("인증 실패:", data.message);
                alert(data.message);
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        });
}

/* 비밀번호 실시간 일치 확인 */
function validatePasswords() {
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const messageSpan = document.getElementById("passwordMatchMessage");

    if (newPassword === "" || confirmPassword === "") {
        messageSpan.textContent = ""; // 입력값이 비어있으면 메시지 제거
    } else if (newPassword === confirmPassword) {
        messageSpan.style.color = "green";
        messageSpan.textContent = "비밀번호가 일치합니다."; // 비밀번호 일치
    } else {
        messageSpan.style.color = "red";
        messageSpan.textContent = "비밀번호가 일치하지 않습니다."; // 비밀번호 불일치
    }
}

/* 비밀번호 변경 처리 */
function handlePasswordUpdate(event) {
    event.preventDefault();

    const newPassword = document.getElementById("newPassword").value.trim();
    const confirmPassword = document.getElementById("confirmPassword").value.trim();

    if (newPassword === "" || confirmPassword === "") {
        alert("비밀번호를 입력해주세요.");
        return;
    }

    if (newPassword !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    // 서버에 비밀번호 변경 요청
    fetch("updatePassword.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: "newPassword=" + encodeURIComponent(newPassword),
    })
        .then(response => response.json())
        .then(data => {
            console.log("서버 응답:", data);
            if (data.status === "success") { // 비밀번호 변경 성공
                alert("비밀번호가 성공적으로 변경되었습니다. 로그인 페이지로 이동합니다.");
                
                closePopup(); // 팝업 닫기
                window.location.href = "loginView.jsp"; // 로그인 페이지로 이동
            } else { // 실패
                alert(data.message);
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        });
}

/* 팝업 닫기 함수 */
function closePopup() {
    document.querySelector(".overlay").style.display = "none";
    document.querySelector(".popup").style.display = "none";
}
</script>
</head>
<body>
	<!-- 비밀번호 찾기 컨테이너 -->
	<div class="container">
		<h1>비밀번호 찾기</h1>
		<form onsubmit="handlePasswordRecovery(event)">
			<label for="userName">이름:</label>
			<input type="text" id="userName" name="userName" required>

			<label for="loginId">아이디:</label>
			<input type="text" id="loginId" name="loginId" required>

			<label for="userEmail">이메일:</label>
			<input type="email" id="userEmail" name="userEmail" required>

			<button type="submit">확인</button>
		</form>
	</div>

	<!-- 비밀번호 변경 팝업 -->
	<div class="overlay" onclick="closePopup()"></div>
	<div class="popup">
		<h2>비밀번호 변경</h2>
		<form id="passwordUpdateForm" onsubmit="handlePasswordUpdate(event)">
			<input type="password" id="newPassword" name="newPassword" placeholder="새 비밀번호" required oninput="validatePasswords()">
			<input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" required oninput="validatePasswords()">
			<!-- 비밀번호 일치 여부 메시지 -->
			<span id="passwordMatchMessage" style="color: red; font-size: 0.9em;"></span>
			<button type="submit">비밀번호 변경</button>
		</form>
	</div>
</body>
</html>
