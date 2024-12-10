package servlet;

// 필요한 클래스와 인터페이스를 가져오기
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UsersService;
import view.ModelAndView;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import controller.Controller;
import controller.HandlerMapping;
import dto.UsersDTO;

/**
 * DispatcherServlet 클래스는 클라이언트 요청을 처리하고, 적절한 Controller를 호출하여 로직을 실행한 뒤
 * 결과(ModelAndView)를 처리합니다. 또한 사용자 세션 관리 및 로그인 상태 유지 기능을 포함합니다.
 */
@WebServlet("*.do") // 모든 ".do" 확장자로 끝나는 요청을 처리
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 기본 생성자: DispatcherServlet 인스턴스를 생성
	 */
	public DispatcherServlet() {
		super();
		System.out.println("[DispatcherServlet] 생성자 호출됨 -> DispatcherServlet 인스턴스 생성됨");
	}

	/**
	 * HTTP GET 요청을 처리하는 메서드. 세션 관리, 사용자 인증 상태 확인, Controller 호출, 결과 처리 등을 수행.
	 *
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 세션 확인 및 복구 로직
		HttpSession session = request.getSession(false); // 기존 세션을 가져옴 (없으면 null 반환)
		boolean sessionRestored = false; // 세션 복구 여부를 추적

		// 세션이 없거나 사용자 정보가 없는 경우
		if (session == null || session.getAttribute("user") == null) {
			System.out.println("[DispatcherServlet] 세션 없음 -> 쿠키 확인 및 복구 시도");

			// 요청에 포함된 쿠키 확인
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					// "loginId" 쿠키를 찾음
					if ("loginId".equals(cookie.getName())) {
						String loginId = cookie.getValue();

						// 로그인 ID를 통해 사용자 정보를 조회
						UsersDTO user = UsersService.getInstance().getUserByLoginId(loginId);

						if (user != null) {
							// 세션 생성 및 사용자 정보 저장
							session = request.getSession(true);
							session.setAttribute("user", user);

							// "로그인 상태 유지" 사용자라면 세션 만료 시간 연장
							session.setAttribute("rememberMe", true);
							sessionRestored = true;
							System.out.println("[DispatcherServlet] 로그인 상태 유지 사용자 세션 복구 완료: " + user.getNickName());
						}
						break; // 쿠키 반복문 종료
					}
				}
			}
		}

		// 2. 기존 세션이 있는 경우 세션 만료 및 연장 처리
		if (session != null && !sessionRestored) {
			Instant expireTime = (Instant) session.getAttribute("sessionExpireTime");
			Boolean rememberMe = (Boolean) session.getAttribute("rememberMe");

			if (rememberMe != null && rememberMe) {
				// "로그인 상태 유지" 사용자는 세션 만료 시간 무시
				System.out.println("[DispatcherServlet] 로그인 상태 유지 사용자는 세션 만료 시간 무시");
			} else if (expireTime != null && Instant.now().isAfter(expireTime)) {
				// 일반 사용자의 세션이 만료된 경우 로그아웃 처리
				session.invalidate();
				System.out.println("[DispatcherServlet] 세션 만료 -> 로그아웃 처리");
				session = null;
			} else {
				// 일반 사용자의 세션 만료 시간 연장
				session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.SECONDS));
				System.out.println("[DispatcherServlet] 일반 사용자 세션 연장");
			}
		}

		// 3. 클라이언트 요청(command) 처리
		String[] path = request.getRequestURI().split("/"); // URI를 "/" 기준으로 분리
		String command = path[path.length - 1].replace(".do", ""); // ".do" 제거하여 명령어 추출

		// HandlerMapping을 통해 적절한 Controller 생성
		Controller controller = HandlerMapping.getInstance().createController(command);
		ModelAndView view = null;

		if (controller != null) {
			// Controller 실행 및 결과(ModelAndView) 반환
			view = controller.execute(request, response);
		}

		// 4. ModelAndView 결과 처리
		if (view != null) {
			// 모델 데이터를 request 스코프에 저장
			for (String key : view.getModel().keySet()) {
				request.setAttribute(key, view.getModel().get(key));
			}

			// 이동 방식 처리 (리다이렉트 또는 포워드)
			if (view.isRedirect()) {
				response.sendRedirect(view.getPath()); // 클라이언트에게 리다이렉트 응답
			} else {
				request.getRequestDispatcher(view.getPath()).forward(request, response); // 서버 내부에서 포워드
			}
		}
	}

	/**
	 * HTTP POST 요청을 처리하는 메서드. POST 요청도 doGet 메서드로 전달하여 동일한 로직을 처리.
	 *
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // POST 요청을 doGet으로 전달
	}
}
