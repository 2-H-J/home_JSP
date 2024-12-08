package servlet;

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

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
		super();
		System.out.println("[DispatcherServlet] 생성자 호출됨 -> DispatcherServlet 인스턴스 생성됨");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		boolean sessionRestored = false;

		// 세션이 없거나 사용자 정보가 없을 경우
		if (session == null || session.getAttribute("user") == null) {
			System.out.println("[DispatcherServlet] 세션 없음 -> 쿠키 확인 및 복구 시도");
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("loginId".equals(cookie.getName())) {
						String loginId = cookie.getValue();
						UsersDTO user = UsersService.getInstance().getUserByLoginId(loginId);

						if (user != null) {
							// 세션 복구
							session = request.getSession(true); // 새 세션 생성
							session.setAttribute("user", user);

							// "로그인 상태 유지" 사용자라면 세션 만료 시간 연장
							session.setAttribute("rememberMe", true);
							sessionRestored = true;
							System.out.println("[DispatcherServlet] 로그인 상태 유지 사용자 세션 복구 완료: " + user.getNickName());
						}
						break;
					}
				}
			}
		}

		// 기존 세션이 있는 경우
		if (session != null && !sessionRestored) {
			Instant expireTime = (Instant) session.getAttribute("sessionExpireTime");
			Boolean rememberMe = (Boolean) session.getAttribute("rememberMe");

			// "로그인 상태 유지" 사용자는 세션 만료 시간 무시
			if (rememberMe != null && rememberMe) {
				System.out.println("[DispatcherServlet] 로그인 상태 유지 사용자는 세션 만료 시간 무시");
			} else if (expireTime != null && Instant.now().isAfter(expireTime)) {
				// 일반 사용자의 세션 만료 처리
				session.invalidate();
				System.out.println("[DispatcherServlet] 세션 만료 -> 로그아웃 처리");
				session = null;
			} else {
				// 일반 사용자의 세션 연장
				session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.SECONDS));
				System.out.println("[DispatcherServlet] 일반 사용자 세션 연장");
			}
		}

		// 기존 로직 실행
		String[] path = request.getRequestURI().split("/");
		String command = path[path.length - 1].replace(".do", "");

		Controller controller = HandlerMapping.getInstance().createController(command);
		ModelAndView view = null;

		if (controller != null) {
			view = controller.execute(request, response);
		}

		if (view != null) {
			for (String key : view.getModel().keySet()) {
				request.setAttribute(key, view.getModel().get(key));
			}

			if (view.isRedirect()) {
				response.sendRedirect(view.getPath());
			} else {
				request.getRequestDispatcher(view.getPath()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}