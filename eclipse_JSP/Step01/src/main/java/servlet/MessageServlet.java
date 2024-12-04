package servlet;

// 필요한 Jakarta Servlet 라이브러리 임포트
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MessageServlet 클래스는 간단한 HTTP 요청 및 응답 처리를 위한 서블릿 클래스입니다. "/send.do" URL 패턴으로
 * 요청을 처리합니다.
 */
//경로 설정
@WebServlet("/send.do") // 이 서블릿이 매핑될 URL 경로 설정
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 기본 생성자 서블릿 객체 생성 시 필요한 초기 작업을 처리합니다.
	 */
	public MessageServlet() { // 생성자
		super(); // 부모 클래스의 생성자 호출
		// TODO Auto-generated constructor stub
	}

	/**
	 * GET 요청을 처리하는 메서드입니다. 클라이언트가 GET 요청을 보낼 때 호출됩니다.
	 * 
	 * @param request  클라이언트의 요청 객체
	 * @param response 서버의 응답 객체
	 * @throws ServletException 서블릿 오류가 발생했을 때 예외 발생
	 * @throws IOException      입출력 오류가 발생했을 때 예외 발생
	 */
	// HttpServletRequest : 요청 / HttpServletResponse : 응답
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트에서 전송된 "txt" 파라미터 값을 콘솔에 출력합니다.
		// 예를 들어, URL에 "/send.do?txt=hello"와 같이 보내면 "hello"가 출력됩니다.
		System.out.println(request.getParameter("txt"));
	}

	/**
	 * POST 요청을 처리하는 메서드입니다. 클라이언트가 POST 요청을 보낼 때 호출됩니다.
	 * 
	 * @param request  클라이언트의 요청 객체
	 * @param response 서버의 응답 객체
	 * @throws ServletException 서블릿 오류가 발생했을 때 예외 발생
	 * @throws IOException      입출력 오류가 발생했을 때 예외 발생
	 */
	// doPost : doGet 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 요청을 처리하기 위해 doGet 메서드를 호출하여 동일한 처리를 하도록 위임합니다.
		// 즉, GET 요청과 POST 요청에 대해 동일한 처리를 수행합니다.
		doGet(request, response);
	}
}
