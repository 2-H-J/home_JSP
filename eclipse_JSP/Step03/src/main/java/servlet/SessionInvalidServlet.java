package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 세션 무효화를 처리하는 서블릿
 * 사용자의 세션을 무효화하고 결과 페이지로 리다이렉트합니다.
 */
@WebServlet("/invalidate.do")
public class SessionInvalidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 생성자
     * 서블릿 초기화를 위한 기본 생성자입니다.
     */
    public SessionInvalidServlet() {
        super();
        System.out.println("[SessionInvalidServlet] 생성자 호출됨 -> 인스턴스 생성됨");
    }

	/**
	 * GET 요청 처리 메서드
	 * 사용자의 세션을 무효화하고 결과 페이지로 리다이렉트합니다.
	 * 
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[SessionInvalidServlet] doGet 메서드 호출됨 -> 세션 무효화 작업 시작");

		// 현재 사용자의 세션을 가져옴 (없으면 새로 생성하지 않음)
		HttpSession session = request.getSession();
		if (session != null) {
			// 세션을 무효화하여 사용자 로그아웃 상태로 만듦
			session.invalidate();
			System.out.println("[SessionInvalidServlet] 세션 무효화됨");
		} else {
			System.out.println("[SessionInvalidServlet] 세션이 존재하지 않음");
		}

		// 무효화 결과 페이지로 리다이렉트
		response.sendRedirect("session_result.jsp");
		System.out.println("[SessionInvalidServlet] session_result.jsp로 리다이렉트 완료");
	}

	/**
	 * POST 요청 처리 메서드
	 * POST 요청을 동일한 로직으로 처리하기 위해 doGet 메서드로 위임합니다.
	 * 
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[SessionInvalidServlet] doPost 메서드 호출됨 -> doGet 메서드로 위임");
		doGet(request, response); // POST 요청은 GET 요청과 동일하게 처리
	}
}
