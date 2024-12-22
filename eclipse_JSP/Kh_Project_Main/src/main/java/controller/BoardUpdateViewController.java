package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardFileDTO;
import dto.BoardsDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class BoardUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		int userNumber = ((UsersDTO) request.getSession().getAttribute("user")).getUserNumber();

		// 게시글
		BoardsDTO board = BoardsService.getInstance().selectBoardByPostNumber(postNumber);
		// 파일리스트
		List<BoardFileDTO> fileList = BoardsService.getInstance().selectFileList(postNumber);

		ModelAndView view = new ModelAndView();
		if (userNumber == board.getUserNumber()) {
			view.addObject("board", board);
			view.addObject("fileList", fileList);
			view.setPath("board_update.jsp");
			view.setRedirect(false);
			return view;
		} else {
			view.setPath("login.jsp");
			view.setRedirect(true);
			return view;
		}
	}
}
