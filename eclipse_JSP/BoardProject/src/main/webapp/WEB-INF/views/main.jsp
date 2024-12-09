<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 메인</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div class="container">
        <h1>게시판</h1>
        <div class="write-button-container">
            <c:if test="${sessionScope.user != null }">
                <a href="./boardWriteView.do" class="btn write-btn">글쓰기</a>
            </c:if>
        </div>
        <table class="board-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                    <th>싫어요</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${boardList}">
                    <tr>
                        <td>${board.bno}</td>
                        <td><a href="./boardView.do?bno=${board.bno}">${board.title}</td>
                        <td>${board.id}</td>
                        <td>${board.writeDate}</td>
                        <td>${board.bcount}</td>
                        <td>${board.blike}</td>
                        <td>${board.bhate}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="7">
                        <div class="pagination">
                          
                        </div>
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>