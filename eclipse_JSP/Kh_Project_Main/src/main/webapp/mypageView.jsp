<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

		<!-- 프로필 이미지 -->
		<div class="profile-image-section">
			<img id="profileImagePreview"
				src="profileImage?image=${user.profileImageUrl}" alt="프로필 이미지"
				class="profile-image"
				onerror="this.src='img/defaultProfile/Default_IMG.png'">
		</div>
		<c:if test="${not empty errorMessage}">
			<div class="error-message">${errorMessage}</div>
		</c:if>

		<!-- 프로필 버튼 -->
		<div class="profile-buttons">
			<label for="profileImageInput" class="file-input-label">프로필
				선택</label>
			<form id="uploadForm" method="post" action="profileImage"
				enctype="multipart/form-data">
				<input type="file" id="profileImageInput" name="profileImage"
					accept="image/*" onchange="previewImage(this)"> <input
					type="hidden" name="userNumber" value="${user.userNumber}">
				<button type="submit">프로필 등록</button>
			</form>
			<form method="post" action="profileImage">
				<input type="hidden" name="action" value="delete"> <input
					type="hidden" name="userNumber" value="${user.userNumber}">
				<button type="submit">프로필 삭제</button>
			</form>
		</div>

		<!-- 사용자 정보 -->
		<div class="user-info-section">
			<div class="user-info-item">
				이름 :<span>${user.userName}</span>
			</div>
			<div class="user-info-item">
				아이디 :<span>${user.loginId}</span>
			</div>
			<div class="user-info-item">
				닉네임 :<span>${user.nickName}</span>
			</div>
			<div class="user-info-item">
				이메일 :<span>${user.userEmail}</span>
			</div>
		</div>

		<!-- 정보 수정 버튼 -->
		<div class="form-buttons">
			<button class="edit-button"
				onclick="location.href='updateUserView.do'">정보 수정</button>
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
