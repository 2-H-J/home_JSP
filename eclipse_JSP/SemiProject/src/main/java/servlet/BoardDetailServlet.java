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
 * Servlet implementation class boardDetail
 */
@WebServlet("/boardDetail.do")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNumberStr = request.getParameter("postNumber");
		int postNumber = Integer.parseInt(postNumberStr);
		BoardsMapper mapper = DBManager.getInstance().getSession().getMapper(BoardsMapper.class);
		List<BoardsDTO> boardList = mapper.selectBoardByPostNumber(postNumber);
		System.out.println("postNumber 값 : " + postNumber);
		System.out.println("postNumber 값 : " + boardList);
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("boardDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
