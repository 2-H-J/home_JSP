<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>네이버 이미지 검색</title>
<style>
h2 {
	text-align: center;
}

#searchForm {
	text-align: center;
}

#searchResult {
	width: 1200px;
	margin: 0 auto;
	display: flex;
	flex-flow: row wrap;
	justify-content: space-between;
	gap: 10px;
}

#searchResult>div {
	max-width: 300px;
}

#searchResult>div  img {
	width: 100%;
}
</style>
<script>
    window.onload = () => {
      const txtSearch = document.querySelector('#txtSearch');
      const btnSearch = document.querySelector('#btnSearch');
      const searchResult = document.querySelector('#searchResult');

      btnSearch.onclick = () => {
          const searchValue = txtSearch.value;
          /* const url = `./naverImgSearch.do?search=\${}`; */
          const url = `./naverImgSearch.do?search=${searchValue}`;
         /*  백틱(\``) 내부에 변수 searchValue`를 사용하지 않아 올바른 URL이 생성되지 않습니다 */


          fetch(url)
          .then(response => response.json())
          .then(json => {
            console.log(json);
            const items = json.items;
            let tag ='';
            items.forEach(item => {
          	    tag += '<div>';
          	    tag += `<img src='${item.thumbnail}'/>`; // thumbnail 섬네일이미지 URL 추가
          	    tag += `<p>${item.title}</p>`; // 이미지 제목 추가
          	    tag += '</div>';
          	});
            /* 
            JavaScript 코드에서 네이버 API의 JSON 응답 데이터를 활용하지 못하고 있습니다. 
            JSON 응답 데이터의 구조에 따라 이미지 URL과 설명을 렌더링하도록 수정
            */

            searchResult.innerHTML = tag;
            
          });
        };

    }
  </script>
</head>
<body>
	<h2>네이버 이미지 검색 페이지</h2>
	<div id="searchForm">
		<input type="text" id="txtSearch" placeholder="검색어 입력" />
		<button id="btnSearch">검색</button>
	</div>
	<hr>
	<div id="searchResult"></div>
</body>
</html>