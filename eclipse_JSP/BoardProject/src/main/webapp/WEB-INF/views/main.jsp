<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 메인</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div class="container">
        <h1>게시판</h1>
        <div class="write-button-container">
	        <c:if test="${sessionScope.user != null }">
	            <a href="./boardWriteView.do" class="btn write-btn">글쓰기</a>
	        </c:if>
        </div>
        <table class="board-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                    <th>싫어요</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${boardList}">
                    <tr>
                        <td>${board.bno}</td>
                        <td><a href="./boardView.do?bno=${board.bno}">${board.title}</td>
                        <td>${board.id}</td>
                        <td>${board.writeDate}</td>
                        <td>${board.bcount}</td>
                        <td>${board.blike}</td>
                        <td>${board.bhate}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="7">
                        <div class="pagination">
                          	<!-- 
								이전 페이지 그룹으로 이동 - 이전 페이지 그룹이 있을때만
								 		◀ 를 클릭시 이전 그룹의 마지막 페이지로 이동
							-->
							<!-- get이랑 is생략하여 가능 -->
                          	<c:if test="${pagging.priviousPageGroup }">
                          		<a href="./boardMain.do?page=${pagging.startPageOfPageGroup - 1 }">◀</a>
                          	</c:if>
                          <!-- 
							페이징 결과 출력
								PaggingVO에 있는 getStartPageOfPageGroup,
								getEndPageOfPageGroup 메서드를 이용해서
								jstl의 forEach를 이용해서 게시판 페이지 번호를 출력
			
								<a href="./boardMain.do?pageNo=번호">번호</a>	
						  -->
						  <!-- get이랑 is생략하여 가능 -->
						  <c:forEach var="i" begin="${pagging.startPageOfPageGroup }" end="${pagging.endPageOfPageGroup }">
						  	<c:choose>
						  		<c:when test="${pagging.currentPage != i }">
						  			<a href="./boardMain.do?page=${i }">${i }</a>
						  		</c:when>
						  		<c:otherwise>
						  			<a class="current">${i }</a>
						  		</c:otherwise>
						  	</c:choose>
						  	</c:forEach>
						  	<!--
								다음 페이지 그룹으로 이동 
								다음 페이지 그룹이 있을때만 표시
								▶ 를 클릭시 다음 그룹의 첫번째 페이지로 이동
								마지막 페이지 그룹이면 해당 링크가 나오면 안됨 
								
								1번째 페이지 그룹 1 2 3 4
								2번째 페이지 그룹 5 6 7 8
								5번 페이지로 이동
								
								isNextPageGroup
								getEndPageOfPageGroup
							 -->
							<!-- get이랑 is생략하여 가능 -->
							 <c:if test="${pagging.nextPageGroup }">
							 	<a href="./boardMain.do?page=${pagging.endPageOfPageGroup + 1 }">▶</a>
							 </c:if>	
						  
                        </div>
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>