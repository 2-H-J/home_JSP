package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mapper.UsersMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import config.DBManager;
import dto.UsersDTO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		System.out.println(loginId);
		String password = request.getParameter("password");
		
        // 1. 파라미터를 맵으로 준비
        Map<String, Object> params = new HashMap<>();
        params.put("loginId", loginId);
        params.put("password", password);
        
        // 2. MyBatis 세션을 통해 로그인 사용자 정보 조회
        try (var session = DBManager.getInstance().getSession()) {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            UsersDTO user = mapper.selectUserLogin(params);
            System.out.println(user);
            System.out.println(loginId);
            System.out.println(password);

            if (user != null) {
                // 로그인 성공 - 세션에 사용자 정보를 저장
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user);
                response.sendRedirect("./login.jsp");  // 메인 페이지로 리다이렉트
            } else {
                // 로그인 실패 - 로그인 페이지로 리다이렉트
                response.sendRedirect("login.jsp?error=true");
            }
        }
	}

}
