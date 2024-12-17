<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>

</head>
<style>
/* 게시글 상세 페이지 CSS */ /* 게시글 상세 페이지 CSS */
body {
	font-family: Arial, sans-serif;
}

.main-container {
	width: 1200px;
	display: flex;
	flex-flow: column nowrap;
	margin: 0 auto;
}

table {
	width: 1200px;
	margin: 20px auto;
	border-collapse: collapse;
	background-color: #fff;
}

th {
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	background-color: #f2f2f2;
	height: 40px;
}

td {
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	padding: 8px;
}

.title {
	font-size: 24px;
	text-align: center;
}

.content {
	height: 400px;
}

/* 공통 버튼 스타일 */
/* 링크 버튼 배치 */
.button-container {
	width: 100%;
	display: flex;
	justify-content: space-between;
	margin-bottom: 20px;
}

.button-container .left {
	display: flex;
}

.button-container .right {
	display: flex;
	gap: 10px; /* 버튼 간격 */
	margin-left: auto;
}

p {
	height: 50px;
}

button {
	width: 100px;
	background-color: white;
	border: 1px solid #d3d3d3;
	height: 30px;
	border-radius: 10px;
}

hr {
	border: 1px solid rgb(221, 221, 221);
	margin-bottom: 20px;
}

.comment_form {
	width: 100%;
	background-color: #f2f2f2;
	display: flex;
	flex-flow: row nowrap;
	justify-content: space-between;
	padding: 10px 15px;
	position: relative;
	font-size: 0px;
	box-sizing: border-box;
}

.btn_comment {
	width: 100px;
	height: 50px;
	position: absolute;
	top: 12px;
	right: 15px;
}

textarea {
	width: 1050px;
	height: 50px;
}

.comment-table {
	font-size: 16px;
}

.comment-table td {
	line-height: 2;
	text-decoration: none;
	color: black;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fff;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 50%;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
}

.reson {
	width: 100%;
	height: 100px;
	margin-top: 10px;
	border: 1px solid #ddd;
	padding: 10px;
	resize: none;
}
</style>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="./views/header.jsp"></jsp:include>
	<div class="main-container">
		<c:if test="${not empty board}">
			<table>
				<!-- 단일 게시글이므로 반복문을 제거하고, 바로 board 객체를 사용 -->
				<tr>
					<th>${board.nickName}</th>
					<th><c:choose>
							<c:when test="${board.updateTime != null}">${board.updateTime}</c:when>
							<c:otherwise>${board.createTime}</c:otherwise>
						</c:choose></th>
					<th>조회수 : ${board.bcount }</th>
					<th>좋아요 : ${board.blike }</th>
				</tr>
				<tr>
					<td colspan="4" class="title">${board.title}</td>
				</tr>
				<tr>
					<td colspan="4" class="content">${board.description}</td>
				</tr>
			</table>
		</c:if>
		<div class="button-container">
			<!-- 왼쪽으로 배치할 버튼 -->
			<div class="left">
				<a href="./allBoard.do"><button>목록</button></a>
			</div>
			<div>
				<button type="button" id="btn_like">
					좋아요 : <span id="like_count">${board.blike }</span>
				</button>
			</div>
			<!-- 오른쪽으로 배치할 버튼 -->
			<div class="right">
				<c:if test="${writer || sessionScope.user.grade == 'admin'}">
					<a href="./updateBoard.do?postNumber=${board.postNumber}"><button>수정</button></a>
					<a href="./deleteBoard.do?postNumber=${board.postNumber}"><button>삭제</button></a>
				</c:if>
			</div>
		</div>
		<hr>
		<!-- 댓글 입력 폼 -->
		<div class="comment_form">
			<form action="commentWrite.do" method="post">
				<input type="hidden" name="postNumber" value="${board.postNumber}">
				<textarea name="comment" placeholder="댓글을 입력하세요"></textarea>
				<button class="btn_comment">댓글작성</button>
			</form>
		</div>
		<!-- 댓글 목록 -->
		<div class="comment">
			<table class="comment-table">
				<c:forEach var="comment" items="${commentList}">
					<tr>
						<input type="hidden" name="commentNumber"
							value="${comment.commentNumber}">
						<td>${comment.nickName}(${comment.cmtCreateTime})<c:if
								test="${not empty sessionScope.user}">
								<button
									onclick="openReportModal('${comment.commentNumber}', '${comment.cDescription}')">
									신고</button>
							</c:if> <c:if
								test="${comment.userNumber == sessionScope.user.userNumber || sessionScope.user.grade == 'admin'}">
								<a
									href="./commentDelete.do?commentNumber=${comment.commentNumber}&postNumber=${comment.postNumber}">삭제</a>
							</c:if> <br>${comment.cDescription}
						</td>
						<td>
							<button type="button" id="btn_comment_like">
								좋아요 : <span class="clike_count">${comment.clike }</span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div></div>
		<p>첨부파일 목록</p>
		<c:forEach var="file" items="${fileList}">
			<a href="./fileDown.do?fileNumber=${file.fileNumber}">${file.fileName}</a>
			<br>
		</c:forEach>
	</div>
	</div>
	<!-- 신고 모달 -->
	<div id="reportModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			<h2>댓글 신고</h2>
			<form action="reportUser.do" method="post">
				<input type="hidden" id="commentNumber" name="commentNumber">
				<input type="hidden" id="postNumber" name="postNumber"
					value="${board.postNumber}">
				<div>
					<label>신고 대상 댓글:</label>
					<p id="commentContent"></p>
				</div>
				<div>
					<label>신고 사유:</label>
					<textarea class="reason" name="reason" required placeholder="신고 사유를 입력하세요"></textarea>
				</div>
				<button type="submit">신고 제출</button>
			</form>
		</div>
	</div>
</body>
<script>
document.addEventListener("DOMContentLoaded", function () {
            //좋아요 경고창 띄우기(글번호 같이 띄움)
            document.querySelector('#btn_like').onclick = () => {
            	const postNumber = '${board.postNumber}';
            	const userNumber = '${board.userNumber}';
            	let baseUrl = './boardLike.do';
            	baseUrl += '?postNumber='+ postNumber+'&userNumber='+userNumber;
            	console.log(baseUrl);
            	fetch(baseUrl).then(response => response.json())
            	.then(result => {
            		alert(result.msg);
            		console.log(result.blike);
            		document.querySelector('#like_count').innerText = result.blike;
        		});
			}
            //좋아요 경고창 띄우기(글번호 같이 띄움)
           document.querySelectorAll('#btn_comment_like').forEach(item => {
				item.onclick = (e) => {
            	const commentNumber = e.target.parentNode.parentNode.querySelector('input').value;
            	console.log("댓글번호 : " + commentNumber);
            	let baseUrl = './boardCommentLike.do';
            	baseUrl += '?commentNumber='+ commentNumber;
            	console.log(baseUrl);
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
    });
//신고 모달 열기
function openReportModal(commentNumber, commentContent) {
    document.getElementById("reportModal").style.display = "block";
    document.getElementById("commentNumber").value = commentNumber;
    document.getElementById("commentContent").innerText = commentContent;
}

// 신고 모달 닫기
function closeModal() {
    document.getElementById("reportModal").style.display = "none";
}

// 모달 외부 클릭 시 닫기
window.onclick = function(event) {
    const modal = document.getElementById("reportModal");
    if (event.target == modal) {
        closeModal();
    }
}
    </script>
</html>



