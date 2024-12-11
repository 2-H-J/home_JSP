<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        		//e.preventDefault();
        		console.log(editor.getHTML());
        		console.log(editor.getMarkdown());
        		document.querySelector('input[name=description]').value = editor.getHTML();
        	}	
        }
</script>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="./insertBoard.do" method="post">
		<label for="tag">태그 선택:</label> <select id="tag" name="tag">
			<option>자유</option>
			<option>팁</option>
			<option>후기</option>
		</select>
		<div class="form-group">
			<label for="title">제목</label> <input type="text" id="title"
				name="title" required>
		</div>
		<div class="form-group">
			<label for="description">내용</label>
			<div id="description"></div>
			<input type="hidden" name="description">
		</div>
		<div class="form-group">
			<button type="submit">작성</button>
		</div>
	</form>
</body>
</html>