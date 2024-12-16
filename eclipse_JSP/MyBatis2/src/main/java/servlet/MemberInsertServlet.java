package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.BoardMemberMapper;

import java.io.IOException;

import config.DBManager;
import dto.BoardMemberDTO;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/register.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 보낸 id, password, name, nickname 받기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");

		BoardMemberDTO dto = new BoardMemberDTO(id, password, name, nickname);

		BoardMemberMapper mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);

		// 데이터 등록
		int count = mapper.insertMember(dto);
		System.out.println("데이터 등록 결과 : "+count);

		// 전체 사용자 조회 페이지로 이동
		response.sendRedirect("all.do");

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
