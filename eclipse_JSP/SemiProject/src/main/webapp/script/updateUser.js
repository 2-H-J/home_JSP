window.onload = () => {
    // 주요 DOM 요소 선택
    const emailDomainSelect = document.getElementById('emailDomain'); // 이메일 도메인 선택 드롭다운
    const customDomainContainer = document.getElementById('customDomainContainer'); // 사용자 정의 도메인 입력 컨테이너
    const customDomainInput = document.getElementById('customDomain'); // 사용자 정의 도메인 입력 필드
    const nicknameInput = document.getElementById('nickname'); // 닉네임 입력 필드
    const emailLocalInput = document.getElementById('emailLocal'); // 이메일 로컬(앞부분) 입력 필드
    const checkNicknameBtn = document.getElementById('checkNicknameBtn'); // 닉네임 중복 확인 버튼
    const checkEmailBtn = document.getElementById('checkEmailBtn'); // 이메일 중복 확인 버튼
    const nicknameMessage = document.getElementById('nicknameMessage'); // 닉네임 확인 결과 메시지
    const emailMessage = document.getElementById('emailMessage'); // 이메일 확인 결과 메시지
    const passwordMatchMessage = document.getElementById('passwordMatchMessage'); // 비밀번호 일치 여부 메시지
    const currentPasswordInput = document.getElementById('currentPassword'); // 현재 비밀번호 입력 필드
    const newPasswordInput = document.getElementById('newPassword'); // 새 비밀번호 입력 필드
    const confirmPasswordInput = document.getElementById('confirmPassword'); // 새 비밀번호 확인 입력 필드
    const passwordErrorMessage = document.getElementById('passwordErrorMessage'); // 비밀번호 오류 메시지

    // 원래 닉네임과 이메일 값
    const originalNickname = document.getElementById('originalNickname').value.trim();
    const originalEmail = document.getElementById('originalEmail').value.trim();

    // 닉네임과 이메일 중복 확인 상태 플래그
    let isNicknameChecked = true;
    let isEmailChecked = true;

    // 정규식 정의
    const localRegex = /^[a-zA-Z0-9]+$/; // 이메일 로컬(앞부분): 영문 대소문자, 숫자만 허용
    const domainRegex = /^[a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/; // 이메일 도메인: 올바른 형식 확인
    const nicknameRegex = /^[a-zA-Z가-힣0-9]{2,10}$/; // 닉네임: 2~10자의 영문, 한글, 숫자만 허용
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/; // 비밀번호: 대소문자, 숫자, 특수문자 포함 8~20자

    // ===== 닉네임 입력 이벤트 =====
    nicknameInput.addEventListener('input', () => {
        // 닉네임 형식 검증
        if (!nicknameRegex.test(nicknameInput.value)) {
            nicknameMessage.textContent = '닉네임은 2~10자의 영문, 한글, 숫자만 가능합니다.';
            nicknameMessage.className = 'message error';
            isNicknameChecked = false;
            return;
        }

        // 닉네임이 변경되었는지 확인
        if (nicknameInput.value.trim() !== originalNickname) {
            isNicknameChecked = false;
            checkNicknameBtn.style.display = 'inline-block'; // 중복 확인 버튼 표시
            nicknameMessage.textContent = '';
        } else {
            isNicknameChecked = true;
            checkNicknameBtn.style.display = 'none'; // 변경되지 않았으므로 버튼 숨김
        }
    });

    // 닉네임 중복 확인 버튼 클릭 이벤트
    checkNicknameBtn.addEventListener('click', () => {
        const nickname = nicknameInput.value.trim();
        fetch(`./checkNickName.do?nickName=${nickname}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    nicknameMessage.textContent = '닉네임이 중복됩니다.';
                    nicknameMessage.className = 'message error';
                    isNicknameChecked = false;
                } else {
                    nicknameMessage.textContent = '닉네임 사용 가능합니다.';
                    nicknameMessage.className = 'message success';
                    isNicknameChecked = true;
                }
            })
            .catch(() => {
                nicknameMessage.textContent = '닉네임 확인 중 문제가 발생했습니다.';
                nicknameMessage.className = 'message error';
                isNicknameChecked = false;
            });
    });

    // ===== 이메일 입력 이벤트 =====
    const updateEmailState = () => {
        const emailLocal = emailLocalInput.value.trim();
        const emailDomain = customDomainInput.value.trim() || emailDomainSelect.value;
        const email = `${emailLocal}@${emailDomain}`;

        // 이메일 로컬 및 도메인 형식 검증
        if (!localRegex.test(emailLocal)) {
            emailMessage.textContent = '로컬 부분은 영문 대소문자와 숫자만 입력 가능합니다.';
            emailMessage.className = 'message error';
            return;
        }

        if (emailDomainSelect.value === 'custom' && !domainRegex.test(emailDomain)) {
            emailMessage.textContent = '도메인 형식이 올바르지 않습니다.';
            emailMessage.className = 'message error';
            return;
        }

        // 중복 확인 버튼 표시/숨김
        if (email !== originalEmail) {
            isEmailChecked = false;
            checkEmailBtn.style.display = 'inline-block';
            emailMessage.textContent = '';
        } else {
            isEmailChecked = true;
            checkEmailBtn.style.display = 'none';
            emailMessage.textContent = '';
        }
    };

    // 이메일 입력 필드와 사용자 정의 도메인 필드에 이벤트 리스너 추가
    [emailLocalInput, customDomainInput].forEach(input => {
        input.addEventListener('input', updateEmailState);
    });

    // 이메일 도메인 드롭다운 변경 이벤트
    emailDomainSelect.addEventListener('change', () => {
        if (emailDomainSelect.value === 'custom') {
            customDomainContainer.style.display = 'block';
        } else {
            customDomainContainer.style.display = 'none';
            customDomainInput.value = ''; // 사용자 정의 도메인 초기화
        }
        updateEmailState();
    });

    // 이메일 중복 확인 버튼 클릭 이벤트
    checkEmailBtn.addEventListener('click', () => {
        const emailLocal = emailLocalInput.value.trim();
        const emailDomain = customDomainInput.value.trim() || emailDomainSelect.value;
        const email = `${emailLocal}@${emailDomain}`;

        fetch(`./checkEmail.do?email=${encodeURIComponent(email)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    emailMessage.textContent = '이메일이 중복됩니다.';
                    emailMessage.className = 'message error';
                    isEmailChecked = false;
                } else {
                    emailMessage.textContent = '사용 가능한 이메일입니다.';
                    emailMessage.className = 'message success';
                    isEmailChecked = true;
                }
            })
            .catch(() => {
                emailMessage.textContent = '이메일 확인 중 문제가 발생했습니다.';
                emailMessage.className = 'message error';
                isEmailChecked = false;
            });
    });

    // ===== 비밀번호 입력 이벤트 =====
    const checkPasswordMatch = () => {
        // 비밀번호 형식 검증
        if (!passwordRegex.test(newPasswordInput.value)) {
            passwordMatchMessage.textContent = '비밀번호는 8~20자의 대소문자, 숫자, 특수문자를 포함해야 합니다.';
            passwordMatchMessage.className = 'message error';
            return;
        }

        // 비밀번호 확인 (일치 여부)
        if (newPasswordInput.value === confirmPasswordInput.value) {
            passwordMatchMessage.textContent = '비밀번호가 일치합니다.';
            passwordMatchMessage.className = 'message success';
        } else {
            passwordMatchMessage.textContent = '비밀번호가 일치하지 않습니다.';
            passwordMatchMessage.className = 'message error';
        }
    };

    // 비밀번호 입력 필드에 이벤트 리스너 추가
    newPasswordInput.addEventListener('input', checkPasswordMatch);
    confirmPasswordInput.addEventListener('input', checkPasswordMatch);

    // ===== 폼 제출 시 확인 =====
    document.querySelector('form').addEventListener('submit', (event) => {
        if (!isNicknameChecked) {
            event.preventDefault();
            alert('닉네임 중복 확인을 완료해주세요.');
        }
        if (!isEmailChecked) {
            event.preventDefault();
            alert('이메일 중복 확인을 완료해주세요.');
        }
        if (newPasswordInput.value && newPasswordInput.value !== confirmPasswordInput.value) {
            event.preventDefault();
            alert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다.');
        }
    });

    // ===== 서버에서 비밀번호 오류 메시지 표시 =====
    if (passwordErrorMessage.textContent.trim()) {
        alert(passwordErrorMessage.textContent);
    }
};
