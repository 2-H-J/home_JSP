package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.BoardsMapper;
import mapper.UsersMapper;

import java.io.IOException;
import java.util.List;

import config.DBManager;
import dto.BoardsDTO;
import dto.UsersDTO;

/**
 * Servlet implementation class BoardsServlet
 */
@WebServlet("/allBoard.do")
public class BoardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardsMapper mapper = DBManager.getInstance().getSession().getMapper(BoardsMapper.class);
		List<BoardsDTO> list = mapper.selectAllBoards();
		System.out.println(list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("boards_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
