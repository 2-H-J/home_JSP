<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.UsersDTO"%>
<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
    <%
        UsersDTO user = (UsersDTO) request.getAttribute("user");
        System.out.println("[JSP] 받은 user 객체: " + (user != null ? user.getNickName() : "null"));
    %>
    <%
        if (user != null) {
    %>
        <p>안녕하세요, <b><%= user.getNickName() %></b> 님!</p>
        <form action="logout.do" method="post">
            <button type="submit">로그아웃</button>
        </form>
    <%
        } else {
    %>
        <a href="signin.jsp"><button>로그인</button></a>
    <%
        }
    %>

    <ul>
        <li><a href="./allUser.do">전체 회원 정보 조회</a></li>
        <li><a href="./allBoard.do">전체 게시판 정보 조회</a></li>
    </ul>
    <a href="./updateUser.do">내정보 수정</a>
</body>
</html>
