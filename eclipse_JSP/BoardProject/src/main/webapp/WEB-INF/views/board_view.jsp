<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 조회</title>
    <link rel="stylesheet" type="text/css" href="css/board_view.css">
    <script>
		window.onload = () => {
			//좋아요 링크 클릭 -> 경고창 띄우기
			
			//댓글 좋아요
		
		}
		
	
	</script>
</head>
<body>
    <div class="container">
        <h1>게시글 조회</h1>
        <table class="board-view-table">
            <tr>
                <th>번호</th>
                <td>${board.bno}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${board.title}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.id}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${board.writeUpdateDate}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${board.bcount}</td>
            </tr>
            <tr>
                <th>좋아요</th>
                <td>${board.blike}</td>
            </tr>
            <tr>
                <th>싫어요</th>
                <td>${board.bhate}</td>
            </tr>
            <tr>
                <th colspan="2" style="text-align: left;">내용</th>
            </tr>
            <tr>
                <td colspan="2">${board.content}</td>
            </tr>
            <tr>
				<td colspan="2"> 
					<a href="#" id="btn_like">좋아요 : <span id="like_count">${board.blike }</span> </a>
					<a href="#" id="btn_hate">싫어요 : <span id="hate_count">${board.bhate }</span> </a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h2>첨부파일 목록</h2>
					<c:forEach var="file" items="${fileList }">
						<a href="./fileDown.do?fno=${file.fno }">${file.fileName }</a><br>
					</c:forEach>
				</td>
			</tr>
			 <tr>
			 	<td colspan="2">
			 		<!-- 댓글 입력 폼 -->
			 		<div class="comment_form">
						<form action="commentWrite.do" method="post">
							<input type="hidden" name="bno" value="${board.bno }">
							<textarea name="comment" placeholder="댓글을 입력하세요"></textarea>
							<button>댓글작성</button>
						</form>
					</div>
			 	</td>
			 </tr>
        </table>
        <div class="actions">
            <a href="./boardMain.do" class="btn">목록으로</a>
           	<!-- 
           		게시글 삭제, 수정버튼 출력
           		./boardUpdateView.do?bno=글번호
           		./boardDelete.do?bno=글번호
           		게시글 작성자와 로그인한 사람이 동일할때만 삭제/수정 출력
           	 -->
           	 <c:if test="${board.id == sessionScope.user.id }">
	           	 <a href="./boardDelete.do?bno=${board.bno}" class="btn edit-btn">게시글 삭제</a>
	           	 <a href="./boardUpdateView.do?bno=${board.bno}" class="btn delete-btn">게시글 수정</a>
           	 </c:if>
        </div>
        <hr>
		<!-- 
			댓글 내용을 출력, 댓글 번호(hidden), 작성일, 좋아요, 싫어요, 작성자, 댓글 내용 
			댓글 삭제 버튼 -> 작성자와 로그인한 사람이 일치할때
			
			작성자, 작성일, 좋아요, 싫어요
			댓글 내용
			삭제버튼
		 -->
		 <c:forEach var="comment" items="${commentList }">
		 	<div class="comment">
		 		<p>
		 			<input type="hidden" name="cno" value="${comment.cno }">
		 			<span>${comment.id }</span>
		 			<span>작성일 ${comment.cdate}</span>
		 			<span><a href="" class="btn_comment_like">
		 				좋아요 : <span>${comment.clike }</span>
		 				</a></span>
		 			<span><a href="" class="btn_comment_hate">싫어요 : <span>${comment.chate}</span></a></span>
		 		</p>
		 		<p>${comment.content }</p>
		 		<c:if test="${comment.id == sessionScope.user.id }">
		 			<a href="./boardCommentDelete.do?cno=${comment.cno}&bno=${comment.bno}">댓글 삭제</a>
		 		</c:if>
		 	</div>
			<hr>		 
		 </c:forEach>
    </div>
</body>
</html>