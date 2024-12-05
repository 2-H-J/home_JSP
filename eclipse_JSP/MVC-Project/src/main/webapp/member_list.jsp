<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>전체 회원 정보</h2>
    <table border="1">
        <thead>
            <tr>
                <th>아이디</th> <!-- 회원 아이디 열 -->
                <th>비밀번호</th> <!-- 회원 비밀번호 열 -->
                <th>이름</th> <!-- 회원 이름 열 -->
                <th>닉네임</th> <!-- 회원 닉네임 열 -->
            </tr>
        </thead>
        <tbody>
            <!-- JSTL을 사용해 서버에서 받은 리스트 데이터를 반복 출력 -->
            <c:forEach var="member" items="${requestScope.list}">
                <tr>
                    <td>${member.id}</td> <!-- 회원의 ID 출력 -->
                    <td>${member.password}</td> <!-- 회원의 비밀번호 출력 -->
                    <td>${member.userName}</td> <!-- 회원의 이름 출력 -->
                    <td>${member.nickName}</td> <!-- 회원의 닉네임 출력 -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 메인 페이지로 이동하는 링크 -->
    <a href="./main.do">메인 페이지로 이동</a>
</body>
</html>
