<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boards List</title>
<style>
/* 전체 페이지 스타일 */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f9f9f9;
}

/* 메인 레이아웃 */
.main-container {
    display: flex;
    justify-content: flex-start;
    margin: 8% auto;
    width: 1200px;
}

/* 카테고리 영역 */
.category-container {
    display: flex;
    flex-direction: column;
    width: 200px;
    gap: 10px;
    background-color: #fff;
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.category-item {
    display: flex;
    align-items: center;
    gap: 10px;
    font-size: 16px;
    font-weight: bold;
    color: #333;
    cursor: pointer;
    padding: 10px;
    border-radius: 8px;
    transition: background-color 0.3s;
    text-decoration: none;
}

.category-item:hover {
    background-color: #e0f1ff;
}

.category-item img {
    width: 20px;
    height: 20px;
}
/*아이콘 스타일 */
.category-icon {
    width: 20px; /* 아이콘 크기 */
    height: 20px;
    background-image: url('img/advertising/icon.jpg'); /* 전체 아이콘 이미지 */
    background-size: 120px 120px;
    display: inline-block;
    mix-blend-mode: multiply; /* 흰색 배경을 투명하게 보이게 */
    filter: brightness(1) contrast(1.2); /* 대비 조정 */
}

/* 각 아이콘의 위치 설정 */
.icon-all { background-position: -62px -62.5px; }
.icon-free { background-position:  -33px -65px; }
.icon-review { background-position: -81.25px -45px; }
.icon-tip { background-position: -11px -70px; }
.icon-accommodation { background-position: -43px -44px; }
.icon-schedule { background-position: -76px -84px; }
.icon-traffic { background-position: -44px -20px; }
.icon-qa { background-position: -81.25px -20px; }

.h1{
	margin-top:20px;
	padding:5px,10px;
}


/* 콘텐츠 영역 */
.body-container {
    flex: 1;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    margin-left: 20px;
}

/* 게시판 테이블 */
.board-table {
    width: 100%;
    border-collapse: collapse; /* 테이블 경계를 합침 */
    margin-top: 10px;
}

.board-table th, .board-table td {
    border-top: 1px solid #ddd; /* 상단 가로줄만 유지 */
    border-bottom: 1px solid #ddd; /* 하단 가로줄 유지 */
    padding: 9px;
    text-align: center;
    font-size: 14px;
}

.board-table th {
    background-color: #f2f2f2;
    font-weight: bold;
}

.board-table a {
    text-decoration: none;
    color: #000;
    font-weight: bold;
}

.comment {
    color: #FF9800;
    font-weight: bold;
}

/* 필터 검색 스타일 */
.filter-container {
    display: flex;
    justify-content: space-between;
    margin-auto: 0px; /* 게시글 목록과의 간격을 더 늘림 */
    gap: 15px;
    align-items: center;
}

.filter-container form {
    display: flex;
    gap: 10px;
    align-items: center;
    margin-top:5px
}

.filter-container select, .filter-container input {
    padding: 5px 8px; /* 사이즈 줄임 */
    font-size: 14px;
    border-radius: 20px; /* 둥글게 */
    border: 1px solid #ddd;
}

.filter-container button {
    padding: 5px 12px; /* 사이즈 줄임 */
    font-size: 14px;
    border-radius: 20px; /* 둥글게 */
    border: none;
    background-color: #82C9E6;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s;
}

.filter-container button:hover {
    background-color: #5FA5C1;
}
/* 정렬 버튼 스타일 */
.sort-container {
    display: flex;
    gap: 10px;
}

.sort-container a button {
    background-color: #82C9E6;
    border: none;
    color: white;
    padding: 5px 12px; /* 사이즈 줄임 */
    font-size: 14px;
    border-radius: 20px; /* 둥글게 */
    cursor: pointer;
    transition: background-color 0.3s;
}

.sort-container a button:hover {
    background-color: #5FA5C1;
}

/* 페이징 스타일 */
.pagination {
    display: flex;
    justify-content: center;
    gap: 10px;
}
.pagination a button {
    background-color: #82C9E6;
    border: none;
    color: white;
    padding: 5px 10px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
}
.pagination a button.active {
    background-color: #5FA5C1;
    font-weight: bold;
}
/* 글쓰기 버튼 스타일 */
.btn_write, .btn_list {
    background-color: #82C9E6;  /* 기존 버튼 색상과 동일 */
    border: none;
    color: white;  /* 글자색은 흰색 */
    padding: 5px 12px;  /* 길이를 적당하게 설정 */
    font-size: 14px;  /* 폰트 크기 */
    border-radius: 5px;  /* 페이징 버튼과 비슷한 정도로 둥글게 */
    cursor: pointer;
    transition: background-color 0.3s;  /* 배경색 전환 효과 */
}

/* 마우스를 올렸을 때의 배경 색상 변화 */
.btn_write:hover, .btn_list:hover {
    background-color: #5FA5C1;  /* 마우스를 올리면 색상 변화 */
}

/* 글쓰기 버튼과 목록 버튼의 크기를 적당히 설정 */
.btn_write, .btn_list {
    width: auto;
    height: auto;
    text-align: center;
}
/* 버튼들이 위치할 영역을 flexbox로 설정 */
.btn_bottom {
    display: flex;
    justify-content: space-between; /* 왼쪽과 오른쪽, 가운데 배치 */
    align-items: center; /* 수직 중앙 정렬 */
    margin-top: 20px;
}

/* 글쓰기 버튼은 왼쪽에, 목록 버튼은 오른쪽에 배치 */
.btn_bottom .btn_write {
    margin-right: auto; /* 왼쪽에 배치 */
}

/* 목록 버튼은 오른쪽에 배치 */
.btn_bottom .btn_list {
    margin-left: auto; /* 오른쪽에 배치 */
}

</style>
</head>
<body>
  <jsp:include page="./views/header.jsp"></jsp:include>
       <div class="main-container">
        <!-- 왼쪽 카테고리 -->
        <div class="category-container">
            <a href="${pageContext.request.contextPath}/boardsCategory.do" class="category-item">
                <span class="category-icon icon-all"></span>
                전체
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=자유" class="category-item">
                <span class="category-icon icon-free"></span>
                자유
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=후기" class="category-item">
                <span class="category-icon icon-review"></span>
                후기
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=팁" class="category-item">
                <span class="category-icon icon-tip"></span>
                팁
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=숙소" class="category-item">
                <span class="category-icon icon-accommodation"></span>
                숙소
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=여행일정" class="category-item">
                <span class="category-icon icon-schedule"></span>
                여행일정
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=교통정보" class="category-item">
                <span class="category-icon icon-traffic"></span>
                교통정보
            </a>
            <a href="${pageContext.request.contextPath}/boardsCategory.do?tag=Q&A" class="category-item">
                <span class="category-icon icon-qa"></span>
                Q&A
            </a>
        </div>

        <!-- 메인 콘텐츠 -->
        <div class="body-container">
            <h1>게시글 목록</h1>

            <!-- 필터 및 정렬 버튼 -->
            <div class="filter-container">
                <form method="get" action="boardsList.do">
                    <select name="tag">
                        <option value="">카테고리</option>
                        <option value="팁" ${param.tag == '팁' ? 'selected' : ''}>팁</option>
                        <option value="자유" ${param.tag == '자유' ? 'selected' : ''}>자유</option>
                        <option value="후기" ${param.tag == '후기' ? 'selected' : ''}>후기</option>
                    </select>
                    <input type="text" name="keyword" placeholder="제목 검색" value="${param.keyword}" />
                    <button type="submit">검색</button>
                </form>
                <!-- 정렬 버튼 -->
                <div class="sort-container">
                    <a href="boardsList.do?sort=bcount&order=${param.sort == 'bcount' && param.order == 'desc' ? 'asc' : 'desc'}">
                        <button>조회수 ${param.sort == 'bcount' && param.order == 'desc' ? '낮은순' : '높은순'}</button>
                    </a>
                    <a href="boardsList.do?sort=blike&order=${param.sort == 'blike' && param.order == 'desc' ? 'asc' : 'desc'}">
                        <button>좋아요 ${param.sort == 'blike' && param.order == 'desc' ? '낮은순' : '높은순'}</button>
                    </a>
                </div>
            </div>

            <!-- 게시판 테이블 -->
            <table class="board-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>카테고리</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>조회</th>
                        <th>좋아요</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="board" items="${list}">
                        <tr>
                            <td>${board.postNumber}</td>
                            <td>
                                <a href="boardDetail.do?postNumber=${board.postNumber}">${board.title}</a>
                                <c:if test="${board.ccount > 0}">
                                    <span class="comment">[${board.ccount}]</span>
                                </c:if>
                            </td>
                            <td>${board.tag}</td>
                            <td>${board.nickName}</td>
                            <td>${board.formattedCreateTime}</td>
                            <td>${board.bcount}</td>
                            <td>${board.blike}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="btn_bottom">
            <a href="./boardWriteView.do"><button class="btn_write">글쓰기</button></a>
            <!-- 페이징 -->
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="boardsList.do?page=${i}">
                        <button class="${i == currentPage ? 'active' : ''}">${i}</button>
                    </a>
                </c:forEach>
            </div>
            <a href="./allBoard.do"><button class="btn_list">목록</button></a>
            </div>
        </div>
    </div>
     <jsp:include page="./views/footer.jsp"></jsp:include>
</body>
</html>
