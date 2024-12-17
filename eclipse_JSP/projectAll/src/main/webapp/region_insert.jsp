<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 정보 작성</title>
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>
window.onload = () => {
    const editor = new toastui.Editor({
        el: document.querySelector('#description'),
        height: '500px',
        initialEditType: 'wysiwyg',
        previewStyle: 'vertical'
    });

    document.querySelector('form').onsubmit = (e) => {
        console.log(editor.getHTML());
        document.querySelector('input[name=description]').value = editor.getHTML();
    }
}
</script>
</head>
<body>
	<h1>지역 정보 작성</h1>
	<form action="./insertRegion.do" method="post">
		<!-- 지역 이름 입력 -->
		<div class="form-group">
			<label for="title">지역 이름</label> <input type="text" id="title"
				name="title" required>
		</div>

		<!-- 지역 설명 입력 -->
		<div class="form-group">
			<label for="description">지역 설명</label>
			<div id="description"></div>
			<input type="hidden" name="description">
		</div>

		<!-- 이미지 URL 입력 -->
		<div class="form-group">
			<label for="imageUrl">이미지 URL</label> <input type="text"
				id="imageUrl" name="imageUrl">
		</div>

		<!-- 작성 버튼 -->
		<div class="form-group">
			<button type="submit">작성</button>
		</div>
	</form>
	<a href="./region.do">
		<button>취소</button>
	</a>
</body>
</html>
