<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 정보 수정</title>
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
        previewStyle: 'vertical',
        initialValue: "${region.description}"  // 기존 지역 정보의 description을 초기 값으로 설정
    });

    document.querySelector('form').onsubmit = (e) => {
        console.log(editor.getHTML());
        document.querySelector('input[name=description]').value = editor.getHTML();
    }
}
</script>
</head>
<body>
	<h1>지역 정보 수정</h1>
	<form action="./syncRegion.do" method="post">
		<!-- 지역 이름 입력 -->
		<div class="form-group">
			<label for="title">지역 이름</label> <input type="text" id="title"
				name="title" value="${region.title}" required>
		</div>

		<!-- 지역 설명 입력 -->
		<div class="form-group">
			<label for="description">지역 설명</label>
			<div id="description">${region.description}</div>
			<input type="hidden" name="description">
		</div>

		<!-- 이미지 URL 입력 -->
		<div class="form-group">
			<label for="imageUrl">이미지 URL</label> <input type="text"
				id="imageUrl" name="imageUrl" value="${region.imageUrl}">
		</div>

		<!-- 지역 번호(수정 시 기준이 되는 값) -->
		<input type="hidden" name="regionNumber"
			value="${region.regionNumber}">

		<!-- 수정 버튼 -->
		<div class="form-group">
			<button type="submit">수정</button>
			<a href="./regionDetail.do?regionNumber=${region.regionNumber}">
				<button type="submit">취소</button>
			</a>
	</form>
</body>
</html>
