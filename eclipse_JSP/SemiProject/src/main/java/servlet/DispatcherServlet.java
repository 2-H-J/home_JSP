package servlet;

// 필요한 클래스와 인터페이스를 가져오기
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

import java.io.IOException;
import java.util.Iterator;

import controller.Controller;
import controller.HandlerMapping;

/**
 * DispatcherServlet 클래스는 클라이언트 요청을 처리하고, 적절한 Controller를 호출하여 로직을 실행한 뒤
 * 결과(ModelAndView)를 처리합니다. 또한 사용자 세션 관리 및 로그인 상태 유지 기능을 포함합니다.
 */
@WebServlet("*.do") // 모든 ".do" 확장자로 끝나는 요청을 처리
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * DispatcherServlet 생성자. 부모 클래스(HttpServlet)의 생성자를 호출.
	 */
	public DispatcherServlet() {
		super();
	}

	/**
	 * HTTP GET 요청을 처리하는 메서드. 클라이언트로부터 전달된 요청을 분석하여 적절한 컨트롤러를 호출하고, 그 결과를 기반으로
	 * 응답합니다.
	 *
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DispatcherServlet: GET 요청 처리 시작");

		// 1. 요청 URI에서 명령(command) 추출
		String[] path = request.getRequestURI().split("/"); // URI를 "/" 기준으로 분리
		String command = path[path.length - 1].replace(".do", ""); // ".do"를 제거하여 명령어 추출
		System.out.println("추출된 명령어: " + command);

		// 2. HandlerMapping을 통해 해당 명령어에 맞는 Controller 생성
		Controller controller = HandlerMapping.getInstance().createController(command);
		if (controller != null) {
			System.out.println("생성된 컨트롤러: " + controller.getClass().getSimpleName());
		} else {
			System.out.println("명령어에 매칭되는 컨트롤러를 찾을 수 없음: " + command);
		}

		// 3. 컨트롤러의 execute 메서드를 호출하여 로직 실행
		// HandlerMapping을 통해 적절한 Controller 생성
		ModelAndView view = null; // ModelAndView 객체 초기화
		if (controller != null) {
			view = controller.execute(request, response); // 요청과 응답 객체를 전달
			System.out.println("컨트롤러 실행 완료. ModelAndView 경로: " + view.getPath());
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
	 * HTTP POST 요청을 처리하는 메서드. POST 요청도 doGet 메서드로 전달하여 동일한 로직을 처리합니다.
	 *
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DispatcherServlet: POST 요청 처리 시작");
		doGet(request, response); // POST 요청을 doGet으로 전달
	}
}
