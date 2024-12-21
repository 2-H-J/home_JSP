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
            background-color: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            width: 500px;
            background-color: #fff;
            padding: 80px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-top: 320px;
        }
        h2 {
            margin-bottom: 50px;
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"], input[type="email"] {
            width: calc(100% - 15px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 15px;
        }
        .container .p{
        	font-size: 10px;
        	margin-bottom: 30px;
        }
        button {
            width: calc(100% - 15px);
            padding: 10px;
            background-color: #C0C0C0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #777;
        }
        .overlay {
		    display: none; /* 기본적으로 숨김 */
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background: rgba(0, 0, 0, 0.4); /* 어두운 반투명 배경 */
		    backdrop-filter: blur(5px); /* 배경 흐림 효과 */
		    z-index: 9998;
		}	
		.overlay.active {
   		 	display: block; /* 활성화 시 표시 */
		}
        .popup {
		    display: none; /* 기본적으로 숨김 */
		    position: fixed;
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
		    width: 400px;
		    padding: 20px;
		    border: 1px solid #ccc;
		    background-color: white;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
		    text-align: center;
		    z-index: 9999;
		    border-radius: 5px;
		}
		.popup.active {
    		display: block; /* 활성화 시 표시 */
		}
        .popup button {
            width: 100%;
            padding: 10px;
            background-color: #D3D3D3;
            border: none;
            border-radius: 5px;
            font-size: 20px;
            color: white;
            cursor: pointer;
        }
        .popup button:hover {
            background-color: #C0C0C0;
        }
        #popupMessage {
            font-size: 28px;
            color: #333;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
    
</head>
<body>
	<!-- 헤더 JSP 포함 -->
    <jsp:include page="./views/header.jsp"></jsp:include>
    <div class="container">
        <h2>아이디 찾기</h2>
        <p>회원 정보를 입력하시면 아이디를 찾을 수 있습니다.</p>
        <form id="findLoginIdForm">
            <input type="text" id="userName" name="userName" placeholder="이름을 입력하세요" required>
            <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" required>
            <button type="button" id="findButton">아이디 찾기</button>
        </form>
    </div>

    <div class="overlay" id="overlay">
    <div class="popup" id="popup">
        <p id="popupMessage">찾으신 아이디 br<br></p>
        <button id="loginButton">로그인</button>
    </div>
</div>
    <jsp:include page="./views/footer.jsp"></jsp:include>
    <!-- footer.jsp 포함 -->
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
                url: './findLoginId.do',
                type: 'POST',
                data: {
                    userName: userName,
                    userEmail: userEmail
                },
                success: function (response) {
                    if (response && response.loginId) {
                        $('#popupMessage').text(`찾으신 아이디 : ` + response.loginId);
                    } else {
                        $('#popupMessage').text('아이디를 찾을 수 없습니다.');
                    }
                    // 팝업창과 오버레이 활성화
                    $('#overlay').addClass('active');
                    $('#popup').addClass('active');
                },
                error: function () {
                    alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
                }
            });
        });

        $('#loginButton').on('click', function () {
            // 팝업창과 오버레이 비활성화
            $('#overlay').removeClass('active');
            $('#popup').removeClass('active');
            window.location.href = './loginView.jsp'; // 로그인 페이지로 이동
        });
    });

    </script>
</body>
</html>
