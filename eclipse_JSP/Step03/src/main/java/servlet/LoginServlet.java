package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 로그인 기능을 구현하는 서블릿 클래스
 * URL 매핑: "/login.do"
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 생성자: 서블릿이 생성될 때 초기화하는 역할을 담당함
     */
    public LoginServlet() {
        super();
    }

    /**
     * GET 요청을 처리하는 메소드
     * 로그인 페이지로부터 요청이 들어왔을 때 아이디와 비밀번호를 검증하고, 로그인 결과에 따라 응답함
     * @param request 클라이언트의 요청을 담고 있는 객체
     * @param response 서버가 클라이언트에 보내는 응답을 담고 있는 객체
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 현재 사용자의 세션을 가져옴. 세션이 없으면 새로 생성
        HttpSession session = request.getSession();
        
        // 요청 파라미터에서 id와 passwd 값을 받아옴
        String id = request.getParameter("id"); // 사용자가 입력한 아이디
        String passwd = request.getParameter("passwd"); // 사용자가 입력한 비밀번호

        // 기본적으로 이동할 페이지 설정 (로그인 실패 시 로그인 페이지로 이동)
        String path = "03_login.jsp";

        // 로그인 정보 검증
        if (id.equals("admin") && passwd.equals("123456")) {
            // 아이디와 비밀번호가 일치하는 경우
            path = "login_result.jsp"; // 로그인 성공 시 이동할 페이지 설정
            session.setAttribute("msg", "로그인에 성공하셨습니다."); // 세션에 성공 메시지 저장
            response.sendRedirect(path); // 성공 시 리다이렉트하여 페이지 이동
        } else {
            // 아이디와 비밀번호가 일치하지 않는 경우
            response.setContentType("text/html;charset=utf-8"); // 응답 콘텐츠 타입 설정 (한글 처리)
            response.getWriter().println("<script>"
                    + "alert('아이디와 비밀번호를 확인하세요');" // 경고창 출력
                    + "location.href='./03_login.jsp';" // 로그인 페이지로 다시 이동
                    + "</script>");
        }
    }

    /**
     * POST 요청을 처리하는 메소드
     * doGet() 메소드를 호출하여 POST 요청도 동일한 로직으로 처리
     * @param request 클라이언트의 요청을 담고 있는 객체
     * @param response 서버가 클라이언트에 보내는 응답을 담고 있는 객체
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청이 들어왔을 때도 doGet() 메소드를 호출하여 동일하게 처리
        doGet(request, response);
    }

}
