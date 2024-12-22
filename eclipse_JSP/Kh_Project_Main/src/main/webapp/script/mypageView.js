// window.onload: 웹 페이지가 완전히 로드된 후 실행될 함수를 정의합니다.
    /**
     * 이미지 파일 미리보기 기능
     * @param {HTMLInputElement} input - 파일 선택 input 요소
     */
    function previewImage(input) {
        // input에서 선택된 파일을 가져옵니다.
        const file = input.files[0]; // input의 files 배열에서 첫 번째 파일만 사용합니다.

        // 사용자가 파일을 선택했을 때만 실행합니다.
        if (file) {
            // 1. 확장자 검증 (파일 타입 확인)
            const allowedExtensions = ["jpg", "jpeg", "png", "bmp", "webp"]; // 허용된 이미지 확장자 목록
            const fileExtension = file.name.split('.').pop().toLowerCase(); // 파일 이름에서 확장자 추출

            if (!allowedExtensions.includes(fileExtension)) {
                alert("이미지 파일만 업로드할 수 있습니다."); // 허용되지 않은 확장자 메시지
                input.value = ""; // 파일 입력값 초기화
                return;
            }

            // 2. 이미지 파일을 읽어서 미리보기
            const reader = new FileReader(); // FileReader 객체 생성
            reader.onload = function (e) {
                // 이미지 파일의 데이터를 미리보기 이미지 요소에 적용
                document.getElementById("profileImagePreview").src = e.target.result;
            };
            reader.readAsDataURL(file); // 파일을 base64 형식으로 읽음
        }
    }

    /**
     * 파일 업로드 시 파일이 선택되지 않은 경우 경고
     * @returns {boolean} 파일이 선택되지 않았다면 false 반환
     */
    function validateFileSelection() {
        const fileInput = document.querySelector('input[name="profileImage"]');
        if (!fileInput || !fileInput.value) {
            alert("파일을 선택해주세요."); // 파일 선택을 요청하는 알림
            return false;
        }
        return true;
    }

    // 파일 input 요소를 선택합니다.
    const fileInput = document.querySelector('input[name="profileImage"]');
    const uploadForm = document.getElementById("uploadForm");

    // 파일 input 요소 이벤트 리스너 추가
    if (fileInput) {
        fileInput.addEventListener("change", function () {
            previewImage(this); // 파일 선택 시 미리보기 업데이트
        });
    }

    // 프로필 등록 버튼 클릭 시 파일 검증
    if (uploadForm) {
        uploadForm.addEventListener("submit", function (event) {
            if (!validateFileSelection()) {
                event.preventDefault(); // 파일이 선택되지 않았다면 폼 제출 중단
            }
        });
    }
