package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 세션 데이터를 설정하고 결과 페이지로 이동하는 서블릿
 * 사용자가 전달한 메시지를 세션에 저장하고 결과 페이지로 리다이렉트합니다.
 */
@WebServlet("/session.do")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 기본 생성자
     * 서블릿 인스턴스가 생성될 때 호출됩니다.
     */
    public SessionServlet() {
        super();
        System.out.println("[SessionServlet] 생성자 호출됨 -> 인스턴스 생성됨");
    }

	/**
	 * GET 요청 처리 메서드
	 * 사용자가 전달한 메시지를 세션에 저장하고 결과 페이지로 리다이렉트합니다.
	 * 
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[SessionServlet] doGet 메서드 호출됨 -> 요청 처리 시작");

		// 세션 객체 가져오기 (없으면 새로 생성)
		HttpSession session = request.getSession();
		System.out.println("[SessionServlet] 세션 객체 가져옴: " + session.getId());

		// 요청에서 전달된 "msg" 파라미터 읽기
		String msg = request.getParameter("msg");
		System.out.println("[SessionServlet] 요청에서 msg 파라미터 값 읽음: " + msg);

		// 세션에 메시지 저장
		session.setAttribute("msg", msg);
		System.out.println("[SessionServlet] 세션에 msg 속성 추가됨");

		// 세션 유효시간 설정 (기본값 사용 주석 처리)
		// session.setMaxInactiveInterval(1800); // 세션 유효 시간 30분으로 설정 (초 단위)
		System.out.println("[SessionServlet] 세션 유효 시간 설정은 기본값 유지");

		// 결과 페이지로 리다이렉트
		response.sendRedirect("session_result.jsp");
		System.out.println("[SessionServlet] session_result.jsp로 리다이렉트 완료");
	}

	/**
	 * POST 요청 처리 메서드
	 * POST 요청을 동일한 로직으로 처리하기 위해 doGet 메서드로 위임합니다.
	 * 
	 * @param request  클라이언트의 HTTP 요청 객체
	 * @param response 서버의 HTTP 응답 객체
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[SessionServlet] doPost 메서드 호출됨 -> doGet 메서드로 위임");
		doGet(request, response); // POST 요청은 GET 요청과 동일하게 처리
	}
}
