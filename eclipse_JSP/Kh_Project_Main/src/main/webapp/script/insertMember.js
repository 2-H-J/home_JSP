/**
 * 
 */
	function validateForm() {
		const loginId = document.getElementById("loginId").value;
		const password = document.getElementById("password").value;
		const confirmPassword = document.getElementById("confirmPassword").value;
		const userEmail = document.getElementById("userEmail").value;
		const userName = document.getElementById("userName").value;
		let message = "";

		if (!loginId.match(/^[a-zA-Z0-9]{3,15}$/)) {
			message += "아이디는 3~20자의 영문 대소문자와 숫자만 가능합니다.\n";
		}

		if (!password.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/)) {
			message += "비밀번호는 8~20자이며, 대소문자, 숫자, 특수문자(@$!%*?&)를 포함해야 합니다.\n";
		}

		if (password !== confirmPassword) {
			message += "비밀번호와 확인 비밀번호가 일치하지 않습니다.\n";
		}

		if (!userEmail.match(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)) {
			message += "이메일 형식이 올바르지 않습니다.\n";
		}

		if (!userName.match(/^[가-힣a-zA-Z]{2,}$/)) {
			message += "이름은 한글 또는 영문으로 2~20자여야 합니다.\n";
		}

		if (message) {
			alert(message);
			return false;
		}

		return true;
	}

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
