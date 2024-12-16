<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로또 번호 생성기</title> <!-- 페이지의 제목을 설정합니다. -->
</head>
<body>
    <h2>로또 셋트 개수 입력</h2>
    <!-- 사용자가 원하는 로또 셋트 개수를 입력받는 폼 -->
    <form action="lotto.do" method="post">
        <!-- action="lotto.do"는 데이터를 처리할 서블릿의 경로를 지정합니다. -->
        <!-- method="post"는 데이터를 전송하는 방식을 지정합니다. 여기서는 POST 방식을 사용합니다. -->
        
        <!-- 로또 셋트 개수를 입력하는 필드 -->
        <label for="count">원하는 로또 셋트 개수를 입력하세요:</label>
        <!-- label의 for 속성은 입력 필드의 id와 연결되어 있습니다. -->
        <input type="number" id="count" name="lotto_count" min="1" required>
        <!-- input 태그:
             - type="number": 숫자만 입력 가능하게 설정합니다.
             - id="count": 이 입력 필드의 고유 식별자입니다.
             - name="lotto_count": 폼 전송 시 서버에 전달되는 파라미터 이름입니다.
             - min="1": 최소 입력 값은 1입니다.
             - required: 이 필드는 반드시 입력해야 함을 의미합니다. -->
        
        <!-- 제출 버튼 -->
        <button type="submit">로또 뽑기!</button>
        <!-- button 태그:
             - type="submit": 폼을 제출하기 위한 버튼임을 지정합니다. -->
    </form>
</body>
</html>
