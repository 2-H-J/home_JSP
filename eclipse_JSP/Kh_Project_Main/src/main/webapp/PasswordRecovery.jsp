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
    padding: 20px;
    margin: 0;
}

.container {
    max-width: 450px;
    margin: 350px auto;
    padding: 20px 25px;
    background-color: #ffffff;
    border: 1px solid #d1d9e6;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.container h1 {
    font-size: 1.8em;
    text-align: center;
    margin-bottom: 20px;
    color: #334155;
}

.container p {
    font-size: 1em;
    text-align: center;
    margin-bottom: 20px;
    color: #64748b;
}

.container form {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.container input {
    width: 100%;
    padding: 12px;
    font-size: 1em;
    border: 1px solid #cbd5e1;
    border-radius: 8px;
    transition: border-color 0.3s, box-shadow 0.3s;
}

.container input:focus {
    border-color: #2563eb;
    box-shadow: 0 0 4px rgba(37, 99, 235, 0.4);
    outline: none;
}

.container button {
    padding: 12px;
    font-size: 1.2em;
    font-weight: bold;
    color: white;
    background-color: #C0C0C0;
    border: none;
    border-radius: 8px;
    cursor: pointer;
}

.container button:hover {
    background-color: #777;
}

.popup {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: white;
    border: 1px solid #cbd5e1;
    padding: 20px;
    width: 400px;
    border-radius: 10px;
    display: none; /* 초기 상태는 숨김 */
    z-index: 10;
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
}

.popup h2 {
    font-size: 1.4em;
    margin-bottom: 15px;
    text-align: center;
    color: #334155;
}

.popup input[type="password"] {
    width: 100%;
    padding: 12px;
    margin-bottom: 15px;
    border: 1px solid #cbd5e1;
    border-radius: 8px;
    font-size: 1em;
    background-color: #f1f5f9;
    transition: border-color 0.3s, box-shadow 0.3s;
}

.popup input[type="password"]:focus {
    border-color: #2563eb;
    box-shadow: 0 0 4px rgba(37, 99, 235, 0.4);
    outline: none;
}

.popup button {
    width: 100%;
    padding: 12px;
    background-color: #C0C0C0;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 1em;
    font-weight: bold;
    transition: background-color 0.3s;
}
.popup button:hover {
    background-color: #777;
}

#passwordMatchMessage {
    font-size: 0.9em;
    margin-bottom: 10px;
    display: block;
    text-align: center;
    color: #e11d48;
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
</head>
<body>
<!-- 헤더 JSP 포함 -->
<jsp:include page="./views/header.jsp"></jsp:include>

<!-- 비밀번호 찾기 컨테이너 -->
<div class="container">
    <h1>비밀번호 찾기</h1>
    <p>회원 정보를 입력하시면 비밀번호를 찾을 수 있습니다.</p>
    <form onsubmit="handlePasswordRecovery(event)">
        <input type="text" id="userName" name="userName" placeholder="이름을 입력하세요" required>

        <input type="text" id="loginId" name="loginId" placeholder="아이디를 입력하세요" required>

        <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" required>

        <button type="submit">비밀번호 변경</button>
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
        <span id="passwordMatchMessage"></span>
        <button type="submit">비밀번호 변경</button>
    </form>
</div>

<jsp:include page="./views/footer.jsp"></jsp:include>
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

/* 비밀번호 실시간 일치 및 정규식 확인 */
function validatePasswords() {
    const newPassword = document.getElementById("newPassword").value.trim();
    const confirmPassword = document.getElementById("confirmPassword").value.trim();
    const messageSpan = document.getElementById("passwordMatchMessage");

    if (newPassword === "" || confirmPassword === "") {
        messageSpan.textContent = ""; // 입력값이 비어있으면 메시지 제거
        return;
    }

    if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(newPassword)) {
        messageSpan.style.color = "red";
        messageSpan.textContent = "비밀번호는 8~20자, 대소문자, 숫자, 특수문자를 포함해야 합니다.";
        return;
    }

    if (newPassword === confirmPassword) {
        messageSpan.style.color = "green";
        messageSpan.textContent = "비밀번호가 일치합니다."; // 비밀번호 일치
    } else {
        messageSpan.style.color = "red";
        messageSpan.textContent = "비밀번호가 일치하지 않습니다."; // 비밀번호 불일치
    }
}

/* 비밀번호 변경 처리 */
async function handlePasswordUpdate(event) {
    event.preventDefault();

    const newPassword = document.getElementById("newPassword").value.trim();
    const confirmPassword = document.getElementById("confirmPassword").value.trim();

    if (newPassword === "" || confirmPassword === "") {
        alert("비밀번호를 입력해주세요.");
        return;
    }

    if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(newPassword)) {
        alert("비밀번호는 8~20자, 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        return;
    }

    if (newPassword !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    try {
        const response = await fetch("updatePassword.do", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: `newPassword=${encodeURIComponent(newPassword)}`,
        });

        const data = await response.json();
        if (data.status === "success") {
            alert("비밀번호가 성공적으로 변경되었습니다. 로그인 페이지로 이동합니다.");
            closePopup();
            window.location.href = "loginView.jsp";
        } else {
            alert(data.message);
        }
    } catch (error) {
        console.error("오류 발생:", error);
        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
    }
}

/* 팝업 닫기 함수 */
function closePopup() {
    document.querySelector(".overlay").style.display = "none";
    document.querySelector(".popup").style.display = "none";
}
</script>
</body>
</html>
