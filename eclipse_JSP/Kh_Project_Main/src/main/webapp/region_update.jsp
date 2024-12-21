<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 정보 수정</title>
<!-- ToastUI 에디터 CSS 및 JS -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=f05ff66bab3d2444d168e2e13e1ed414&libraries=services"></script>
<style>
/* 공통 스타일 */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.header {
	background-color: #81d4fa;
	padding: 20px;
	text-align: center;
	color: #333;
	font-size: 28px;
}

.section {
	margin-bottom: 30px;
}

.section-title {
	font-size: 20px;
	margin-bottom: 10px;
	border-bottom: 2px solid #ddd;
	padding-bottom: 5px;
}

input {
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
	margin-bottom: 10px;
}

button {
	background-color: #007BFF;
	color: #fff;
	border: none;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}

#map {
	width: 100%;
	height: 500px;
	margin-bottom: 20px;
}

.search-container {
	display: flex;
	margin-bottom: 10px;
}

.search-input {
	flex: 1;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ddd;
	border-radius: 5px 0 0 5px;
}

.search-button {
	background-color: #007BFF;
	color: white;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 0 5px 5px 0;
}

.search-button:hover {
	background-color: #0056b3;
}

.result-list {
	list-style: none;
	padding: 0;
	margin: 0;
}

.result-item {
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-bottom: 5px;
	cursor: pointer;
}

.result-item:hover {
	background-color: #f9f9f9;
}
</style>
</head>
<body>
	<div class="header">지역 정보 수정</div>

	<div class="container">
		<form action="./syncRegion.do" method="post"
			enctype="multipart/form-data">
			<div class="section">
				<h2 class="section-title">썸네일 이미지</h2>
				<c:if test="${not empty region.imageUrl}">
					<img id="thumbnail-preview"
						src="data:image/png;base64,${region.imageUrl}" alt="기존 썸네일 이미지"
						style="max-width: 100%; height: auto;">
				</c:if>
				<c:if test="${empty region.imageUrl}">
					<div id="thumbnail-container"></div>
					<!-- 이미지가 없을 경우 이미지 컨테이너만 출력 -->
				</c:if>
				<input type="file" id="image" name="image" accept="image/*">
				<input type="hidden" name="existingImageUrl"
					value="${region.imageUrl}">
			</div>

			<div class="section">
				<h2 class="section-title">지역 이름</h2>
				<input type="text" name="title" value="${region.title}" required>
			</div>
			<div class="section">
				<h2 class="section-title">지역 설명</h2>
				<div id="description-editor"></div>
				<input type="hidden" name="description">
			</div>

			<div class="section">
				<h2 class="section-title">지도 검색</h2>
				<div class="search-container">
					<input type="text" id="search-input" class="search-input"
						placeholder="검색어를 입력하세요">
					<button id="search-button" class="search-button" type="button">검색</button>
				</div>

				<div id="map"></div>
				<ul id="result-list" class="result-list"></ul>
				<label for="latitude"></label> <input type="hidden" id="latitude"
					name="latitude" value="${region.latitude}" readonly> <label
					for="longitude"></label> <input type="hidden" id="longitude"
					name="longitude" value="${region.longitude}" readonly>
			</div>

			<div class="section">
				<button type="submit">수정</button>
				<a href="./regionDetail.do?regionNumber=${region.regionNumber}"><button
						type="button">취소</button></a>
			</div>

			<input type="hidden" name="regionNumber"
				value="${region.regionNumber}">
		</form>
	</div>

	<script>
        window.onload = () => {
            const editor = new toastui.Editor({
                el: document.querySelector('#description-editor'),
                height: '500px',
                initialEditType: 'wysiwyg',
                previewStyle: 'vertical',
                initialValue: `${region.description}`
            });

            document.querySelector('form').onsubmit = () => {
                const descriptionContent = editor.getHTML();
                document.querySelector('input[name=description]').value = descriptionContent;
            };
            
            document.getElementById('image').addEventListener('change', function(event) {
                const file = event.target.files[0];

                // 파일이 선택된 경우
                if (file) {
                    const reader = new FileReader();

                    // 파일 읽기가 완료되었을 때 실행되는 함수
                    reader.onload = function(e) {
                        // 썸네일 미리보기 태그가 없으면 생성
                        let preview = document.getElementById('thumbnail-preview');
                        if (!preview) {
                            const container = document.getElementById('thumbnail-container'); // 썸네일을 추가할 컨테이너
                            preview = document.createElement('img');
                            preview.id = 'thumbnail-preview';
                            preview.alt = '썸네일 미리보기';
                            preview.style.maxWidth = '100%';
                            preview.style.height = 'auto';
                            container.appendChild(preview); // 컨테이너에 이미지 추가
                        }

                        preview.src = e.target.result; // 미리보기 이미지 설정
                        preview.style.display = 'block'; // 이미지 표시
                    };

                    reader.readAsDataURL(file); // 파일을 읽어 데이터 URL로 변환
                } else {
                    // 파일이 없을 경우 미리보기 숨기기
                    const preview = document.getElementById('thumbnail-preview');
                    if (preview) {
                        preview.src = '';
                        preview.style.display = 'none';
                    }
                }
            });
            
            document.querySelector('form').onsubmit = () => {
    	        const descriptionContent = editor.getHTML();
    	        document.querySelector('input[name=description]').value = descriptionContent;

    	        const latitude = document.getElementById('latitude').value.trim();
    	        const longitude = document.getElementById('longitude').value.trim();

    	        if (!isValidCoordinate(latitude) || !isValidCoordinate(longitude)) {
    	            alert('위도와 경도 값을 입력하고 유효한 좌표를 선택해주세요.');
    	            return false; // 폼 제출 중단
    	        }
    	    };

    	    function isValidCoordinate(value) {
    	        return !isNaN(value) && value !== ''; // 숫자이며 비어 있지 않은지 확인
    	    }
            
            
        };

        var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
        var mapContainer = document.getElementById('map');
        var mapOption = {
            center: new kakao.maps.LatLng(${region.latitude}, ${region.longitude}),
            level: 3
        };
        var map = new kakao.maps.Map(mapContainer, mapOption);
        var ps = new kakao.maps.services.Places();
        var resultList = document.getElementById('result-list');

        document.getElementById('search-button').addEventListener('click', function() {
            var keyword = document.getElementById('search-input').value;
            if (!keyword.trim()) {
                alert('검색어를 입력하세요.');
                return;
            }
            ps.keywordSearch(keyword, placesSearchCB);
        });

        function placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {
                resultList.innerHTML = '';
                data.forEach(function(place) {
                    displayMarker(place);
                    displayResultItem(place);
                });
            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                alert('검색 결과가 없습니다.');
            } else {
                alert('검색 중 오류가 발생했습니다.');
            }
        }

        function displayMarker(place) {
            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(place.y, place.x)
            });

            kakao.maps.event.addListener(marker, 'click', function() {
                infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                infowindow.open(map, marker);
                document.getElementById('latitude').value = place.y;
                document.getElementById('longitude').value = place.x;
            });
        }

        function displayResultItem(place) {
            var item = document.createElement('li');
            item.className = 'result-item';
            item.innerText = place.place_name + ' (' + place.address_name + ')';
            item.addEventListener('click', function() {
                var coords = new kakao.maps.LatLng(place.y, place.x);
                map.setCenter(coords);
                infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                infowindow.open(map, new kakao.maps.Marker({ position: coords }));
                document.getElementById('latitude').value = place.y;
                document.getElementById('longitude').value = place.x;
            });

            resultList.appendChild(item);
        }
    </script>
</body>
</html>
