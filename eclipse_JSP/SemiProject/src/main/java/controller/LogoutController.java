package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

/**
 * LogoutController 클래스는 사용자의 로그아웃을 처리하는 컨트롤러 역할을 수행한다.
 * Controller 인터페이스를 구현하여 특정 요청을 처리하고, 그 결과를 반환한다.
 */
public class LogoutController implements Controller {

	/**
	 * execute 메서드는 로그아웃 로직을 처리하고, 처리 결과를 기반으로 사용자에게 적합한 화면(view)을 반환한다.
	 *
	 * @param request  클라이언트로부터 들어온 HTTP 요청 객체
	 * @param response 클라이언트로 전송할 HTTP 응답 객체
	 * @return ModelAndView 객체 (화면 정보 및 전환 방식을 포함)
	 * @throws ServletException 서블릿 처리 중 발생할 수 있는 예외
	 * @throws IOException      입출력 처리 중 발생할 수 있는 예외
	 */
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[LogoutController] execute() 호출 -> 로그아웃 처리 시작");

		// 1. 현재 사용자의 세션 객체를 가져온다.
		System.out.println("[LogoutController] 요청 URI: " + request.getRequestURI());
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			System.out.println("[LogoutController] 현재 세션 ID: " + session.getId());
			session.invalidate(); // 세션을 무효화하여 모든 세션 데이터를 제거한다.
			System.out.println("[LogoutController] 세션 무효화 완료");
			
		} else {
			System.out.println("[LogoutController] 유효한 세션이 존재하지 않음");
		}

		// 2. 클라이언트로부터 전송된 쿠키를 가져온다.
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			System.out.println("[LogoutController] 요청된 쿠키 수: " + cookies.length);
			
			for (Cookie cookie : cookies) {
				System.out.println("[LogoutController] 쿠키 이름: " + cookie.getName() + ", 값: " + cookie.getValue());
				
				if ("loginId".equals(cookie.getName())) {
					cookie.setMaxAge(0); // 쿠키의 유효기간을 0으로 설정하여 삭제
					cookie.setPath("/"); // 해당 쿠키를 모든 경로에서 삭제하도록 설정
					response.addCookie(cookie); // 응답에 삭제된 쿠키를 추가
					System.out.println("[LogoutController] 'loginId' 쿠키 삭제 완료");
				}
			}
		} else {
			System.out.println("[LogoutController] 요청된 쿠키가 없음");
		}

		// 3. 로그아웃 처리 후 이동할 화면 설정
		ModelAndView view = new ModelAndView(); // 화면 정보 객체 생성
		view.setPath("./index.jsp"); // 이동할 페이지를 "signin.jsp"로 설정
		view.setRedirect(true); // 리다이렉트 방식으로 화면 전환
		System.out.println("[LogoutController] 로그아웃 처리 완료 -> 이동할 페이지: " + view.getPath() + ", 리다이렉트: " + view.isRedirect());

		return view; // ModelAndView 객체 반환
	}
}
