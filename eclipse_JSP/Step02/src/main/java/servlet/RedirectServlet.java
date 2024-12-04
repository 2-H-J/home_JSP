package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/redirect.do")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터 준비--------------------------------------------------
		// 클라이언트로부터 전달된 "name"과 "age" 파라미터를 받아옵니다.
		String name = request.getParameter("name");
		// "age"는 정수형으로 변환하여 사용합니다. 파라미터가 없거나 잘못된 경우 예외가 발생할 수 있으니 실제로는 예외 처리가 필요합니다.
		int age = Integer.parseInt(request.getParameter("age"));

		// "name"과 "age" 데이터를 합쳐서 하나의 문자열로 만듭니다.
		String result = "이름 : " + name + " / 나이 : " + age;
		// 콘솔에 결과를 출력합니다. 주로 디버깅 용도로 사용됩니다.
		System.out.println(result);

		// ---------------------------------------------------------------

		// 데이터 셋팅
		// request.setAttribute("키값", 데이터);
		// request 객체에 결과 데이터를 "msg"라는 이름으로 추가합니다.
		// forward된 페이지에서 이 데이터를 사용할 수 있습니다.
		request.setAttribute("msg", result);
		
		// Redirect 방식 페이지 이동
		// redirect는 새로운 경로를 요청하는 것이기 때문에 기존의 request 객체는 사라짐
		// 새 request가 만들어짐, 그래서 주소창에 url이 바뀐것을 볼 수 있음.
		
		// response.sendRedirect("이동할 페이지 경로"); - 새로운 경로 요청을 클라이언트에게 보냄
		// 리다이렉트 방식은 클라이언트가 새로운 요청을 하게 하므로 기존 주소창이 변경됩니다.
		response.sendRedirect("forward_result.jsp");
//		response.sendRedirect("forward_result.jsp?이름 = " + name + " / 나이 = " + age);
		// 리다이렉트 방식은 새로운 요청이 발생하므로 request의 데이터는 유지되지 않습니다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
