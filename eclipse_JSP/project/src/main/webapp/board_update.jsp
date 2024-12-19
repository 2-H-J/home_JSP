<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>수정</title>
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<style>
body {
	height: 1000px;
}

.file_drop_area {
	width: 200px;
	height: 200px;
	border: 2px dashed #e9e9e9;
	background-image: url('img/file.png');
	background-repeat: no-repeat;
	background-position: center;
}

#file {
	display: none;
}

.file_area_active {
	box-shadow: 0px 0px 3px 5px red;
}
</style>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<h1>수정</h1>
	<form action="./syncBoard.do" method="post"
		enctype="multipart/form-data">
		<c:if test="${not empty board}">
			<!-- 하나의 게시글 객체에서 제목을 가져와서 입력 필드에 표시 -->
			<label for="tag">태그 선택:</label>
			<select id="tag" name="tag">
				<option value="자유" <c:if test="${board.tag == '자유'}">selected</c:if>>자유</option>
				<option value="팁" <c:if test="${board.tag == '팁'}">selected</c:if>>팁</option>
				<option value="후기" <c:if test="${board.tag == '후기'}">selected</c:if>>후기</option>
			</select>
			<div class="form-group">
				<label for="title">제목</label> <input type="text" id="title"
					name="title" value="${board.title}" required>
			</div>
			<div class="form-group">
				<label for="description">내용</label>
				<div id="description">${board.description}</div>
				<input type="hidden" name="description">
			</div>
			<div class="file_drop_area"></div>
			<input type="file" name="file" id="file">
			<div class="file_list_view"></div>
			<!-- 숨겨진 삭제된 파일 정보 -->
			<div class="form-group">
				<input type="hidden" name="deleteFile" id="deleteFile"> <input
					type="hidden" name="postNumber" value="${board.postNumber}">
				<!-- 게시글 번호 숨겨서 전달 -->
				<button type="submit">수정</button>
			</div>
		</c:if>
	</form>
</body>
<script>
        window.onload = () => {
        	const editor = new toastui.Editor({
        		  el: document.querySelector('#description'),
        		  height: '500px',
        		  initialEditType: 'wysiwyg',
        		  previewStyle: 'vertical',
        		  initialValue: "${board.description}"
        		});

        	document.querySelector('form').onsubmit = (e) => {
        		//e.preventDefault();
        		console.log(editor.getHTML());
        		console.log(editor.getMarkdown());
        		document.querySelector('input[name=description]').value = editor.getHTML();
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

