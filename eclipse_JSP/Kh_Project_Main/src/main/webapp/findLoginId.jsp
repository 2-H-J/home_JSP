<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/findLoginId.css">
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
