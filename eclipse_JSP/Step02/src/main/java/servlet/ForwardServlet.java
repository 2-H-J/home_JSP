package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet("/forward.do")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForwardServlet() {
		super();
		// TODO Auto-generated constructor stub
		// 기본 생성자 - 서블릿이 생성될 때 호출됩니다.
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터 준비--------------------------------------------------
		// 클라이언트로부터 전달된 "name"과 "age" 파라미터를 받아옵니다.
		String name = request.getParameter("name");
		// "age"는 정수형으로 변환하여 사용합니다. 파라미터가 없거나 잘못된 경우 예외가 발생할 수 있으니 실제로는 예외 처리가 필요합니다.
		int age = Integer.parseInt(request.getParameter("age"));
		
		// "name"과 "age" 데이터를 합쳐서 하나의 문자열로 만듭니다.
		String result = "이름 : "+ name + " / 나이 : " + age;
		// 콘솔에 결과를 출력합니다. 주로 디버깅 용도로 사용됩니다.
		System.out.println(result);
		
		//---------------------------------------------------------------
		
		// 데이터 셋팅
		// request.setAttribute("키값", 데이터);
		// request 객체에 결과 데이터를 "msg"라는 이름으로 추가합니다. 
		// forward된 페이지에서 이 데이터를 사용할 수 있습니다.
		request.setAttribute("msg", result);
		
		// 페이지 이동 - forward 방식-----------------------------------
		// 사용자의 요청사항을 그대로 가지고 감
		// 최초에 호출했던 URL이 주소창에 남아있음
		
		// request.getRequestDispatcher("이동할 페이지 경로"); - 페이지에서 데이터 출력
		
		// RequestDispatcher 객체를 사용하여 특정 페이지(forward_result.jsp)로 이동합니다.
		// forward 방식은 서버 내에서 요청을 다른 자원(JSP 등)으로 넘기는 방식입니다.
		// 요청(request)과 응답(response)을 그대로 유지하여 이동합니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("forward_result.jsp");
		dispatcher.forward(request, response);
		// forward는 서버 내에서만 이동하므로 클라이언트의 URL은 변경되지 않습니다.
		//---------------------------------------------------------------
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doPost 메서드에서는 doGet 메서드를 호출하여 GET과 POST 방식 모두 동일한 로직을 수행하도록 설정합니다.
		doGet(request, response);
	}

}
