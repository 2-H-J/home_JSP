<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="css/mypageView.css?v=2">
</head>
<body>
    <!-- 헤더 JSP 포함 -->
    <jsp:include page="./views/header.jsp"></jsp:include>

    <!-- 마이페이지 전체를 감싸는 컨테이너 -->
    <div class="mypage-container">
        <h1>마이페이지</h1>

        <!-- 에러 메시지 표시 -->
        <c:if test="${not empty param.error}">
            <div class="error-message">
                <c:choose>
                    <c:when test="${param.error == 'invalidFile'}">업로드할 수 없는 파일 형식입니다. 이미지 파일만 가능합니다.</c:when>
                    <c:when test="${param.error == 'noFile'}">업로드할 파일을 선택해주세요.</c:when>
                    <c:when test="${param.error == 'serverError'}">서버 오류가 발생했습니다. 다시 시도해주세요.</c:when>
                    <c:otherwise>알 수 없는 오류가 발생했습니다.</c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <!-- 프로필 이미지 섹션 -->
        <div class="profile-image-section">
            <div class="profile-image-container">
                <!-- Base64 인코딩된 이미지 표시 -->
                <c:if test="${not empty profileImageBase64}">
                    <img id="profileImagePreview"
                        src="data:image/png;base64,${profileImageBase64}" 
                        alt="프로필 이미지" 
                        class="profile-image" 
                        onerror="this.src='img/defaultProfile/Default_IMG.png'">
                </c:if>
                <!-- 기본 이미지 표시 -->
                <c:if test="${empty profileImageBase64}">
                    <img id="profileImagePreview"
                        src="img/defaultProfile/Default_IMG.png" 
                        alt="프로필 이미지" 
                        class="profile-image">
                </c:if>
            </div>

            <!-- 버튼 영역 -->
            <div class="profile-buttons">
                <!-- 이미지 선택 버튼 -->
                <label for="profileImageInput" class="file-input-label">프로필 선택</label>

                <!-- 이미지 업로드 폼 -->
                <form id="uploadForm" method="post" action="mypageView.do"
                    enctype="multipart/form-data" style="display: inline;">
                    <input type="hidden" name="action" value="uploadProfile">
                    <input type="file" id="profileImageInput" name="profileImage" accept="image/*" onchange="previewImage(this)">
                    <input type="hidden" name="userNumber" value="${user.userNumber}">
                    <button type="submit" class="profile-register-button">프로필 등록</button>
                </form>

                <!-- 이미지 삭제 폼 -->
                <form method="post" action="mypageView.do" style="display: inline;">
                    <input type="hidden" name="action" value="deleteProfile">
                    <input type="hidden" name="userNumber" value="${user.userNumber}">
                    <button type="submit" class="profile-delete-button">프로필 삭제</button>
                </form>
            </div>
        </div>

        <!-- 사용자 정보 섹션 -->
        <div class="user-info-section">
            <div class="user-info-item">
                이름 <span>${user.userName}</span>
            </div>
            <div class="user-info-item">
                아이디 <span>${user.loginId}</span>
            </div>
            <div class="user-info-item">
                닉네임 <span>${user.nickName}</span>
            </div>
            <div class="user-info-item">
                이메일 <span>${user.userEmail}</span>
            </div>
        </div>

        <!-- 수정 버튼 -->
        <div class="form-buttons">
            <button class="edit-button"
                onclick="location.href='updateUserView.do'">정보 수정</button>
        </div>
    </div>

    <!-- 하단 푸터 JSP 포함 -->
    <jsp:include page="./views/footer.jsp"></jsp:include>

    <!-- JavaScript -->
    <script src="script/mypageView.js"></script>
</body>
</html>
