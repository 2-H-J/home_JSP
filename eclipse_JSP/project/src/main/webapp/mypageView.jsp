<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
    JSP 페이지 지시어:
    - page: JSP 페이지의 설정(언어, 컨텐츠 타입, 인코딩) 정의.
    - taglib: JSTL Core 태그 라이브러리 선언.
-->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link rel="stylesheet" type="text/css" href="css/mypageView.css?v=2">
    <!-- 
        스타일시트 링크:
        - mypageView.css: 이 페이지 전용 CSS 파일을 로드합니다.
        - ?v=2: 캐시를 무효화하기 위한 버전 파라미터를 추가했습니다.
    -->
</head>
<body>
    <!-- 헤더 JSP 포함 -->
    <jsp:include page="./views/header.jsp"></jsp:include>
    <!-- 
        공통 헤더 파일 삽입:
        - header.jsp 파일을 불러와 상단 영역을 표시합니다.
        - 코드 중복을 피하고 일관된 헤더를 유지하기 위해 사용됩니다.
    -->

    <!-- 마이페이지 전체를 감싸는 컨테이너 -->
    <div class="mypage-container">
        <h1>마이페이지</h1>
        <!-- 페이지 제목: 사용자 정보를 확인하고 수정할 수 있는 페이지입니다. -->

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
        <!-- 
            에러 메시지 처리:
            - URL 파라미터에서 전달된 `param.error` 값을 검사합니다.
            - 각 에러 상황에 맞는 메시지를 사용자에게 보여줍니다.
        -->

        <!-- 프로필 이미지 섹션 -->
        <div class="profile-image-section">
            <!-- 프로필 이미지 표시 -->
            <div class="profile-image-container">
                <img id="profileImagePreview" src="profileImage?image=${user.profileImageUrl}" alt="프로필 이미지"
                     class="profile-image" onerror="this.src='img/defaultProfile/Default_IMG.png'">
                <!-- 
                    프로필 이미지:
                    - src: 서버에 저장된 사용자 프로필 이미지를 불러옵니다.
                    - onerror: 이미지가 없거나 오류가 발생하면 기본 이미지(Default_IMG.png)를 표시합니다.
                -->
            </div>

            <!-- 버튼 영역 -->
            <div class="profile-buttons">
                <!-- 이미지 선택 버튼 -->
                <label for="profileImageInput" class="file-input-label">프로필 선택</label>

                <!-- 이미지 업로드 폼 -->
                <form id="uploadForm" method="post" action="profileImage"
                      enctype="multipart/form-data" style="display: inline;">
                    <input type="file" id="profileImageInput" name="profileImage"
                           accept="image/*" onchange="previewImage(this)">
                    <input type="hidden" name="userNumber" value="${user.userNumber}">
                    <button type="submit" class="profile-register-button">프로필 등록</button>
                    <!-- 
                        프로필 이미지 업로드:
                        - 파일 선택 시 미리보기를 보여주기 위해 `onchange` 이벤트에 previewImage 함수를 연결합니다.
                        - POST 요청으로 서버에 파일을 전송합니다.
                    -->
                </form>

                <!-- 이미지 삭제 폼 -->
                <form method="post" action="profileImage" style="display: inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="userNumber" value="${user.userNumber}">
                    <button type="submit" class="profile-delete-button">프로필 삭제</button>
                    <!-- 
                        프로필 이미지 삭제:
                        - hidden 필드에 `action=delete` 값을 전달해 삭제 동작을 지정합니다.
                        - 서버에서 기존 이미지를 삭제하고 기본 이미지로 설정합니다.
                    -->
                </form>
            </div>
        </div>

        <!-- 사용자 정보 섹션 -->
        <div class="user-info-section">
            <div class="user-info-item">
                이름 <span>${user.userName}</span>
                <!-- 사용자 이름 표시 -->
            </div>
            <div class="user-info-item">
                아이디 <span>${user.loginId}</span>
                <!-- 사용자 아이디 표시 -->
            </div>
            <div class="user-info-item">
                닉네임 <span>${user.nickName}</span>
                <!-- 사용자 닉네임 표시 -->
            </div>
            <div class="user-info-item">
                이메일 <span>${user.userEmail}</span>
                <!-- 사용자 이메일 표시 -->
            </div>
        </div>

        <!-- 수정 버튼 -->
        <div class="form-buttons">
            <button class="edit-button" onclick="location.href='updateUserView.do'">정보 수정</button>
            <!-- 
                정보 수정 버튼:
                - 클릭 시 `updateUserView.do`로 이동하여 사용자 정보를 수정할 수 있습니다.
            -->
        </div>
    </div>

    <!-- 하단 푸터 JSP 포함 -->
    <jsp:include page="./views/footer.jsp"></jsp:include>
    <!-- 
        공통 푸터 파일 삽입:
        - footer.jsp 파일을 불러와 하단 영역을 표시합니다.
        - 코드 중복을 줄이고 페이지 일관성을 유지합니다.
    -->

    <!-- JavaScript -->
    <script src="script/mypageView.js"></script>
    <!-- 
        JavaScript 파일:
        - script/mypageView.js: 이미지 미리보기 기능 등을 처리합니다.
    -->
</body>
</html>
