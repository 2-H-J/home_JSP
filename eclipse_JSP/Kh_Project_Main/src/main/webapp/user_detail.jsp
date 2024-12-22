<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 상세 정보</title>
    <link rel="stylesheet" type="text/css" href="css/user_detail.css">
</head>
<body>
    <!-- 헤더 포함 -->
   <jsp:include page="./views/header.jsp"></jsp:include>
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
    	<jsp:include page="./views/footer.jsp"></jsp:include>
    
</body>
</html>