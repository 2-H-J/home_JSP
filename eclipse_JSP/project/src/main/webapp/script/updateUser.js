window.onload = () => {
    // 주요 DOM 요소 선택
    const emailDomainSelect = document.getElementById('emailDomain');
    const customDomainContainer = document.getElementById('customDomainContainer');
    const customDomainInput = document.getElementById('customDomain');
    const nicknameInput = document.getElementById('nickname');
    const emailLocalInput = document.getElementById('emailLocal');
    const checkNicknameBtn = document.getElementById('checkNicknameBtn');
    const checkEmailBtn = document.getElementById('checkEmailBtn');
    const nicknameMessage = document.getElementById('nicknameMessage');
    const emailMessage = document.getElementById('emailMessage');
    const passwordMatchMessage = document.getElementById('passwordMatchMessage');
    const currentPasswordInput = document.getElementById('currentPassword');
    const newPasswordInput = document.getElementById('newPassword');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const passwordErrorMessage = document.getElementById('passwordErrorMessage');

    const originalNickname = document.getElementById('originalNickname').value.trim();
    const originalEmail = document.getElementById('originalEmail').value.trim();

    let isNicknameChecked = true;
    let isEmailChecked = true;

    // 정규식 정의
    const localRegex = /^[a-zA-Z0-9]+$/;
    const domainRegex = /^[a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/;
    const nicknameRegex = /^[a-zA-Z가-힣0-9]{2,10}$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;

    // 닉네임 입력 이벤트
    nicknameInput.addEventListener('input', () => {
        if (!nicknameRegex.test(nicknameInput.value)) {
            nicknameMessage.textContent = '닉네임은 2~10자의 영문, 한글, 숫자만 가능합니다.';
            nicknameMessage.className = 'message error';
            isNicknameChecked = false;
            return;
        }

        if (nicknameInput.value.trim() !== originalNickname) {
            isNicknameChecked = false;
            checkNicknameBtn.style.display = 'inline-block';
            nicknameMessage.textContent = '';
        } else {
            isNicknameChecked = true;
            checkNicknameBtn.style.display = 'none';
        }
    });

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

    const updateEmailState = () => {
        const emailLocal = emailLocalInput.value.trim();
        const emailDomain = customDomainInput.value.trim() || emailDomainSelect.value;
        const email = `${emailLocal}@${emailDomain}`;

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

    [emailLocalInput, customDomainInput].forEach(input => {
        input.addEventListener('input', updateEmailState);
    });

    emailDomainSelect.addEventListener('change', () => {
        if (emailDomainSelect.value === 'custom') {
            customDomainContainer.style.display = 'block';
        } else {
            customDomainContainer.style.display = 'none';
            customDomainInput.value = '';
        }
        updateEmailState();
    });

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

    const checkPasswordMatch = () => {
        if (!passwordRegex.test(newPasswordInput.value)) {
            passwordMatchMessage.textContent = '비밀번호는 8~20자의 대소문자, 숫자, 특수문자를 포함해야 합니다.';
            passwordMatchMessage.className = 'message error';
            return;
        }

        if (newPasswordInput.value === confirmPasswordInput.value) {
            passwordMatchMessage.textContent = '비밀번호가 일치합니다.';
            passwordMatchMessage.className = 'message success';
        } else {
            passwordMatchMessage.textContent = '비밀번호가 일치하지 않습니다.';
            passwordMatchMessage.className = 'message error';
        }
    };

    newPasswordInput.addEventListener('input', checkPasswordMatch);
    confirmPasswordInput.addEventListener('input', checkPasswordMatch);

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
        if (!passwordRegex.test(newPasswordInput.value)) {
            event.preventDefault();
            alert('비밀번호 형식이 잘못되었습니다.');
        }
    });

    if (passwordErrorMessage && passwordErrorMessage.textContent.trim()) {
        alert(passwordErrorMessage.textContent);
    }
};
