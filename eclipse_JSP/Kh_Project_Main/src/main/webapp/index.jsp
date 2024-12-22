<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.UsersDTO"%>
<%@ page import="service.UsersService"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="jakarta.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<!-- 상단 3분의 1 색상 변경 영역 -->
	<div class="color-changing-header"></div>

	<!-- 헤더 JSP 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>

	<div class="main-container">
		<!-- 텍스트 슬라이드 컨테이너 -->
		<div class="slider-container text-container">
			<div class="slide text-slide" id="text1">
				<span class="highlight-red">기차타고</span> <span class="highlight-pink">떠나기
					좋은</span> <span class="highlight-orange">가을여행지</span>
			</div>
			<div class="slide text-slide" id="text2">
				<span class="highlight-red">기차타고</span> <span class="highlight-pink">풍경을
					즐기는</span> <span class="highlight-orange">산의 이야기</span>
			</div>
			<div class="slide text-slide" id="text3">
				<span class="highlight-red">기차타고</span> <span class="highlight-pink">강변에서
					만나는</span> <span class="highlight-orange">힐링의 순간</span>
			</div>
			<div class="slide text-slide" id="text4">
				<span class="highlight-red">기차타고</span> <span class="highlight-pink">걷는
					숲 속의</span> <span class="highlight-orange">아름다운 길</span>
			</div>
		</div>

		<!-- 이미지 슬라이드 컨테이너 -->
		<div class="slider-container image-container">
			<div class="slide image-slide" id="img1">
				<img src="img/nature/1.jpg" alt="자연 사진 1">
			</div>
			<div class="slide image-slide" id="img2">
				<img src="img/nature/2.jpg" alt="자연 사진 2">
			</div>
			<div class="slide image-slide" id="img3">
				<img src="img/nature/3.jpg" alt="자연 사진 3">
			</div>
			<div class="slide image-slide" id="img4">
				<img src="img/nature/4.jpg" alt="자연 사진 4">
			</div>
		</div>
	</div>

	<jsp:include page="./views/footer.jsp"></jsp:include>

	<script>
    const slidesText = document.querySelectorAll('.text-container .slide');
    const slidesImage = document.querySelectorAll('.image-container .slide');
    let currentSlide = 0; // 현재 슬라이드 인덱스

    // 슬라이드 초기화
    function initializeSlides() {
        slidesText.forEach(slide => {
            slide.style.opacity = 0;
            slide.style.zIndex = 0;
        });
        slidesImage.forEach(slide => {
            slide.style.opacity = 0;
            slide.style.zIndex = 0;
        });

        // 첫 번째 슬라이드 활성화
        slidesText[0].style.opacity = 1;
        slidesText[0].style.zIndex = 1;
        slidesImage[0].style.opacity = 1;
        slidesImage[0].style.zIndex = 1;
    }

    // 텍스트와 이미지 슬라이드를 함께 변경
    function changeSlide() {
        // 현재 슬라이드 비활성화
        slidesText[currentSlide].style.opacity = 0;
        slidesText[currentSlide].style.zIndex = 0;
        slidesImage[currentSlide].style.opacity = 0;
        slidesImage[currentSlide].style.zIndex = 0;

        // 다음 슬라이드 인덱스 계산
        currentSlide = (currentSlide + 1) % slidesText.length;

        // 다음 슬라이드 활성화 (우측에서 나타남)
        slidesText[currentSlide].style.opacity = 1;
        slidesText[currentSlide].style.zIndex = 1;
        slidesImage[currentSlide].style.opacity = 1;
        slidesImage[currentSlide].style.zIndex = 1;
    }

    // 초기화 및 슬라이드 자동 변경
    initializeSlides();
    setInterval(changeSlide, 5000); // 텍스트와 이미지 슬라이드는 5초마다 함께 변경
	</script>
</body>
</html>
