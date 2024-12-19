<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 50%;
            margin: 0 auto;
            text-align: center;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], input[type="email"] {
            width: 80%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            z-index: 9999;
            border-radius: 5px;
        }
        .popup button {
            margin-top: 10px;
            width: 100px;
            background-color: #28a745;
        }
        #popupMessage {
               color: black; /* 텍스트 색상 /
            font-size: 16px; / 텍스트 크기 /
                display: block; / 요소가 보이도록 설정 /
            visibility: visible; / 숨겨진 요소를 표시 */
        }
    </style>
    <script>
        $(document).ready(function () {
            $('#findButton').on('click', function () {
                const userName = $('#userName').val();
                const userEmail = $('#userEmail').val();

                if (!userName || !userEmail) {
                    alert('모든 필드를 입력해주세요.');
                    return;
                }

                $.ajax({
                    url: './findLoginId.do', // 서버 URL
                    type: 'POST', // HTTP 메서드
                    data: {
                        userName: $('#userName').val(), // 입력된 사용자 이름
                        userEmail: $('#userEmail').val() // 입력된 이메일
                    },
                    success: function (response) {

                        if (response && response.loginId) {
                            // 찾으신 아이디 뒤에 값 삽입
                        console.log("서버 응답:", response); // 서버 응답 확인
                        let loginId = response.loginId;
                        console.log("받은 loginId:", loginId); // 서버에서 받은 loginId 확인

                            $('#popupMessage').text(`찾으신 아이디:` + loginId);
                            $('#popup').fadeIn(); // 팝업창 표시
                        } else {
                            // 에러 메시지 표시
                            $('#popup').fadeIn();
                        }
                    },
                    error: function () {
                        alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
                    }
                });

            });

            $('#loginButton').on('click', function () {
                window.location.href = './loginView.jsp'; // 로그인 페이지로 이동
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <h2>아이디 찾기</h2>
        <form id="findLoginIdForm">
            <input type="text" id="userName" name="userName" placeholder="이름을 입력하세요" required>
            <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" required>
            <button type="button" id="findButton">아이디 찾기</button>
        </form>
    </div>

    <!-- 팝업창 -->
    <div class="popup" id="popup">
        <p id="popupMessage">찾으신 아이디:</p>
        <button id="loginButton">로그인</button>
    </div>


</body>
</html>