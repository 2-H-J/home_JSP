package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class UpdateBoardPage implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postNumberStr = request.getParameter("postNumber");
		int postNumber = Integer.parseInt(postNumberStr);

		// 게시글 상세 조회 서비스 호출
		List<BoardsDTO> boardList = BoardsService.getInstance().selectBoardByPostNumber(postNumber);

		ModelAndView view = new ModelAndView();
		view.addObject("boardList", boardList);
		view.setPath("update_board.jsp");
		view.setRedirect(false);
		return view;
	}
}
