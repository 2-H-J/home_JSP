package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardMainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 전체 게시글 목록 조회
		List<BoardDTO> list = BoardService.getInstance().getBoardList();
		
		// ModelAndView에 데이터를 추가
		
		// 이동할 페이지는 main.jsp로 이동
		ModelAndView view = new ModelAndView();
		view.addObject("boardList", list);
		view.setPath("main.jsp");
		
		return view;
	}

}
