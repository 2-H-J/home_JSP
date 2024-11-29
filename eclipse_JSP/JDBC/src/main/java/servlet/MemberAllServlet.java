package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import dao.BoardMemberDAO;
import dto.BoardMemberDTO;

/**
 * Servlet implementation class MemberAllServlet
 */
@WebServlet("/all.do")
public class MemberAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. DAO에서 전체 회원정보를 받음
		ArrayList<BoardMemberDTO> list = BoardMemberDAO.getInstance().selectAllMember();
		System.out.println(list);
		//2. request영역에 회원정보가 있는 리스트를 저장
		request.setAttribute("list", list);
		//3. 페이지 이동 - forward, member_list.jsp
		request.getRequestDispatcher("member_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
