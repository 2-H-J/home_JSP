<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="css/mypageView.css?v=2"> <!-- 외부 CSS 연결 -->
</head>
<body>
	<!-- 헤더 JSP 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<!-- header.jsp는 상단 메뉴나 로고 등을 포함할 수 있는 JSP로 -->

	<!-- 마이페이지 전체를 감싸는 컨테이너 -->
	<div class="mypage-container">
		<!-- 페이지 제목 -->
		<h1>마이페이지</h1>

		<!-- 사용자 정보를 보여주는 섹션 -->
		<div class="user-info-section">
			<!-- 사용자 이름 -->
			<div class="user-info-item">
				이름 <span>${user.userName}</span>
				<!-- user 객체에서 userName 속성을 가져와 표시 -->
			</div>
			<!-- 사용자 로그인 아이디 -->
			<div class="user-info-item">
				아이디 <span>${user.loginId}</span>
			</div>
			<!-- 사용자 닉네임 -->
			<div class="user-info-item">
				닉네임 <span>${user.nickName}</span>
			</div>
			<!-- 사용자 이메일 -->
			<div class="user-info-item">
				이메일 <span>${user.userEmail}</span>
			</div>
		</div>

		<!-- 게시물 카테고리별 작성 수를 보여주는 섹션 -->
		<div class="user-posts">
			<h2>내 게시물</h2>
			<!-- 게시물 정보를 보여주는 테이블 -->
			<table class="posts-table">
				<!-- 테이블 헤더 -->
				<thead>
					<tr>
						<th>카테고리</th>
						<th>작성 게시물 수</th>
					</tr>
				</thead>
				<!-- 테이블 내용 -->
				<tbody>
					<!-- categoryCounts 리스트를 반복하면서 각 카테고리 출력 -->
					<c:forEach var="category" items="${categoryCounts}">
						<tr>
							<td>${category.categoryName}</td>
							<!-- 카테고리 이름 -->
							<td>${category.postCount}</td>
							<!-- 게시물 수 -->
						</tr>
					</c:forEach>
					<!-- 게시물이 없는 경우 -->
					<c:if test="${empty categoryCounts}">
						<tr>
							<td colspan="2">작성한 게시물이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>

		<!-- 수정 버튼을 포함한 섹션 -->
		<div class="form-buttons">
			<button class="edit-button" onclick="location.href='updateUserView.do'">정보 수정</button>
			<!-- 'updateUserView.do'로 이동하여 사용자 정보 수정 화면으로 전환 -->
		</div>
	</div>

	<!-- 하단 푸터 JSP 포함 -->
	<jsp:include page="./views/footer.jsp"></jsp:include>
	<!-- footer.jsp는 사이트의 하단 정보를 포함할 수 있음 -->
</body>
</html>