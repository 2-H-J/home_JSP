<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
</head>
<link rel="stylesheet" type="text/css" href="css/boardView.css">
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<!-- 왼쪽 카테고리 -->
	<div class="main-container">
		<div class="category-container">
			<a href="${pageContext.request.contextPath}/boardsCategory.do"
				class="category-item"> <span class="category-icon icon-all"></span>
				전체
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=자유"
				class="category-item"> <span class="category-icon icon-free"></span>
				자유
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=후기"
				class="category-item"> <span class="category-icon icon-review"></span>
				후기
			</a> <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=팁"
				class="category-item"> <span class="category-icon icon-tip"></span>
				팁
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=숙소"
				class="category-item"> <span
				class="category-icon icon-accommodation"></span> 숙소
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=여행일정"
				class="category-item"> <span class="category-icon icon-schedule"></span>
				여행일정
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=교통정보"
				class="category-item"> <span class="category-icon icon-traffic"></span>
				교통정보
			</a> <a
				href="${pageContext.request.contextPath}/boardsCategory.do?tag=Q&A"
				class="category-item"> <span class="category-icon icon-qa"></span>
				Q&A
			</a>
		</div>
		<div class="body-container">
			<c:if test="${not empty board}">
				<table>
					<tr>
						<th>${board.nickName}</th>
						<th><c:choose>
								<c:when test="${board.formattedUpdateTime != null}">${board.formattedUpdateTime}</c:when>
								<c:otherwise>${board.formattedCreateTime}</c:otherwise>
							</c:choose></th>
						<th>조회수 : ${board.bcount }</th>
						<th>좋아요 : ${board.blike }</th>
					</tr>
					<tr>
						<td colspan="4" class="title">${board.title}</td>
					</tr>
					<tr class="board-content">
						<td colspan="4" class="content">${board.description}</td>
					</tr>
					<tr class="fileList">
						<td colspan="4">
							<p>첨부파일 목록</p> 
							<c:forEach var="file" items="${fileList}">
								<a href="./fileDown.do?fileNumber=${file.fileNumber}">${file.fileName} <br>
								</a>
							</c:forEach>
						</td>
					</tr>
				</table>
			</c:if>

			<div class="button-container">
				<!-- 왼쪽으로 배치할 버튼 -->
				<div class="left">
					<a href="./allBoard.do"><button>목록</button></a>
				</div>
				<div class="like-container">
					<button type="button" id="btn_like">
						좋아요 <span id="like_count">${board.blike }</span>
					</button>
					<c:if test="${not empty sessionScope.user}">
						<button
							onclick="openModal({ postNumber: '${board.postNumber}', title: '${board.title}', nickName: '${board.nickName}' })">신고</button>
					</c:if>
				</div>
				<!-- 오른쪽으로 배치할 버튼 -->
				<div class="right">
					<c:if test="${writer || sessionScope.user.grade == 'admin'}">
						<a href="./updateBoard.do?postNumber=${board.postNumber}"><button>수정</button></a>
						<a href="./deleteBoard.do?postNumber=${board.postNumber}"><button>삭제</button></a>
					</c:if>
				</div>
			</div>

			<!-- 댓글 목록 -->
			<div class="comment">
				<table class="comment-table">
					<c:forEach var="comment" items="${commentList}">
						<tr class="comment-title">
							<input type="hidden" name="commentNumber"
								value="${comment.commentNumber}">
							<td>${comment.nickName}(${comment.cmtCreateTime})
							<c:if test="${comment.userNumber == sessionScope.user.userNumber || sessionScope.user.grade == 'admin' }">
							<a href="./commentDelete.do?commentNumber=${comment.commentNumber}&postNumber=${comment.postNumber}" class="comment-delete-btn"><button>x</button></a>
							</c:if>
							</td>
							<td class="comment-button">
								<button type="button" class="btn_comment_like">
									좋아요 <span class="clike_count">${comment.clike }</span>
								</button> <c:if test="${not empty sessionScope.user}">
									<button class="report-btn"
										onclick="openCommentReportModal('${comment.commentNumber}', '${comment.cDescription}', '${comment.userNumber }')">신고</button>
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="comment_content">${comment.cDescription}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 댓글 입력 폼 -->
			<div class="comment_form">
				<form action="commentWrite.do" method="post">
					<input type="hidden" name="postNumber" value="${board.postNumber}">
					<textarea name="comment" placeholder="댓글을 입력하세요" required></textarea>
					<button class="btn_comment">댓글작성</button>
				</form>
			</div>
		</div>
		<!-- 신고 모달 -->
		<div id="commentReportModal" class="commentmodal">
			<div class="comment-modal-content">
				<span class="commentclose">&times;</span>
				<h2>댓글 신고</h2>
				<form action="commentReport.do" method="post">
					<input type="hidden" id="commentNumber" name="commentNumber">
					<input type="hidden" id="postNumber" name="postNumber"
						value="${board.postNumber}">
					<div>
						<label>신고 대상 댓글:</label>
						<p id="commentContent"></p>
					</div>
					<div>
						<label>신고 사유:</label>
						<textarea class="commentreason" name="reason" required
							placeholder="신고 사유를 입력하세요"></textarea>
					</div>
					<button type="submit">신고 제출</button>
				</form>
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
	</div>
	<script>
	// 게시글 신고
	function openModal(board) {
		let userNumber = '${sessionScope.user.getUserNumber()}';
		let writerNumber = '${board.userNumber}'
		if(userNumber == writerNumber){
			alert('자기 자신이 쓴 게시글은 신고할 수 없습니다.');
            return;
		}
		
		var modal = document.getElementById('reportModal');
		document.getElementById('modalPostTitle').innerText = board.title;
		document.getElementById('modalPostNumber').value = board.postNumber;
		document.getElementById('modalPostNumberDisplay').innerText = board.postNumber;
		document.getElementById('modalAuthorId').innerText = board.nickName;
		// 사용자 닉네임을 세션에서 가져오거나 입력하도록 설정
		var userNickname = "${sessionScope.user != null ? sessionScope.user.nickName : ''}";
		document.getElementById('modalUserNickname').value = userNickname;
		modal.style.display = 'block';
	}

	function closeModal() {
		var modal = document.getElementById('reportModal');
		modal.style.display = 'none';
	}
	document.addEventListener("DOMContentLoaded", function () {
    const user = ${sessionScope.user != null ? 'true' : 'false'}; // 유저 정보가 있는지 확인

    // 좋아요 클릭 시 처리
    document.querySelector('#btn_like').onclick = () => {
        if (!user) {
            alert('로그인 후 좋아요를 눌러주세요.');
            window.location.href = './login.do'; // 로그인 페이지로 리디렉션
            return;
        }
        const postNumber = '${board.postNumber}';
        const userNumber = '${board.userNumber}';
        let baseUrl = './boardLike.do';
        baseUrl += '?postNumber='+ postNumber+'&userNumber='+userNumber;
        fetch(baseUrl).then(response => response.json())
        .then(result => {
            alert(result.msg);
            document.querySelector('#like_count').innerText = result.blike;
        });
    }

    // 댓글 좋아요 클릭 시 처리
    document.querySelectorAll('.btn_comment_like').forEach(item => {
        item.onclick = (e) => {
            if (!user) {
                alert('로그인 후 좋아요를 눌러주세요.');
                window.location.href = './login.do'; // 로그인 페이지로 리디렉션
                return;
            }
            const commentNumber = e.target.parentNode.parentNode.querySelector('input').value;
            let baseUrl = './boardCommentLike.do';
            baseUrl += '?commentNumber='+ commentNumber;
            fetch(baseUrl).then(response => response.json())
            .then(result => {
                alert(result.msg);
                const likeCountElement = e.target.querySelector('.clike_count');
                if (likeCountElement) {
                    likeCountElement.innerText = result.clike;
                }
            });
        }
    });

    // 댓글 삭제 버튼 클릭 시 처리
    document.querySelectorAll('.comment-delete-btn').forEach(item => {
        item.onclick = (event) => {
            if (!user) {
                alert('로그인 후 댓글을 삭제할 수 있습니다.');
                window.location.href = './login.do'; // 로그인 페이지로 리디렉션
                event.preventDefault(); // 기본 동작을 방지 (삭제 요청을 보내지 않음)
                return;
            }
        }
    });

    // 신고 모달 열기
    window.openCommentReportModal = function(commentNumber, commentContent, commentUserNumber) {
        if (!user) {
            alert('로그인 후 댓글 신고를 할 수 있습니다.');
            window.location.href = './login.do'; // 로그인 페이지로 리디렉션
            return;
        }
        let userNumber = '${sessionScope.user.getUserNumber()}';
		let writerNumber = commentUserNumber;
		if(userNumber == writerNumber){
			alert('자기 자신이 쓴 댓글은 신고할 수 없습니다.');
            return;
		}
        document.getElementById('commentReportModal').style.display = 'block';
        document.getElementById('commentNumber').value = commentNumber;
        document.getElementById('commentContent').innerText = commentContent;
    };

    // 신고 모달 닫기
    function commentCloseModal() {
        document.getElementById('commentReportModal').style.display = 'none';
    }

    // 'X' 버튼 클릭 시 모달 닫기
    document.querySelector('.commentclose').addEventListener('click', commentCloseModal);

});
</script>
</body>
</html>
