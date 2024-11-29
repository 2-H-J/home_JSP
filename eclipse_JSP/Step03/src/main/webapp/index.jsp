<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	nav {
		width: 1200px;
		margin: 0 auto;
	}
	nav ul {
		list-style-type: none;
		display: flex;
		flex-flow: row nowrap;
		justify-content: center;
	}
	nav ul li{
		width:200px;
		text-align: center;
		border:1px solid black;
	}
	nav a:link, nav a:visited {
		display: inline-block;
		width: 100%;
		padding:20px 0px;
		color : black;
		text-decoration: none;
	}
	main{
		margin: 100px auto;
		width: 1200px;
		height: 400px;
		border :1px solid black;
	}
</style>
</head>
<body>
	<jsp:include page="./template/header.jsp"></jsp:include>
	
	<main>
		<h2>메인 콘텐츠 영역</h2>
	</main>
	
</body>
</html>