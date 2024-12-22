<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>

<div class="container">
    <h1>관리자 페이지</h1>

    <!-- 회원 관리 페이지 링크 -->
    <a href="./allUser.do">
        <button class="view-btn">회원 전체 조회 or 회원 관리</button>
    </a>
    
    <!-- 게시글 신고 목록 페이지 링크 -->
    <a href="adminReports.do">
        <button class="report-btn">게시글 신고 목록 보기</button>
    </a>
    
    <!-- 댓글 신고 목록 페이지 링크 -->
    <a href="commentReportList.do">
        <button class="report-btn">댓글 신고 목록 보기</button>
    </a>
</div>

</body>
</html>