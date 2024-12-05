package servlet;

import java.io.IOException;

import config.DBManager;
import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mapper.BoardsMapper;

/**
 * Servlet implementation class insertBoardServlet
 */
@WebServlet("/insertBoard.do")
public class insertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public insertBoardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		// HttpSession session = request.getSession();
		int userNumber = 1;
		System.out.println(userNumber);
		BoardsDTO dto = new BoardsDTO(userNumber, title, description);
		BoardsMapper mapper = DBManager.getInstance().getSession().getMapper(BoardsMapper.class);

		// 데이터 등록
		int count = mapper.insertBoard(dto);
		System.out.println("데이터 등록 결과 : " + count);
		// 전체 사용자 조회 페이지로 이동
		response.sendRedirect("./allBoard.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}