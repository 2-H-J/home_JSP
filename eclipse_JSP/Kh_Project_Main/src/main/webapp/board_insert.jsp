<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSP 페이지의 언어와 문자 인코딩 설정 -->
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="UTF-8">
<!-- 페이지의 문자 인코딩 설정 -->
<title>Insert title here</title>
<jsp:include page="./views/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/boardInsert.css">
<!-- Toast UI Editor의 스타일 시트 로드 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<!-- Toast UI Editor의 JavaScript 파일 로드 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<style>

</style>
<script>
// 페이지 로드 후 실행될 스크립트
window.onload = () => {
	// Toast UI Editor 초기화
	const editor = new toastui.Editor({
		  el: document.querySelector('#description'), 
		  height: '700px', 
		  initialEditType: 'wysiwyg', 
		  previewStyle: 'vertical' 
		});

	// 폼 제출 시 에디터의 HTML과 Markdown 데이터를 숨겨진 input에 설정
	document.querySelector('form').onsubmit = (e) => {
	document.querySelector('input[name=description]').value = editor.getHTML(); // HTML 데이터를 숨겨진 input에 설정
	}	

	//파일 드래그 처리
	let file_area = document.querySelector('.file_drop_area');
	let file = document.querySelector('#file');

	file_area.ondrop = (e) => {
		e.preventDefault();
		const data = e.dataTransfer;
		console.log(data);
		console.log(data.files);
		//파일 태그에 드래그한 파일 정보를 연결
		file.files = data.files;
		let file_list_view = document.querySelector('.file_list_view');
		for(let i=0;i<data.files.length;i++){
			file_list_view.innerHTML += `\${data.files[i].name} <br>`;
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
			<!-- 게시글 작성 폼 -->
			<form action="./insertBoard.do" method="post"
				enctype="multipart/form-data">
				<div class="title_container">
					<!-- 태그 선택 -->
					<select id="tag" name="tag">
						<option>자유</option>
						<option>팁</option>
						<option>후기</option>
					</select>
					<!-- 제목 입력 -->
					<div class="form-group">
						<label for="title"> 제목:</label> <input type="text" id="title"
							name="title" required>
						<!-- 제목 입력 필수 -->
					</div>
				</div>
				<!-- 내용 입력 -->
				<div class="form-group">
					<div id="description"></div>
					<!-- Toast UI Editor가 렌더링될 요소 -->
					<input type="hidden" name="description">
					<!-- 에디터 내용이 HTML로 저장될 숨겨진 input 태그 -->
				</div>
					<!-- 파일 드래그 앤 드롭 영역 -->
					<div class="file_drop_area"></div>
					<!-- 파일 선택 input (숨겨짐) -->
					<input type="file" name="file" id="file">
					<!-- 파일 목록 보기 영역 -->
					<div class="file_list_view"></div>
				<!-- 제출 버튼 -->
				<div class="form-button">
					<button type="submit" class="btn_submit">작성</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>