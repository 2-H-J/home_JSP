<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        .body-container {
            width: 80%;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table th, table td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        table th {
            background-color: #f2f2f2;
            color: #333;
        }
        table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .action-buttons button {
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .action-buttons .approve-btn {
            background-color: #28a745;
            color: white;
        }
        .action-buttons .reject-btn {
            background-color: #dc3545;
            color: white;
        }
        .status-approved {
            color: #28a745;
            font-weight: bold;
        }
        .status-rejected {
            color: #dc3545;
            font-weight: bold;
        }
        .status-pending {
            color: #ffc107;
            font-weight: bold;
        }
        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <!-- 공통 헤더 포함 -->
    <jsp:include page="./views/header.jsp"></jsp:include>
    <jsp:include page="./views/footer.jsp"></jsp:include>
    <div class="body-container">
        <h1>신고 목록</h1>

        <c:if test="${not empty reports}">
           <table>
    <thead>
        <tr>
            <th>신고 번호</th>
            <th>신고자 닉네임</th>
            <th>게시글 번호</th>
            <th>작성자 닉네임</th>
            <th>신고 사유</th>
            <th>신고 상태</th>
            <th>신고 일시</th>
            <th>처리 관리자 ID</th>
            <th>조치</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.reportNumber}</td>
                <td>${report.userNickname}</td>
                <td>${report.postNumber}</td>
                <td>${report.postWriterNickname}</td>
                <td>${report.userReportReason}</td>
                <td>
                    <c:choose>
                        <c:when test="${report.reportStatus == 'APPROVED'}">
                            <span class="status-approved">승인됨</span>
                        </c:when>
                        <c:when test="${report.reportStatus == 'REJECTED'}">
                            <span class="status-rejected">거부됨</span>
                        </c:when>
                        <c:otherwise>
                            <span class="status-pending">대기 중</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${report.createTime}</td>
                <td>
                    <c:choose>
                        <c:when test="${report.handledByAdminId != 0}">
                            ${report.handledByAdminId}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
            <td class="action-buttons">
    <!-- 승인 버튼 -->
<form action="${pageContext.request.contextPath}/adminReports.do" method="post" style="display: inline;">
    <input type="hidden" name="reportNumber" value="${report.reportNumber}" />
    <input type="hidden" name="action" value="approve" />
    <button type="submit" class="approve-btn">승인</button>
</form>
<form action="${pageContext.request.contextPath}/adminReports.do" method="post" style="display: inline;">
    <input type="hidden" name="reportNumber" value="${report.reportNumber}" />
    <input type="hidden" name="action" value="reject" />
    <button type="submit" class="reject-btn">거부</button>
</form>
</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
        </c:if>

        <c:if test="${empty reports}">
            <p>신고 내역이 없습니다.</p>
        </c:if>

        <!-- 목록으로 돌아가기 버튼 -->
        <a href="admin.jsp" class="back-button">목록으로</a>
    </div>
</body>
</html>