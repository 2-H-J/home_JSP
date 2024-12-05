<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.UsersDTO"%>
<%@ page import="service.UsersService"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<ul>
		<li><a href="./allUser.do">전체 회원 정보 조회</a></li>
		<li><a href="./allBoard.do">전체 게시판 정보 조회</a></li>
	</ul>

	<%
	// 세션 확인
	if (session == null || session.getAttribute("user") == null) {
		System.out.println("[MainPage] 세션이 없거나 사용자 정보가 없음, 쿠키 확인 중");
		// 세션이 없거나 유저 정보가 없을 경우 쿠키를 확인
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
				System.out.println("[MainPage] 세션 복구 완료 - 사용자: " + user.getNickName());
			}
			break;
		}
			}
		}
	}

	// 세션 만료 시간 확인
	if (session != null && session.getAttribute("user") != null) {
		Instant sessionExpireTime = (Instant) session.getAttribute("sessionExpireTime");
		if (sessionExpireTime != null && sessionExpireTime.isBefore(Instant.now())) {
			// 세션 만료 처리
			System.out.println("[MainPage] 세션 만료 - 사용자 로그아웃 처리");
			session.invalidate();
			response.sendRedirect("signin.jsp?error=sessionExpired");
			return;
		}
		UsersDTO user = (UsersDTO) session.getAttribute("user");
	%>
	<p>
		안녕하세요, <b><%=user.getNickName()%></b> 님!
	</p>
	<form action="logout.do" method="post">
		<button type="submit">로그아웃</button>
	</form>
	<%
	} else {
	System.out.println("[MainPage] 사용자 정보 없음, 로그인 페이지 링크 제공");
	%>
	<a href="signin.jsp"><button>로그인</button></a>
	<%
	}
	%>
</body>
</html>

<!-- 이 JSP 페이지는 사용자 세션을 확인하고, 세션이 없을 경우 쿠키를 확인하여 사용자를 자동으로 로그인시키려고 시도합니다. -->
<!-- 세션이 유효할 경우 사용자에게 인사말을 표시하고 로그아웃 버튼을 제공합니다. -->
<!-- 세션이 만료되었거나 유효하지 않을 경우 로그인 버튼을 표시하며, 모든 과정에서 로그를 남겨 흐름을 추적할 수 있습니다. -->
