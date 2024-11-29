package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//파라미터 받아옴
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		//처리
		String path = "03_login.jsp";
		if(id.equals("admin") && passwd.equals("123456")) {
			path = "login_result.jsp";
			session.setAttribute("msg", "로그인에 성공하셨습니다.");
			//페이지 이동
			response.sendRedirect(path);
		}else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("<script>"
					+ "alert('아이디와 비밀번호 확인하세요');"
					+ "location.href='./03_login.jsp';"
					+ "</script>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
