package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet("/person.do") // 이 서블릿이 매핑될 URL 경로 설정
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(name + " " + age);
		
		// 문자코드 변경
//		response.setContentType("text/html;charset=utf-8");
		
		
		// 클라이언트에게 데이터 전송
		PrintWriter pw = response.getWriter();
		pw.println("<h2>이름 : "+name + " / 나이 : " + age+"</h2>");
		// 문자코드 변경 없이 실행하면 서버랑 문자코드가 달라서 한글이 깨짐 문자코드 변경해야함
		// 문자코드 변경 방법 : 위와 같이 response.setContentType("text/html;charset=utf-8");
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
