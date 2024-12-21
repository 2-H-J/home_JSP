// 주요 DOM 요소 선택

const emailLocalInput = document.getElementById('emailLocal'); // 이메일 로컬(앞부분) 입력 필드
const emailDomainSelect = document.getElementById('emailDomain'); // 이메일 도메인 선택 드롭다운
const customDomainContainer = document.getElementById('customDomainContainer'); // 사용자 정의 도메인 입력 컨테이너
const customDomainInput = document.getElementById('customDomain'); // 사용자 정의 도메인 입력 필드
const checkEmailBtn = document.getElementById('checkEmailBtn'); // 이메일 중복 확인 버튼

const emailMessage = document.getElementById('emailMessage'); // 이메일 확인 결과 메시지

const nicknameInput = document.getElementById('nickname'); // 닉네임 입력 필드
const checkNicknameBtn = document.getElementById('checkNicknameBtn'); // 닉네임 중복 확인 버튼
const nicknameMessage = document.getElementById('nicknameMessage'); // 닉네임 확인 결과 메시지

const currentPasswordInput = document.getElementById('currentPassword'); // 현재 비밀번호 입력 필드
const newPasswordInput = document.getElementById('newPassword'); // 새 비밀번호 입력 필드
const confirmPasswordInput = document.getElementById('confirmPassword'); // 새 비밀번호 확인 입력 필드
const passwordMatchMessage = document.getElementById('passwordMatchMessage'); // 비밀번호 일치 여부 메시지
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
    if (!nicknameRegex.test(nicknameInput.value)) {
        nicknameMessage.textContent = '닉네임은 2~10자의 영문, 한글, 숫자만 가능합니다.';
        nicknameMessage.className = 'message error active';
        isNicknameChecked = false;
        return;
    }

    if (nicknameInput.value.trim() !== originalNickname) {
        isNicknameChecked = false;
        checkNicknameBtn.style.display = 'inline-block';
        nicknameMessage.textContent = '';
        nicknameMessage.className = 'message';
    } else {
        isNicknameChecked = true;
        checkNicknameBtn.style.display = 'none';
        nicknameMessage.textContent = '';
        nicknameMessage.className = 'message';
    }
});

// ===== 닉네임 중복 확인 버튼 클릭 이벤트 =====
checkNicknameBtn.addEventListener('click', () => {
    const nickname = nicknameInput.value.trim();

    if (!nicknameRegex.test(nickname)) {
        nicknameMessage.textContent = '닉네임은 2~10자의 영문, 한글, 숫자만 가능합니다.';
        nicknameMessage.className = 'message error active';
		//isNicknameChecked = false;
        return;
    }

    fetch(`./checkNickName.do?nickName=${nickname}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                nicknameMessage.textContent = '닉네임이 중복됩니다.';
                nicknameMessage.className = 'message error active';
                isNicknameChecked = false;
            } else {
                nicknameMessage.textContent = '닉네임 사용 가능합니다.';
                nicknameMessage.className = 'message success active';
                isNicknameChecked = true;
            }
        })
        .catch(() => {
            nicknameMessage.textContent = '닉네임 확인 중 문제가 발생했습니다.';
            nicknameMessage.className = 'message error active';
            isNicknameChecked = false;
        });
});

// ===== 이메일 입력 이벤트 =====
const updateEmailState = () => {
    const emailLocal = emailLocalInput.value.trim();
    const emailDomain = customDomainInput.value.trim() || emailDomainSelect.value;
    const email = `${emailLocal}@${emailDomain}`;

    if (!localRegex.test(emailLocal)) {
        emailMessage.textContent = '로컬 부분은 영문 대소문자와 숫자만 입력 가능합니다.';
        emailMessage.className = 'message error active';
        return;
    }
	
	// 로컬 부분 길이 검증
	if (emailLocal.length > 15) {
	    emailMessage.textContent = '로컬 부분은 최대 15자까지 입력 가능합니다.';
	    emailMessage.className = 'message error active';
	    return;
	}

    if (emailDomainSelect.value === 'custom' && !domainRegex.test(emailDomain)) {
        emailMessage.textContent = '도메인 형식이 올바르지 않습니다.';
        emailMessage.className = 'message error active';
        return;
    }
	
    if (email !== originalEmail) {
        isEmailChecked = false;
        checkEmailBtn.style.display = 'inline-block';
        emailMessage.textContent = '';
        emailMessage.className = 'message';
    } else {
        isEmailChecked = true;
        checkEmailBtn.style.display = 'none';
        emailMessage.textContent = '';
        emailMessage.className = 'message';
    }
};
// ===== 이메일 중복 확인 버튼 클릭 이벤트 =====
checkEmailBtn.addEventListener('click', () => {
    const emailLocal = emailLocalInput.value.trim();
    const emailDomain = customDomainInput.value.trim() || emailDomainSelect.value;
    const email = `${emailLocal}@${emailDomain}`;

    // 이메일 유효성 검증
    if (!localRegex.test(emailLocal) || (emailDomainSelect.value === 'custom' && !domainRegex.test(emailDomain))) {
        emailMessage.textContent = '올바른 이메일 형식이 아닙니다.';
        emailMessage.className = 'message error active';
        return;
    }
	// 로컬 부분 길이 검증
	if (emailLocal.length > 15) {
	    emailMessage.textContent = '로컬 부분은 최대 15자까지 입력 가능합니다.';
	    emailMessage.className = 'message error active';
	    return;
	}

    // 서버에 이메일 중복 확인 요청
    fetch(`./checkEmail.do?email=${encodeURIComponent(email)}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                emailMessage.textContent = '이미 사용 중인 이메일입니다.';
                emailMessage.className = 'message error active';
                isEmailChecked = false;
            } else {
                emailMessage.textContent = '사용 가능한 이메일입니다.';
                emailMessage.className = 'message success active';
                isEmailChecked = true;
            }
        })
        .catch(() => {
            emailMessage.textContent = '이메일 확인 중 문제가 발생했습니다.';
            emailMessage.className = 'message error active';
            isEmailChecked = false;
        });
});

// ===== 이메일 이벤트 바인딩 =====
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

// ===== 비밀번호 입력 이벤트 =====
const checkPasswordMatch = () => {
    if (!passwordRegex.test(newPasswordInput.value)) {
        passwordMatchMessage.textContent = '비밀번호는 8~20자의 대소문자, 숫자, 특수문자를 포함해야 합니다.';
        passwordMatchMessage.className = 'message error active';
        return;
    }

    if (newPasswordInput.value === confirmPasswordInput.value) {
        passwordMatchMessage.textContent = '비밀번호가 일치합니다.';
        passwordMatchMessage.className = 'message success active';
    } else {
        passwordMatchMessage.textContent = '비밀번호가 일치하지 않습니다.';
        passwordMatchMessage.className = 'message error active';
    }
};

// ===== 비밀번호 이벤트 바인딩 =====
newPasswordInput.addEventListener('input', checkPasswordMatch);
confirmPasswordInput.addEventListener('input', checkPasswordMatch);

// ===== 폼 제출 =====
document.querySelector('form').addEventListener('submit', (event) => {
    if (!isNicknameChecked) {
        event.preventDefault();
        alert('닉네임 중복 확인을 완료해주세요.');
        return;
    }

    if (!isEmailChecked) {
        event.preventDefault();
        alert('이메일 중복 확인을 완료해주세요.');
        return;
    }

    if (newPasswordInput.value.trim() || confirmPasswordInput.value.trim()) {
        if (!passwordRegex.test(newPasswordInput.value)) {
            event.preventDefault();
            alert('비밀번호는 8~20자의 대소문자, 숫자, 특수문자를 포함해야 합니다.');
            return;
        }
        if (newPasswordInput.value !== confirmPasswordInput.value) {
            event.preventDefault();
            alert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다.');
            return;
        }
    }
});

// ===== 서버에서 비밀번호 오류 메시지 표시 =====
if (passwordErrorMessage && passwordErrorMessage.textContent.trim()) {
    alert(passwordErrorMessage.textContent);
}
