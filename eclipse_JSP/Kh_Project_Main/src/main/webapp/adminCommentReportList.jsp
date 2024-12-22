<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 목록</title>
<link rel="stylesheet" type="text/css" href="css/adminCommentReportList.css">
</head>
<body>
	<!-- 공통 헤더 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<jsp:include page="./views/footer.jsp"></jsp:include>
	<div class="body-container">
		<h1>신고 목록</h1>

		<c:if test="${not empty list}">
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
					<c:forEach var="report" items="${list}">
						<tr>
							<td>${report.reportNumber}</td>
							<td>${report.userNickname}</td>
							<td>${report.commentNumber}</td>
							<td>${report.commentWriterNickname}</td>
							<td>${report.commentReportReason}</td>
							<td><c:choose>
									<c:when test="${report.reportStatus == 'APPROVED'}">
										<span class="status-approved">승인됨</span>
									</c:when>
									<c:when test="${report.reportStatus == 'REJECTED'}">
										<span class="status-rejected">거부됨</span>
									</c:when>
									<c:otherwise>
										<span class="status-pending">대기 중</span>
									</c:otherwise>
								</c:choose></td>
							<td>${report.createTime}</td>
							<td><c:choose>
									<c:when test="${report.handledByAdminId != 0}">
                            ${report.handledByAdminId}
                        </c:when>
									<c:otherwise>
                            -
                        </c:otherwise>
								</c:choose></td>
							<td class="action-buttons">
								<!-- 승인 버튼 -->
								<form action="./commentReportList.do" method="post" style="display: inline;">
									<input type="hidden" name="reportNumber" value="${report.reportNumber}" /> 
									<input type="hidden" name="action" value="approve" />
									<button type="submit" class="approve-btn">승인</button>
								</form>
								<form action="./commentReportList.do" method="post" style="display: inline;">
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

		<c:if test="${empty list}">
			<p>신고 내역이 없습니다.</p>
		</c:if>

		<!-- 목록으로 돌아가기 버튼 -->
		<a href="admin.jsp" class="back-button">목록으로</a>
	</div>
</body>
</html>