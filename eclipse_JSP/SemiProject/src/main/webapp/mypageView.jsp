<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="css/myPageView.css">
<script src="js/myPageView.js"></script>
</head>
<body>
    <!-- 헤더 JSP 포함 -->
    <jsp:include page="./views/header.jsp"></jsp:include>

    <div class="mypage-container">
        <h1>내정보 [MyPage]</h1>
        <form action="./myPage.do" method="post">
            <!-- 아이디: 읽기 전용 -->
            <label> 
                아이디 
                <input type="text" name="loginId" value="${user.loginId}" readonly> 
            </label> 

            <!-- 이름: 읽기 전용 -->
            <label> 
                이름 
                <input type="text" name="userName" value="${user.userName}" readonly> 
            </label> 

            <!-- 닉네임 -->
            <label> 
                닉네임
                <div class="nickname-row">
                    <input type="text" id="nickname" name="nickName" value="${user.nickName}">
                    <button type="button" id="checkNicknameBtn" class="nickname-check">중복 확인</button>
                </div>
                <p id="nicknameMessage" class="message"></p> <!-- 닉네임 중복 확인 메시지 -->
            </label>

            <!-- 비밀번호 -->
            <label> 
                비밀번호
                <div class="password-row">
                    <input type="password" name="password" placeholder="********" readonly>
                    <button type="button" class="password-edit">수정</button>
                </div>
                <div class="password-edit-fields" style="display: none;">
                    <input type="password" name="currentPassword" placeholder="현재 비밀번호">
                    <input type="password" name="newPassword" placeholder="새로운 비밀번호">
                    <input type="password" name="confirmNewPassword" placeholder="새로운 비밀번호 확인">
                    <p id="passwordMessage" class="message"></p> <!-- 비밀번호 확인 메시지 -->
                </div>
            </label>

            <!-- 이메일 -->
            <label> 
                이메일
                <div class="email-row">
                    <!-- 이메일 로컬 파트 -->
                    <input type="text" id="emailLocal" name="emailLocal" value="${user.emailLocal}">
                    <span class="email-at">@</span>

                    <!-- 이메일 도메인 선택 -->
                    <select name="emailDomain" id="emailDomain">
                        <option value="gmail.com" ${user.emailDomain == 'gmail.com' ? 'selected' : ''}>gmail.com</option>
                        <option value="naver.com" ${user.emailDomain == 'naver.com' ? 'selected' : ''}>naver.com</option>
                        <option value="daum.net" ${user.emailDomain == 'daum.net' ? 'selected' : ''}>daum.net</option>
                        <option value="yahoo.com" ${user.emailDomain == 'yahoo.com' ? 'selected' : ''}>yahoo.com</option>
                        <option value="nate.com" ${user.emailDomain == 'nate.com' ? 'selected' : ''}>nate.com</option>
                        <option value="icloud.com" ${user.emailDomain == 'icloud.com' ? 'selected' : ''}>icloud.com</option>
                        <option value="custom" ${user.emailDomain == 'custom' ? 'selected' : ''}>직접입력</option>
                    </select>

                    <!-- 이메일 중복 확인 버튼 -->
                    <button type="button" id="checkEmailBtn" class="email-check">중복 확인</button>
                </div>

                <!-- 이메일 중복 확인 결과 메시지 -->
                <p id="emailMessage" class="message"></p>
                <div class="custom-domain" style="display: none;">
                    <input type="text" name="customDomain" placeholder="도메인 입력">
                </div>
            </label>

            <!-- 폼 버튼 -->
            <div class="form-buttons">
                <button type="submit">수정</button>
                <button type="button" class="form-cancel">취소</button>
            </div>
        </form>
    </div>

    <jsp:include page="./views/footer.jsp"></jsp:include>
    <script src="./script/myPageView.js"></script>
</body>
</html>
