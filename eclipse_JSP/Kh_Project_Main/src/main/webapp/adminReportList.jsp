<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 목록</title>
<link rel="stylesheet" type="text/css" href="css/adminReportList.css">
<body>
	<!-- 공통 헤더 포함 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<div class="body-container">
		<h1>신고 목록</h1>

		<!-- 검색 및 필터 폼 -->
		<div style="margin-bottom: 20px;">
			<form method="get"
				action="${pageContext.request.contextPath}/adminReports.do">
				<input type="text" name="memberId" placeholder="회원 번호로 검색"
					value="${param.memberId}" />
				<button type="submit"
					style="padding: 8px 12px; border-radius: 4px; background-color: #007bff; color: white; border: none; cursor: pointer;">검색</button>
			</form>
		</div>

		<c:if test="${not empty reports}">
			<table>
				<thead>
					<tr>
						<th>신고 번호</th>
						<th>신고자 회원번호</th>
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
							<td>${report.userNumber}</td>
							<td>${report.postNumber}</td>
							<td>${report.postWriterNickname}</td>
							<td>${report.userReportReason}</td>
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
								<form
									action="${pageContext.request.contextPath}/adminReports.do"
									method="post" style="display: inline;">
									<input type="hidden" name="reportNumber"
										value="${report.reportNumber}" /> <input type="hidden"
										name="action" value="approve" />
									<!-- 현재 페이지와 검색 조건 유지 -->
									<input type="hidden" name="memberId" value="${param.memberId}" />
									<input type="hidden" name="page" value="${currentPage}" />
									<button type="submit" class="approve-btn">승인</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/adminReports.do"
									method="post" style="display: inline;">
									<input type="hidden" name="reportNumber"
										value="${report.reportNumber}" /> <input type="hidden"
										name="action" value="reject" />
									<!-- 현재 페이지와 검색 조건 유지 -->
									<input type="hidden" name="memberId" value="${param.memberId}" />
									<input type="hidden" name="page" value="${currentPage}" />
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

		<!-- 페이징 -->
		<div class="pagination" style="margin-top: 20px; text-align: center;">
			<c:if test="${currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}/adminReports.do?page=${currentPage - 1}&memberId=${param.memberId}">
					<button
						style="padding: 8px 12px; border-radius: 4px; background-color: #007bff; color: white; border: none; cursor: pointer;">이전</button>
				</a>
			</c:if>
			<c:if test="${currentPage == 1}">
				<button
					style="padding: 8px 12px; border-radius: 4px; background-color: #b0b0b0; color: white; border: none; cursor: not-allowed;">이전</button>
			</c:if>

			<c:forEach var="i" begin="1" end="${totalPages}">
				<a
					href="${pageContext.request.contextPath}/adminReports.do?page=${i}&memberId=${param.memberId}">
					<button
						style="padding: 8px 12px; border-radius: 4px; background-color: ${i == currentPage ? '#0056b3' : '#007bff'}; color: white; border: none; cursor: pointer; margin: 0 5px;">
						${i}</button>
				</a>
			</c:forEach>

			<c:if test="${currentPage < totalPages}">
				<a
					href="${pageContext.request.contextPath}/adminReports.do?page=${currentPage + 1}&memberId=${param.memberId}">
					<button
						style="padding: 8px 12px; border-radius: 4px; background-color: #007bff; color: white; border: none; cursor: pointer;">다음</button>
				</a>
			</c:if>
			<c:if test="${currentPage == totalPages}">
				<button
					style="padding: 8px 12px; border-radius: 4px; background-color: #b0b0b0; color: white; border: none; cursor: not-allowed;">다음</button>
			</c:if>
		</div>

		<!-- 목록으로 돌아가기 버튼 -->
		<a href="admin.jsp" class="back-button">목록으로</a>
	</div>
		<jsp:include page="./views/footer.jsp"></jsp:include>
	
</body>
</html>
