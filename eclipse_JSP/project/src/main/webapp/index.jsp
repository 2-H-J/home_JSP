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
<style>
/* 전체 페이지 스타일 */
body {
	margin: 0; /* 기본 여백 제거 */
	padding: 0; /* 기본 여백 제거 */
	box-sizing: border-box; /* 모든 요소에 테두리 박스 모델 적용 */
	font-family: Arial, sans-serif; /* 기본 폰트 설정 */
	background-color: white; /* 배경 색상 */
}

/* 색상이 변경되는 상단 영역 */
.color-changing-header {
	width: 100%; /* 화면 너비를 채움 */
	height: 66.66vh; /* 화면 높이의 3분의 1 */
	position: fixed; /* 화면에 고정 */
	top: 0; /* 상단에 위치 */
	left: 0; /* 좌측에 위치 */
	z-index: -1; /* 다른 요소 뒤에 위치 */
	animation: changeColor 20s infinite; /* 5초마다 색상 변경 */
}

/* 색상 변경 애니메이션 */
@keyframes changeColor {
	0% { background-color: #F5CEC7; } /* 빨간색 */
	20% { background-color: #FFE5D8 ; } /* 녹색 */
	40% { background-color: #FFF7DD; } /* 녹색 */
	60% { background-color: #BAEDCE; } /* 파란색 */
	80% { background-color: #C2F2F6; } /* 노란색 */
	100% { background-color: #E5E7FB; } /* 녹색 */
}

/* 메인 컨테이너 */
.main-container {
	display: flex; /* 플렉스 박스로 구성 */
	flex-direction: row; /* 텍스트와 이미지를 가로로 배치 */
	justify-content: center; /* 수평 중앙 정렬 */
	align-items: center; /* 텍스트와 이미지 세로 정렬 */
	gap: 50px; /* 텍스트와 이미지 간 간격 */
	margin-top: 33.33vh; /* 상단 여백 */
}

/* 텍스트 슬라이드 컨테이너 */
.text-container {
	flex: 2; /* 텍스트 영역 크기 비율 */
	max-width: 200px; /* 텍스트 슬라이드 최대 너비 */
	height: 200px; /* 텍스트 영역 높이는 자동 설정 */
	border-radius: 8px; /* 모서리를 둥글게 */
	padding: 20px; /* 내부 여백 */
}

/* 텍스트 슬라이드 */
.text-slide {
	position: absolute; /* 슬라이드 절대 위치 지정 */
	width: 400px; /* 텍스트 슬라이드 너비 */
	height: 500px; /* 텍스트 높이는 자동 */
	top: 150px; /* 상단 고정 */
	left: 400px; /* 슬라이드는 항상 중앙에 위치 */
	display: flex; /* 내부 요소 플렉스 정렬 */
	flex-direction: column; /* 세로 방향 정렬 */
	align-items: center; /* 수직 중앙 정렬 */
	justify-content: center; /* 가로 중앙 정렬 */
	text-align: center; /* 텍스트 중앙 정렬 */
	font-size: 2rem; /* 텍스트 크기 */
	font-weight: bold; /* 굵은 텍스트 */
	color: #333; /* 텍스트 색상 */
	opacity: 0; /* 기본적으로 숨김 */
	transition: opacity 1s ease; /* 부드러운 투명도 전환 효과 */
}

/* 이미지 슬라이드 컨테이너 */
.image-container {
	flex: 2; /* 이미지 영역 크기 비율 */
	max-width: 900px; /* 이미지 슬라이드 최대 너비 */
	height: 500px; /* 이미지 영역 고정 높이 */
	overflow: hidden; /* 넘치는 내용 숨김 */
	position: static; /* 위치 조정을 위한 상대 위치 */
	border-radius: 8px; /* 모서리를 둥글게 */
}

/* 이미지 슬라이드 */
.image-slide {
	position: absolute; /* 슬라이드 절대 위치 지정 */
	width: 700px; /* 슬라이드 너비 */
	height: 500px; /* 슬라이드 높이 */
	top: 200px; /* 상단 고정 */
	left: 800px; /* 슬라이드는 항상 중앙에 위치 */
	display: flex; /* 내부 요소 플렉스 정렬 */
	align-items: center; /* 수직 중앙 정렬 */
	justify-content: center; /* 가로 중앙 정렬 */
	opacity: 0; /* 기본적으로 숨김 */
	transition: opacity 1s ease; /* 부드러운 투명도 전환 효과 */
}

.image-slide img {
	width: 100%; /* 이미지 너비 채움 */
	height: 100%; /* 이미지 높이 채움 */
	object-fit: cover; /* 이미지 비율 유지하며 채움 */
}

/* 강조 텍스트 */
.highlight-red {
	color: #b84e4e; /* 빨간색 강조 */
}

.highlight-pink {
	color: #d569a8; /* 분홍색 강조 */
}

.highlight-orange {
	color: #d88c26; /* 주황색 강조 */
}
</style>

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
