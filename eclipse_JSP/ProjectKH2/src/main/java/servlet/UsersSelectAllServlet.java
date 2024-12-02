package servlet;

import java.io.IOException;
import java.util.List;

import config.DBManager;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.UsersMapper;

/**
 * Servlet implementation class UsersSelectAllServlet
 */
@WebServlet("/all.do")
public class UsersSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersSelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersMapper mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
		
		List<UsersDTO> list = mapper.selectAllUsers();
		
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("users_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
