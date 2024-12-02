<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP 페이지 지시자 설정 -->
<!-- 
    language="java": 이 페이지에서 사용되는 언어는 Java입니다.
    contentType="text/html; charset=UTF-8": 이 페이지의 컨텐츠 타입을 HTML로 설정하고 UTF-8 인코딩을 사용합니다.
    pageEncoding="UTF-8": JSP 페이지 파일 자체의 문자 인코딩 방식을 UTF-8로 지정합니다.
-->

<!DOCTYPE html>
<!-- HTML5 문서 타입 선언 -->
<html>
<head>
    <meta charset="UTF-8">
    <!-- 문서의 문자 인코딩을 UTF-8로 설정하여 한글과 같은 특수문자도 문제없이 표시되도록 합니다. -->
    <title>Insert title here</title>
    <!-- 웹페이지의 제목을 설정하는 부분입니다. 실제 제목을 적절하게 변경해야 합니다. -->
</head>
<body>
    <ul>
        <!-- 회원 및 학과 정보 관련 링크들을 나열하는 리스트입니다. -->
        <li><a href="./all.do">전체 회원 정보 조회</a></li>
        <!-- "./all.do" URL을 호출하여 전체 회원 정보를 조회합니다. -->
        
        <li><a href="./member_insert_view.jsp">회원 정보 추가</a></li>
        <!-- "member_insert_view.jsp" 페이지로 이동하여 새 회원 정보를 추가할 수 있는 폼을 표시합니다. -->
        
        <li><a href="./allMajor.do">전체 학과 정보 조회</a></li>
        <!-- "./allMajor.do" URL을 호출하여 전체 학과 정보를 조회합니다. -->
        
        <li><a href="./member_name_search.jsp">사용자 이름 일부 조회</a></li>
        
        <li><a href="./majorSelectList.do">특정 학과 조회</a></li>
    </ul>
</body>
</html>
