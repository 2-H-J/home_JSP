package controller;

import java.io.IOException;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class InsertBoard implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파라미터로 전달된 게시글 제목과 설명을 가져옴
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		int userNumber = 1;
		BoardsDTO dto = new BoardsDTO(userNumber, title, description);

		int count = BoardsService.getInstance().insertBoard(dto);

		ModelAndView view = new ModelAndView();
		view.setPath("./allBoard.do");
		view.setRedirect(true);

		return view;
	}
}