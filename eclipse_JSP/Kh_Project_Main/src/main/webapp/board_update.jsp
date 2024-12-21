<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>수정</title>
<!-- 공통 헤더 -->
<jsp:include page="./views/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<style>
/* 페이지 기본 스타일 */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f9f9f9;
}

.main_container {
	display: flex;
	flex-flow: row nowrap;
	justify-content: flex-start;
	margin: 8% auto;
	width: 1200px;
	background-color: rgb(249, 249, 249);
}

.bady_container {
	width: 1000px;
	flex: 1;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	margin-left: 20px;
	justify-content: center;
	gap: 10px;
}

/* 파일 드래그 영역 */
.file_drop_area {
	height: 100px;
	border: 2px dashed #e9e9e9;
	background-image: url('img/file.png');
	background-repeat: no-repeat;
	background-position: center;
	margin: 20px;
}

#file {
	display: none;
}

/* 숨겨진 파일 input 태그 */
.file_area_active {
	box-shadow: 0px 0px 3px 3px gray;
}

/* 제목 및 태그 영역 */
.title_container {
	display: flex;
	flex-flow: row nowrap;
	gap: 10px;
	justify-content: center;
	align-items: center;
}

#title {
	width: 500px;
	height: 25px;
	border-radius: 10px;
	margin: 10px;
}

#tag {
	width: 100px;
	font-size: 14px;
	padding: 5px 8px;
	border-radius: 20px;
	border-color: rgb(221, 221, 221);
	text-align: center;
}

/* 카테고리 영역 */
.category-container {
	display: flex;
	flex-direction: column;
	width: 200px;
	gap: 10px;
	background-color: #fff;
	border-radius: 10px;
	padding: 20px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.category-item {
	display: flex;
	align-items: center;
	gap: 10px;
	font-size: 16px;
	font-weight: bold;
	color: #333;
	cursor: pointer;
	padding: 10px;
	border-radius: 8px;
	transition: background-color 0.3s;
	text-decoration: none;
}

.category-item:hover {
	background-color: #e0f1ff;
}

/* 아이콘 스타일 */
.category-icon {
	width: 20px;
	height: 20px;
	background-image: url('img/advertising/icon.jpg');
	background-size: 120px 120px;
	display: inline-block;
	mix-blend-mode: multiply;
	filter: brightness(1) contrast(1.2);
}

.icon-all {
	background-position: -62px -62.5px;
}

.icon-free {
	background-position: -33px -65px;
}

.icon-review {
	background-position: -81.25px -45px;
}

.icon-tip {
	background-position: -11px -70px;
}

.icon-accommodation {
	background-position: -43px -44px;
}

.icon-schedule {
	background-position: -76px -84px;
}

.icon-traffic {
	background-position: -44px -20px;
}

.icon-qa {
	background-position: -81.25px -20px;
}

.file_list_view {
	color: black;
	padding: 5px;
}

input {
	border: 1px solid rgb(221, 221, 221);
}

/* 버튼 스타일 */
.form-button {
	display: flex;
	justify-content: center;
	align-items: center;
}

.btn_submit {
	background-color: #82C9E6; /* 기존 버튼 색상 */
	border: none;
	color: white; /* 글자색은 흰색 */
	padding: 5px 12px; /* 길이를 적당하게 설정 */
	font-size: 14px; /* 폰트 크기 */
	border-radius: 5px; /* 페이징 버튼과 비슷한 정도로 둥글게 */
	cursor: pointer;
	transition: background-color 0.3s; /* 배경색 전환 효과 */
}

.btn_submit:hover {
	background-color: #66B2C1; /* 버튼 호버 시 배경색 변경 */
}
</style>
</head>
<body>
	<div class="main_container">
		<!-- 왼쪽 카테고리 -->
		<div class="category-container">
			<a href="${pageContext.request.contextPath}/boardsCategory.do"
				class="category-item"> <span class="category-icon icon-all"></span>
				전체
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=자유"
				class="category-item"> <span class="category-icon icon-free"></span>
				자유
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=후기"
				class="category-item"> <span class="category-icon icon-review"></span>
				후기
			</a> <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=팁"
				class="category-item"> <span class="category-icon icon-tip"></span>
				팁
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=숙소"
				class="category-item"> <span
				class="category-icon icon-accommodation"></span> 숙소
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=여행일정"
				class="category-item"> <span class="category-icon icon-schedule"></span>
				여행일정
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=교통정보"
				class="category-item"> <span class="category-icon icon-traffic"></span>
				교통정보
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=Q&A"
				class="category-item"> <span class="category-icon icon-qa"></span>
				Q&A
			</a>
		</div>
		<div class="bady_container">
			<form action="./syncBoard.do" method="post"
				enctype="multipart/form-data">
				<div class="title_container">
					<select id="tag" name="tag">
						<option value="자유"
							<c:if test="${board.tag == '자유'}">selected</c:if>>자유</option>
						<option value="팁" <c:if test="${board.tag == '팁'}">selected</c:if>>팁</option>
						<option value="후기"
							<c:if test="${board.tag == '후기'}">selected</c:if>>후기</option>
					</select>
					<div class="form-group">
						<label for="title">제목</label> <input type="text" id="title"
							name="title" value="${board.title}" required>
					</div>
				</div>
				<div class="form-group">
					<!-- 에디터의 내용이 여기 들어갈 위치 -->
					<div id="description">${board.description}</div>
					<!-- 수정된 내용은 hidden input으로 전달 -->
					<input type="hidden" name="description">
				</div>
					<div class="file_drop_area"></div>
					<input type="file" name="file" id="file">
					<div class="file_list_view"></div>
				<div class="form-button">
					<!-- 숨겨진 삭제된 파일 정보 -->
					<input type="hidden" name="deleteFile" id="deleteFile"> 
					<input type="hidden" name="postNumber" value="${board.postNumber}">
					<button type="submit" class="btn_submit">수정</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
    window.onload = () => {
        // 서버에서 Base64 이미지를 포함한 HTML을 그대로 가져옴
        const descriptionContent = '${board.description}';  // Base64 이미지가 포함된 HTML

        // Editor 초기화
        const editor = new toastui.Editor({
            el: document.querySelector("#description"),  // 에디터가 삽입될 DOM 요소
            height: '700px',
            initialEditType: 'wysiwyg',  // WYSIWYG 에디터로 시작
            previewStyle: 'vertical',  // 미리보기 스타일
        });

        // 폼 제출 시 수정된 내용을 hidden input에 저장
        document.querySelector('form').onsubmit = (e) => {
            // 에디터에서 수정된 HTML을 가져와서 hidden input에 저장
            console.log(editor.getHTML());
        	console.log(editor.getMarkdown());
            document.querySelector('input[name=description]').value = editor.getHTML();
        }

        // 파일 드래그 처리
        let file_area = document.querySelector('.file_drop_area');
        let file = document.querySelector('#file');

        file_area.ondrop = (e) => {
            e.preventDefault();
            const data = e.dataTransfer;
            file.files = data.files;
            let file_list_view = document.querySelector('.file_list_view');
            for (let i = 0; i < data.files.length; i++) {
                file_list_view.innerHTML += `\${data.files[i].name}<br>`;
            }
            e.target.classList.remove('file_area_active');
        }

        file_area.ondragover = (e) => {
            e.preventDefault();
        }

        file_area.ondragenter = (e) => {
            e.target.classList.add('file_area_active');
            e.preventDefault();
        }

        file_area.ondragleave = (e) => {
            e.target.classList.remove('file_area_active');
            e.preventDefault();
        }
    }
</script>
</html>

