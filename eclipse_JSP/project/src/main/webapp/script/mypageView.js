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
            // 허용된 이미지 확장자 목록
            const allowedExtensions = ["jpg", "jpeg", "png", "bmp"];

            // 파일 이름에서 확장자 추출
            const fileExtension = file.name.split('.').pop().toLowerCase();

            // 확장자가 허용되지 않은 경우 알림 표시 및 파일 입력값 초기화
            if (!allowedExtensions.includes(fileExtension)) {
                alert("이미지 파일만 업로드할 수 있습니다."); // 알림 메시지 표시
                input.value = ""; // 파일 입력값을 초기화
                return; // 함수 종료
            }

            // 2. 이미지 파일을 읽어서 미리보기
            const reader = new FileReader(); // FileReader 객체를 생성합니다.

            // load 이벤트: 파일이 성공적으로 읽힌 후 실행됩니다.
            reader.onload = function (e) {
                // 이미지 파일의 데이터를 미리보기 이미지 요소에 적용합니다.
                // e.target.result에는 파일의 데이터 URL이 담깁니다.
                document.getElementById("profileImagePreview").src = e.target.result;
            };

            // readAsDataURL 메서드: 파일을 읽어 base64 형식의 데이터 URL로 변환합니다.
            reader.readAsDataURL(file);
        }
    }

    // 파일 input 요소를 선택합니다.
    const fileInput = document.querySelector('input[name="profileImage"]');
    
    // input 요소가 존재하는지 확인 후 이벤트 리스너 추가
    if (fileInput) {
        // change 이벤트: 파일 input 값이 변경될 때 실행됩니다.
        fileInput.addEventListener("change", function () {
            // previewImage 함수를 호출하여 선택된 파일 처리
            previewImage(this); // this는 이벤트가 발생한 input 요소입니다.
        });
    }
};
