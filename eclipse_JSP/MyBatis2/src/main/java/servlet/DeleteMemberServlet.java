package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;

import java.io.IOException;

@WebServlet("/deleteMember.do")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 삭제할 아이디값 받음
		String id = request.getParameter("id");
		
		// 2. 서비스로 삭제할 아이디값 전달
		int count = BoardMemberService.getInstance().deleteMember(id);
		System.out.println("삭제 결과 : "+count);
		
		// 3. 전체 회원 정보 조회하는 페이지로 이동(서블릿을 호출)
		response.sendRedirect("all.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
