<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>파일 업로드 테스트</h1>
	<form action="./fileUpload.do" method="post" enctype="multipart/form-data">
		<input type="text" name="txt"><br>
		<!-- 
			java.lang.NullPointerException 발생
			
		 -->
	
		파일 : <input type="file" name="file1"><br> 
		파일 : <input type="file" name="file2"><br>
		파일 : <input type="file" name="file3"><br>
		<button>파일 업로드</button>
	</form>
</body>
</html>