package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class SelectAllBoards implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("controller - ");
		List<BoardsDTO> list = BoardsService.getInstance().selectAllBoards();

		ModelAndView view = new ModelAndView();
		view.addObject("list", list);
		view.setPath("boards_list.jsp");
		view.setRedirect(false);
		return view;
	}

}
