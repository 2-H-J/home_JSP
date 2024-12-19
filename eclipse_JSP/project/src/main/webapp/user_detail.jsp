<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 상세 정보</title>
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
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table th, table td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #ddd;
        }
        table th {
            background-color: #007bff;
            color: white;
            text-align: center;
            width: 30%;
        }
        table td {
            background-color: #f9f9f9;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s;
        }
        button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
    <!-- 헤더 포함 -->
   <jsp:include page="./views/header.jsp"></jsp:include>
    <jsp:include page="./views/footer.jsp"></jsp:include>
    <div class="container">
        <h2>사용자 상세 정보</h2>

        <table>
            <tr>
                <th>유저 번호</th>
                <td>${userDetail.userNumber}</td>
            </tr>
            <tr>
                <th>아이디</th>
                <td>${userDetail.loginId}</td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${userDetail.nickName}</td>
            </tr>
            <tr>
                <th>이름</th>
                <td>${userDetail.userName}</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${userDetail.userEmail}</td>
            </tr>
            <tr>
                <th>가입일</th>
                <td>${userDetail.createTime}</td>
            </tr>
        </table>

        <!-- 페이지 이동 버튼 -->
        <div class="button-container">
            <a href="adminUser.do?action=view"><button>목록으로 돌아가기</button></a>
        </div>
    </div>
</body>
</html>