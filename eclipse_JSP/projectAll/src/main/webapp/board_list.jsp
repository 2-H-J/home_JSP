<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boards List</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
}

* {
	margin: 0;
	padding: 0;
}

.board-container {
	width: 100%;
}

.board-table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
	font-size: 16px;
	text-align: center; /* 모든 텍스트 기본 정렬: 가운데 */
}

.board-table th, .board-table td {
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	padding: 8px;
}

a {
	color: black;
	text-decoration: none;
}

.comment {
	color: rgb(249, 75, 75);
}
/* 제목 열만 좌측 정렬 */
.board-table td:nth-child(2) {
	text-align: left;
	width: 50%;
}

.btn_container {
	width: 100%;
	display: flex;
	flex-flow: row nowrap;
	justify-content: space-between;
}

button {
	width: 100px;
	background-color: white;
	border: 1px solid #d3d3d3;
	height: 30px;
	border-radius: 10px;
}

.body-container {
	width: 1200px;
	display: flex;
	flex-flow: column nowrap;
	margin: 0 auto;
}

thead {
	background-color: #f2f2f2;
	font-weight: bolder;
}

input {
	height: 25px;
}

select {
	height: 30px;
}
 /* Modal 스타일 */
        .modal {
            display: none; /* 기본적으로 숨김 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4); /* 검은색 반투명 배경 */
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            border-radius: 10px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .submit-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }
</style>
<script>
	// 모달 열기 및 닫기 기능
	function openModal(board) {
		var modal = document.getElementById("reportModal");
		document.getElementById("modalPostTitle").innerText = board.title;
		document.getElementById("modalPostNumber").value = board.postNumber;
		document.getElementById("modalPostNumberDisplay").innerText = board.postNumber;
		document.getElementById("modalAuthorId").innerText = board.nickName;
		// 사용자 닉네임을 세션에서 가져오거나 입력하도록 설정
		var userNickname = "${sessionScope.user != null ? sessionScope.user.nickName : ''}";
		document.getElementById("modalUserNickname").value = userNickname;
		modal.style.display = "block";
	}

	function closeModal() {
		var modal = document.getElementById("reportModal");
		modal.style.display = "none";
	}

	// 사용자가 모달 외부를 클릭하면 모달을 닫음
	window.onclick = function(event) {
		var modal = document.getElementById("reportModal");
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
</script>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<div class="body-container">
		<h1>게시글 목록</h1>

		<!-- 정렬 순서 설정 -->
		<c:set var="viewOrder"
			value="${param.sort == 'bcount' && param.order == 'desc' ? 'asc' : 'desc'}" />
		<c:set var="likeOrder"
			value="${param.sort == 'blike' && param.order == 'desc' ? 'asc' : 'desc'}" />

		<div>
			<!-- 최신순, 오래된순 버튼 -->
			<a
				href="boardsList.do?sort=createTime&order=desc&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}">
				<button>최신순</button>
			</a> <a
				href="boardsList.do?sort=createTime&order=asc&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}">
				<button>오래된순</button>
			</a>

			<!-- 조회수 정렬 버튼 -->
			<a
				href="boardsList.do?sort=bcount&order=${viewOrder}&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}">
				<button>조회수 ${param.sort == 'bcount' && param.order == 'desc' ? '낮은 순' : '높은 순'}</button>
			</a>

			<!-- 좋아요 정렬 버튼 -->
			<a
				href="boardsList.do?sort=blike&order=${likeOrder}&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}">
				<button>좋아요 ${param.sort == 'blike' && param.order == 'desc' ? '낮은 순' : '높은 순'}</button>
			</a>
		</div>

		<!-- 태그별 필터 버튼 -->
		<div>
			<a href="${pageContext.request.contextPath}/boardsCategory.do?tag=팁"><button>팁</button></a>
			<a href="${pageContext.request.contextPath}/boardsCategory.do?tag=자유"><button>자유</button></a>
			<a href="${pageContext.request.contextPath}/boardsCategory.do?tag=후기"><button>후기</button></a>
			<a href="${pageContext.request.contextPath}/boardsCategory.do"><button>전체</button></a>
		</div>

		<!-- 검색 폼 -->
		<form action="${pageContext.request.contextPath}/boardsList.do"
			method="get">
			<select name="tag">
				<option value="">전체</option>
				<option value="팁" <c:if test="${param.tag == '팁'}">selected</c:if>>팁</option>
				<option value="자유" <c:if test="${param.tag == '자유'}">selected</c:if>>자유</option>
				<option value="후기" <c:if test="${param.tag == '후기'}">selected</c:if>>후기</option>
			</select> <input type="text" name="keyword" placeholder="검색어 입력"
				value="${param.keyword}" /> <select name="type">
				<option value="title"
					<c:if test="${param.type == 'title'}">selected</c:if>>제목</option>
				<option value="writer"
					<c:if test="${param.type == 'writer'}">selected</c:if>>작성자</option>
			</select>
			<button type="submit">검색</button>
		</form>

		<!-- 검색 결과 -->
		<div class="board-container">
			<c:if test="${not empty list}">
				<table class="board-table">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>조회</th>
							<th>좋아요</th>
							<th>신고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list}">
							<tr>
								<td>${board.postNumber}</td>
								<td>[${board.tag}] <a
									href="./boardDetail.do?postNumber=${board.postNumber}">${board.title}</a>
									<c:if test="${board.ccount > 0}">
										<span class="comment">[${board.ccount}]</span>
									</c:if>
								</td>
								<td>${board.nickName}</td>
								<td><c:choose>
										<c:when test="${board.updateTime != null}">
                                        ${board.formattedUpdateTime}
                                    </c:when>
										<c:otherwise>
                                        ${board.formattedCreateTime}
                                    </c:otherwise>
									</c:choose></td>
								<td>${board.bcount}</td>
								<td>${board.blike}</td>
								<td><c:if test="${not empty sessionScope.user}">
										<button
											onclick="openModal({ postNumber: '${board.postNumber}', title: '${board.title}', nickName: '${board.nickName}' })">신고</button>
									</c:if> <c:if test="${empty sessionScope.user}">
										<button disabled title="신고하려면 로그인해야 합니다.">신고</button>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${empty list}">
				<p>검색 결과가 없습니다.</p>
			</c:if>
		</div>

		<!-- 버튼 -->
		<div class="btn_container">
			<a href="./boardWriteView.do"><button>글쓰기</button></a>
			<!-- 페이징 처리 -->
			<c:if test="${totalPages > 1}">
				<div class="pagination">
					<!-- 이전 버튼 -->
					<c:if test="${currentPage > 1}">
						<a
							href="boardsList.do?page=${currentPage - 1}&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}&sort=${param.sort}&order=${param.order}">
							<button>이전</button>
						</a>
					</c:if>
					<c:if test="${currentPage == 1}">
						<button disabled>이전</button>
					</c:if>

					<!-- 페이지 번호 -->
					<c:forEach var="i" begin="1" end="${totalPages}">
						<a
							href="boardsList.do?page=${i}&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}&sort=${param.sort}&order=${param.order}">
							<button <c:if test="${i == currentPage}">class="active"</c:if>>${i}</button>
						</a>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${currentPage < totalPages}">
						<a
							href="boardsList.do?page=${currentPage + 1}&tag=${param.tag}&keyword=${param.keyword}&type=${param.type}&sort=${param.sort}&order=${param.order}">
							<button>다음</button>
						</a>
					</c:if>
					<c:if test="${currentPage == totalPages}">
						<button disabled>다음</button>
					</c:if>
				</div>
			</c:if>
			<a href="./allBoard.do"><button>목록</button></a>
		</div>
	</div>
	<!-- 신고 모달 -->
	<div id="reportModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			<h2>게시글 신고</h2>
			<form action="reportUser.do" method="post">
				<div class="form-group">
					<label>게시글 제목:</label> <span id="modalPostTitle"></span>
				</div>
				<div class="form-group">
					<label>게시글 번호:</label> <span id="modalPostNumberDisplay"></span> <input
						type="hidden" id="modalPostNumber" name="postNumber" />
				</div>
				<div class="form-group">
					<label>작성자 ID:</label> <span id="modalAuthorId"></span>
				</div>
				<div class="form-group">
					<label>신고 사유:</label>
					<textarea name="reason" rows="4" required></textarea>
				</div>
				<div class="form-group">
					<label>내 닉네임:</label> <input type="text" id="modalUserNickname"
						name="userNickname"
						value="${sessionScope.user != null ? sessionScope.user.nickName : ''}"
						readonly />
				</div>
				<button type="submit" class="submit-btn">신고 제출</button>
			</form>
		</div>
	</div>
</body>
</html>