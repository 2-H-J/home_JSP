package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class DeleteBoardByPostNumber implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postNumberStr = request.getParameter("postNumber");
		int postNumber = Integer.parseInt(postNumberStr);
		int result = BoardsService.getInstance().deleteBoardByPostNumber(postNumber);

		ModelAndView view = new ModelAndView();
		if (result > 0) {
			System.out.println("게시글 삭제 성공");
			view.setPath("./allBoard.do");
			view.setRedirect(true);
		} else {
			response.getWriter().println("게시글 삭제 실패. 존재하지 않는 게시글 번호입니다.");
			view.setPath("/errorPage.jsp");
			view.setRedirect(false);
		}
		return view;
	}
}
