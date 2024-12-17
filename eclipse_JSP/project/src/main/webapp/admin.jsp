<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        .container {
            width: 80%;
            max-width: 800px;
            margin: 40px auto;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        a {
            text-decoration: none;
        }
        button {
            padding: 14px 35px;
            font-size: 16px;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s;
            margin: 10px;
        }
        button:hover {
            transform: scale(1.05);
        }
        .view-btn {
            background-color: #28a745; /* 녹색 버튼 */
        }
        .view-btn:hover {
            background-color: #218838;
        }
        .report-btn {
            background-color: #007bff; /* 파란색 버튼 */
        }
        .report-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>관리자 페이지</h1>

    <!-- 회원 관리 페이지 링크 -->
    <a href="./allUser.do">
        <button class="view-btn">회원 전체 조회 or 회원 관리</button>
    </a>
    
    <!-- 신고 목록 페이지 링크 -->
    <a href="adminReports.do">
        <button class="report-btn">신고 목록 보기</button>
    </a>
</div>

</body>
</html>