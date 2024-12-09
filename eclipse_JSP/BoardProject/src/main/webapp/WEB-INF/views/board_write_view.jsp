<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <link rel="stylesheet" type="text/css" href="css/board_write_view.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script>
    	window.onload = () => {
    		const editor = new toastui.Editor({
    			  el: document.querySelector('#content'),
    			  height: '500px',
    			  initialEditType: 'wysiwyg',
    			  previewStyle: 'vertical'
   			});		
    		
    		document.querySelector('form').onsubmit = (e) => {
    			//e.preventDefault();
    			console.log(editor.getHTML());
    			console.log(editor.getMarkdown());
    			document.querySelector('input[name=content]').value = editor.getHTML(); 
    		}
    	}
    </script>
</head>
<body>
    <div class="container">
        <h1>게시글 작성</h1>
        <form action="./boardWrite.do" method="post">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <div id="content"></div>
                <input type="hidden" name="content">
            </div>
            <div class="form-actions">
                <button type="submit" class="btn submit-btn">등록</button>
                <a href="./boardMain.do" class="btn cancel-btn">취소</a>
            </div>
        </form>
    </div>
</body>
</html>