window.onload = () => {
    // DOM 요소 참조
    const emailDomainSelect = document.getElementById('emailDomain');
    const customDomainField = document.querySelector('.custom-domain');
    const passwordEditButton = document.querySelector('.password-edit');
    const passwordEditFields = document.querySelector('.password-edit-fields');
    const nicknameCheckButton = document.querySelector('.nickname-check');
    const emailCheckButton = document.getElementById('checkEmailBtn');
    const nicknameInput = document.getElementById('nickname');
    const nicknameMessage = document.getElementById('nicknameMessage');
    const emailMessage = document.getElementById('emailMessage');

    // 이메일 도메인 선택 변경 시 동작
    emailDomainSelect.addEventListener('change', () => {
        if (emailDomainSelect.value === 'custom') {
            customDomainField.style.display = 'block'; // 직접 입력 필드 보이기
        } else {
            customDomainField.style.display = 'none'; // 직접 입력 필드 숨기기
        }
    });

    // 비밀번호 수정 버튼 클릭 시 추가 입력 필드 표시
    passwordEditButton.addEventListener('click', () => {
        passwordEditFields.style.display = 'block'; // 비밀번호 수정 필드 보이기
        passwordEditButton.style.display = 'none'; // 수정 버튼 숨기기
    });

    // 닉네임 중복 확인 버튼 클릭 시 동작
    nicknameCheckButton.addEventListener('click', () => {
        const nickname = nicknameInput.value.trim(); // 닉네임 입력값 가져오기
        if (!nickname) {
            nicknameMessage.textContent = '닉네임을 입력해주세요.';
            nicknameMessage.style.color = 'red';
            return;
        }

        // 서버로 닉네임 중복 확인 요청
        fetch(`./checkNickName.do?nickName=${nickname}`)
            .then((response) => response.json())
            .then((data) => {
                if (data.exists) {
                    nicknameMessage.textContent = '중복되는 닉네임입니다.';
                    nicknameMessage.style.color = 'red';
                } else {
                    nicknameMessage.textContent = '사용 가능한 닉네임입니다.';
                    nicknameMessage.style.color = 'green';
                }
            })
            .catch(() => {
                nicknameMessage.textContent = '중복 확인 중 오류가 발생했습니다.';
                nicknameMessage.style.color = 'red';
            });
    });

	// 이메일 중복 확인 버튼 클릭 시 동작
	emailCheckButton.addEventListener('click', () => {
	    const emailLocal = document.getElementById('emailLocal').value.trim(); // 이메일 로컬 부분
	    const emailDomain = emailDomainSelect.value === 'custom'
	        ? document.querySelector('input[name="customDomain"]').value.trim() // 직접 입력 도메인
	        : emailDomainSelect.value; // 선택한 도메인

	    if (!emailLocal || !emailDomain) {
	        emailMessage.textContent = '이메일을 완전히 입력해주세요.';
	        emailMessage.style.color = 'red';
	        return;
	    }

	    const fullEmail = `${emailLocal}@${emailDomain}`; // 이메일 완성

	    // 서버로 이메일 중복 확인 요청
	    fetch(`./checkEmail.do?email=${encodeURIComponent(fullEmail)}`)
	        .then((response) => {
	            if (!response.ok) {
	                throw new Error('네트워크 응답에 문제가 있습니다.');
	            }
	            return response.json();
	        })
	        .then((data) => {
	            if (data.exists) {
	                emailMessage.textContent = '중복되는 이메일입니다.';
	                emailMessage.style.color = 'red';
	            } else {
	                emailMessage.textContent = '사용 가능한 이메일입니다.';
	                emailMessage.style.color = 'green';
	            }
	        })
	        .catch((error) => {
	            console.error("에러 발생:", error); // 디버깅 로그
	            emailMessage.textContent = '중복 확인 중 오류가 발생했습니다.';
	            emailMessage.style.color = 'red';
	        });
	});

};
