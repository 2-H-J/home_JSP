package controller;

import java.io.IOException;

import dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardDTO dto = BoardService.getInstance().selectBoard(bno);
		
		ModelAndView view = new ModelAndView();
		view.setPath("board_update_view.jsp");
		view.addObject("board", dto);
		return view;
	}

}
