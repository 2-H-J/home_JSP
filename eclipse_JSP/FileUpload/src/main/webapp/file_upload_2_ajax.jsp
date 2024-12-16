<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
/* 파일 업로드 버튼을 클릭 했을때 input태그 선택 및 파일 태그 선택하게끔 진행 */
	window.onload = () => {
		document.querySelector(".btn_upload").onclick = () => {
			// type이 file인 모든 input태그 지정
			// input 태그의 name이 'file'인 태그들을 선택합니다.
			const fileList = document.querySelectorAll("input[name=file]");
			console.log("fileList : "+fileList);
			
			// type이 text인 input태그 지정
			// const txt = document.querySelector("input[name=txt]");

			// * 만약 input type이 text가(또는 다른거) 여러개 인데 
			//			file빼고 나머지 input태그들만 선택하는 방법

			// 1. JavaScript로 구현
			// 방법 1 : filter 사용
			// const nonFileInputs = Array.from(allInputs).filter(input => input.type !== 'file');
			
			// 방법 2 : CSS 선택자를 사용하여 직접 선택
			// input 태그 중 type이 file이 아닌 태그를 모두 선택합니다.
			const inputList = document.querySelectorAll('input:not([type="file"])');
			console.log("inputList : "+inputList)
			
			// 2. jQuery로 구현
			// 방법 1 : not 메서드 사용
			// const nonFileInputs = $('input').not('[type="file"]');
			
			// 방법 2 : CSS 선택자 활용
			// const nonFileInputs = $('input:not([type="file"])');
			
			// 서버쪽으로 보내주는 form데이터를 직접 담아주는 데이터 (map처럼 담아준다)
			// FormData 객체를 생성하여 데이터를 key-value 형식으로 저장합니다.
			const formData = new FormData();
			
			// => 함수로 똑같은 일 반복
			// 선택된 파일 input 태그를 순회하며 FormData에 파일 정보를 추가합니다.
			// item : 현재 순회 중인 요소입니다.
			fileList.forEach((item) => {
				// append('네임속성',item.files[0]);
				// 파일은 밸류로 들어가면 안돼서 files[0]로 파일 정보를 추가해준다
				formData.append('file', item.files[0]);
			});
			
			// file input을 제외한 나머지 input 태그를 순회하며 FormData에 추가합니다.
			inputList.forEach((item) => {
				// append('네임속성',item.value);
				// 파일은 value로 들어가지 않아 item.value로 파일 정보를 뺀 나머지 input 적용
				formData.append(item.name, item.value);
			});
			
			// fetch로 데이터 보내기
			// 데이터를 POST 방식으로 서버에 전송합니다.
			fetch("./fileUpload.do",{
				method:"POST", // 파일은 POST타입으로 보내기
				body:formData // body는 formData로 보내기
				
			}).then(response => response.json()) // 서버에서 응답받은 데이터를 JSON 형식으로 처리합니다.
			
			.then(result => { // 결과 출력
				// 전송 성공 시 결과를 출력합니다.
				console.log('전송성공(succes) : ',result);
			
			}).catch(error => { // 에러 출력
				// 전송 실패 시 에러를 출력합니다.
				console.log('전송실패(error) : ',error);
			});
			
		}
	}
</script>

</head>
<body>
	<h1>파일 업로드 테스트</h1>
	텍스트 : <input type="text" name="txt1"><br>
	텍스트 : <input type="text" name="txt2"><br>
	
	파일 : <input type="file" name="file"><br>
	파일 : <input type="file" name="file"><br>
	파일 : <input type="file" name="file"><br>
	<button type="button" class="btn_upload">파일 업로드</button>
</body>
</html>