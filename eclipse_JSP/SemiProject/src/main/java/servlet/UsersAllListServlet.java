package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.UsersMapper;

import java.io.IOException;
import java.util.List;

import config.DBManager;
import dto.UsersDTO;

@WebServlet("/all.do")
public class UsersAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public UsersAllListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Mapper에서 전체 유저 정보를 받음
		UsersMapper mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
		List<UsersDTO> list = mapper.selectAllUsers();
		
		System.out.println(list);
		
		// 2. request영역에 유저정보가 있는 리스트를 저장
		request.setAttribute("list", list);
		
		// 3. 페이지 이동 - forward, users_list.jsp
		request.getRequestDispatcher("users_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
