package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class BoardInsertViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsersDTO user = (UsersDTO) session.getAttribute("user");
		if (user == null) {
			// 세션에 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
			response.sendRedirect("login.do");
			return null;
		}
		ModelAndView view = new ModelAndView();
		view.setPath("board_insert.jsp");
		return view;
	}

}
