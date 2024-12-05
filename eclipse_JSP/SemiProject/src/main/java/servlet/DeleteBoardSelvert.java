package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.BoardsMapper;

import java.io.IOException;
import java.util.List;

import config.DBManager;
import dto.BoardsDTO;

/**
 * Servlet implementation class DeleteBoardSelvert
 */
@WebServlet("/deleteBoard.do")
public class DeleteBoardSelvert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBoardSelvert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardsMapper mapper = DBManager.getInstance().getSession().getMapper(BoardsMapper.class);
		String postNumberStr = request.getParameter("postNumber");
		int postNumber = Integer.parseInt(postNumberStr);
		int result = mapper.deleteBoardByPostNumber(postNumber);
		System.out.println("deleteboard");
		System.out.println(postNumber);
		System.out.println(result);

		if (result > 0) {

			System.out.println("게시글 삭제 성공");
			response.sendRedirect("./allBoard.do");
		} else {
			response.getWriter().println("게시글 삭제 실패. 존재하지 않는 게시글 번호입니다.");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
