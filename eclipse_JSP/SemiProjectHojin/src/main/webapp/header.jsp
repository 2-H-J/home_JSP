<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>

<script>
window.onload = () => {
    // 모든 메뉴 항목을 선택
    const menuItems = document.querySelectorAll('.menu_bar li');
    const currentUrl = window.location.href; // 현재 URL

    // 페이지 로드 시 URL과 일치하는 메뉴에 active 클래스를 추가
    menuItems.forEach(item => {
        const link = item.querySelector('a');
        // 메뉴 링크의 href 값이 현재 URL과 일치하면 active 클래스를 추가
        if (currentUrl.includes(link.href)) {
            item.classList.add('active'); // 현재 페이지의 메뉴 항목에 active 클래스 추가
        }

        // 마우스를 올리면 글씨 크기와 굵기 증가
        item.addEventListener('mouseenter', () => {
            if (!item.classList.contains('active')) { // active 클래스가 없으면만 적용
                item.style.fontSize = '22px'; // 글씨 크기 변경
                item.style.fontWeight = 'bolder'; // 글씨 굵기 증가
            }
        });

        // 마우스를 떼면 원래 크기와 굵기로 돌아가게 설정
        item.addEventListener('mouseleave', () => {
            if (!item.classList.contains('active')) { // active 클래스가 없으면만 적용
                item.style.fontSize = '20px'; // 원래 크기로 복원
                item.style.fontWeight = 'bold'; // 원래 굵기로 복원
            }
        });

        // 메뉴 클릭 시 active 클래스를 추가하여 클릭한 메뉴 스타일 적용
        item.addEventListener('click', () => {
            // 클릭된 메뉴에만 active 클래스를 추가하고 나머지 메뉴에서 제거
            menuItems.forEach(menu => menu.classList.remove('active'));
            item.classList.add('active');
        });
    });
};
</script>

<body>
	<header>
		<nav class="menu_container">
			<ul class="menu_bar">
				<li><a href="index.jsp">홈</a></li>
				<li><a href="./region.do">지역소개</a></li>
				<li><a href="./allBoard.do">여행게시판</a></li>
			</ul>
		</nav>
		<nav class="user-icon">
			<!-- 세션에 유저 정보가 있으면 로그아웃 버튼으로 변경 -->
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<a href="./logout.do">👤 로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href="./loginView.jsp">👤 로그인</a>
				</c:otherwise>
			</c:choose>
		</nav>
	</header>
</body>

</html>
