package controller;

import java.io.IOException;

import dto.CommentsDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardsService;
import view.ModelAndView;

public class CommentWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션에서 UsersDTO 객체 가져오기
		HttpSession session = request.getSession();
		UsersDTO user = (UsersDTO) session.getAttribute("user");

		if (user == null) {
			// 세션에 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
			response.sendRedirect("login.do");
			return null;
		}

		int postNumber = Integer.parseInt(request.getParameter("postNumber"));

		String cDescription = request.getParameter("comment");
		int userNumber = ((UsersDTO) request.getSession().getAttribute("user")).getUserNumber();

		CommentsDTO comment = new CommentsDTO(postNumber, userNumber, cDescription);
		BoardsService.getInstance().insertComment(comment);

		ModelAndView view = new ModelAndView();
		view.addObject("comment", comment);
		view.setPath("./boardDetail.do?postNumber=" + postNumber);
		view.setRedirect(true);

		return view;
	}

}
