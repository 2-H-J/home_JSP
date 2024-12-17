<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSP 페이지 설정 -->
<!-- contentType: JSP에서 출력할 문서의 인코딩을 UTF-8로 설정 -->
<!-- JSTL Core 라이브러리 사용을 위한 선언 (c:forEach, c:if 등 태그 사용) -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 조회</title>
    <link rel="stylesheet" type="text/css" href="css/board_view.css">
    <!-- 게시글 조회 페이지를 위한 CSS 파일 연결 -->

    <!-- JavaScript 코드 작성 -->
    <script>
		window.onload = () => {
			// 게시글 좋아요/싫어요 버튼 이벤트 처리
			document.querySelectorAll('#btn_like, #btn_hate').forEach(item => {
				item.onclick = (e) => {
					const bno = '${board.bno}'; // 현재 게시글 번호
					let baseUrl = item.id == 'btn_like' ? './boardLike.do' : './boardHate.do';
					baseUrl += '?bno=' + bno; // 요청 URL에 게시글 번호 추가

					// 서버 요청 (좋아요/싫어요 처리)
					fetch(baseUrl).then(response => response.json())
					.then(result => {
						// 서버에서 반환된 메시지와 개수를 화면에 업데이트
						alert(result.msg); // 서버에서 전달된 메시지
						document.querySelector('#like_count').innerText = result.blike; // 좋아요 개수 업데이트
						document.querySelector('#hate_count').innerText = result.bhate; // 싫어요 개수 업데이트
					})
				}
			})

			// 댓글 좋아요/싫어요 버튼 이벤트 처리
			document.querySelectorAll('.btn_comment_like, .btn_comment_hate').forEach(item => {
				item.onclick = (e) => {
					// 댓글 번호 가져오기
					const cno = e.target.parentNode.parentNode.querySelector('input').value;
					let baseUrl = item.className == 'btn_comment_like' ? 
								'./boardCommentLike.do' : './boardCommentHate.do';
					baseUrl += '?cno=' + cno; // 요청 URL에 댓글 번호 추가

					// 서버 요청 (댓글 좋아요/싫어요 처리)
					fetch(baseUrl).then(response => response.json())
					.then(result => {
						alert(result.msg); // 서버에서 전달된 메시지
						location.reload(); // 페이지 새로고침 (댓글 개수 업데이트)
					})
				}
			})
		}
	</script>
</head>
<body>
    <div class="container">
        <!-- 게시글 상세 정보 테이블 -->
        <h1>게시글 조회</h1>
        <table class="board-view-table">
            <tr>
                <th>번호</th>
                <td>${board.bno}</td> <!-- 게시글 번호 -->
            </tr>
            <tr>
                <th>제목</th>
                <td>${board.title}</td> <!-- 게시글 제목 -->
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.id}</td> <!-- 게시글 작성자 -->
            </tr>
            <tr>
                <th>작성일</th>
                <td>${board.writeUpdateDate}</td> <!-- 게시글 작성/수정일 -->
            </tr>
            <tr>
                <th>조회수</th>
                <td>${board.bcount}</td> <!-- 게시글 조회수 -->
            </tr>
            <tr>
                <th>좋아요</th>
                <td>${board.blike}</td> <!-- 게시글 좋아요 수 -->
            </tr>
            <tr>
                <th>싫어요</th>
                <td>${board.bhate}</td> <!-- 게시글 싫어요 수 -->
            </tr>
            <tr>
                <th colspan="2" style="text-align: left;">내용</th>
            </tr>
            <tr>
                <td colspan="2">${board.content}</td> <!-- 게시글 내용 -->
            </tr>
            <!-- 좋아요/싫어요 버튼 -->
            <tr>
				<td colspan="2"> 
					<a href="#" id="btn_like">좋아요 : <span id="like_count">${board.blike }</span></a>
					<a href="#" id="btn_hate">싫어요 : <span id="hate_count">${board.bhate }</span></a>
				</td>
			</tr>
			<!-- 첨부파일 목록 출력 -->
			<tr>
				<td colspan="2">
					<h2>첨부파일 목록</h2>
					<c:forEach var="file" items="${fileList}">
						<a href="./fileDown.do?fno=${file.fno}">${file.fileName}</a><br>
					</c:forEach>
				</td>
			</tr>
			<!-- 댓글 입력 폼 -->
			<tr>
			 	<td colspan="2">
			 		<div class="comment_form">
						<form action="commentWrite.do" method="post">
							<input type="hidden" name="bno" value="${board.bno}">
							<textarea name="comment" placeholder="댓글을 입력하세요"></textarea>
							<button>댓글작성</button>
						</form>
					</div>
			 	</td>
			 </tr>
        </table>

        <!-- 버튼 영역 -->
        <div class="actions">
            <a href="./boardMain.do" class="btn">목록으로</a>
            <!-- 게시글 작성자만 수정/삭제 가능 -->
            <c:if test="${board.id == sessionScope.user.id}">
	            <a href="./boardDelete.do?bno=${board.bno}" class="btn delete-btn">게시글 삭제</a>
	            <a href="./boardUpdateView.do?bno=${board.bno}" class="btn edit-btn">게시글 수정</a>
            </c:if>
        </div>

        <hr>

		<!-- 댓글 목록 출력 -->
		<c:forEach var="comment" items="${commentList}">
		 	<div class="comment">
		 		<p>
		 			<input type="hidden" name="cno" value="${comment.cno}">
		 			<span>${comment.id}</span> <!-- 댓글 작성자 -->
		 			<span>작성일 ${comment.cdate}</span> <!-- 댓글 작성일 -->
		 			<span><a href="#" class="btn_comment_like">
		 				좋아요 : <span>${comment.clike}</span></a></span>
		 			<span><a href="#" class="btn_comment_hate">싫어요 : <span>${comment.chate}</span></a></span>
		 		</p>
		 		<p>${comment.content}</p> <!-- 댓글 내용 -->
		 		<!-- 댓글 삭제 버튼 (댓글 작성자만 보임) -->
		 		<c:if test="${comment.id == sessionScope.user.id}">
		 			<a href="./boardCommentDelete.do?cno=${comment.cno}&bno=${comment.bno}">댓글 삭제</a>
		 		</c:if>
		 	</div>
			<hr>		 
		</c:forEach>
    </div>
</body>
</html>
