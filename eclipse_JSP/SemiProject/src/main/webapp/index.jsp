<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.UsersDTO"%>
<%@ page import="service.UsersService"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="jakarta.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<!-- 공통 헤더 -->
	<jsp:include page="header.html" />

	<%
	// 세션 확인 및 복구
	if (session == null || session.getAttribute("user") == null) {
		System.out.println("[MainPage] 세션이 없거나 사용자 정보가 없음 -> 쿠키 확인 시작");

		// 세션이 없거나 사용자 정보가 없을 경우 쿠키 확인
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("loginId".equals(cookie.getName())) {
					String loginId = cookie.getValue();
					System.out.println("[MainPage] 쿠키에서 loginId 확인: " + loginId);

					// 데이터베이스에서 사용자 정보 조회
					UsersDTO user = UsersService.getInstance().getUserByLoginId(loginId);
					if (user != null) {
						// 세션 복구
						session = request.getSession();
						session.setAttribute("user", user);
						session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.MINUTES));
						session.setAttribute("rememberMe", true); // "로그인 상태 유지" 사용자
						System.out.println("[MainPage] 세션 복구 완료 - 사용자: " + user.getNickName());
					} else {
						System.out.println("[MainPage] 쿠키 loginId에 해당하는 사용자 정보 없음");
					}
					break;
				}
			}
		}
	}

	// 세션 만료 시간 확인 및 처리
	if (session != null && session.getAttribute("user") != null) {
		Instant sessionExpireTime = (Instant) session.getAttribute("sessionExpireTime");
		Boolean rememberMe = (Boolean) session.getAttribute("rememberMe");

		// "로그인 상태 유지" 사용자는 세션 만료 시간 무시
		if (rememberMe != null && rememberMe) {
			System.out.println("[MainPage] 로그인 상태 유지 사용자는 세션 만료 시간 무시");
		} else if (sessionExpireTime != null && sessionExpireTime.isBefore(Instant.now())) {
			// 일반 사용자의 세션 만료 처리
			System.out.println("[MainPage] 세션 만료 - 사용자 로그아웃 처리");
			session.invalidate();
			response.sendRedirect("signin.jsp?error=sessionExpired");
			return;
		}

		// 세션이 유효할 경우 사용자 정보 출력
		UsersDTO user = (UsersDTO) session.getAttribute("user");
	%>
	<p>
		안녕하세요, <b><%=user != null ? user.getNickName() : "게스트"%></b> 님!
	</p>
	<form action="logout.do" method="post">
		<button type="submit">로그아웃</button>
	</form>
	<a href="./updateUser.do">내 정보 수정</a>
	<%
	} else {
		// 사용자 정보 없음 -> 로그인 버튼 제공
		System.out.println("[MainPage] 사용자 정보 없음 -> 로그인 페이지 링크 제공");
	}
	%>
<%-- 	<a href="signin.jsp"><button>로그인</button></a>
	<%
	}
	%> --%>

	
</body>
</html>
