// window.onload: 웹 페이지가 완전히 로드된 후 실행될 함수를 정의합니다.
window.onload = () => {
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

            // 파일 이름에서 확장자를 추출하고 소문자로 변환
            const fileExtension = file.name.split('.').pop().toLowerCase();

            // 허용되지 않은 확장자인 경우
            if (!allowedExtensions.includes(fileExtension)) {
                alert("이미지 파일만 업로드할 수 있습니다."); // 알림 메시지 표시
                input.value = ""; // 파일 입력값 초기화
                return; // 함수 종료
            }

            // 2. 이미지 파일을 읽어서 미리보기
            const reader = new FileReader(); // FileReader 객체 생성

            // load 이벤트: 파일이 성공적으로 읽힌 후 실행
            reader.onload = function (e) {
                // 이미지 데이터를 미리보기 요소에 적용
                const previewImageElement = document.getElementById("profileImagePreview");
                if (previewImageElement) {
                    previewImageElement.src = e.target.result; // base64 데이터 URL 설정
                }
            };

            // readAsDataURL 메서드로 파일 읽기 시작 (base64 변환)
            reader.readAsDataURL(file);
        }
    }

    // 파일 input 요소를 선택
    const fileInput = document.querySelector('input[name="profileImage"]');

    // 파일 input 요소가 존재하는지 확인 후 이벤트 리스너 추가
    if (fileInput) {
        // change 이벤트: 파일 선택 시 실행
        fileInput.addEventListener("change", function () {
            previewImage(this); // previewImage 함수 호출
        });
    }
};
