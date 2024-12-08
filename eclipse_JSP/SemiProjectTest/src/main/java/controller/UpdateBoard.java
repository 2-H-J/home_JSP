package controller;

import java.io.IOException;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class UpdateBoard implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String postNumberStr = request.getParameter("postNumber");
		int postNumber = Integer.parseInt(postNumberStr);

		// 게시글 객체 생성 후 수정 내용 설정
		BoardsDTO board = new BoardsDTO();
		board.setPostNumber(postNumber);
		board.setTitle(title);
		board.setDescription(description);

		// 게시글 업데이트
		int result = BoardsService.getInstance().updateBoard(board); // updateBoard 메서드가 영향을 받은 행 수를 반환한다고 가정
		System.out.println("데이터 등록 결과 : " + result);

		// ModelAndView 객체 생성
		ModelAndView view = new ModelAndView();
		view.setPath("./boardDetail.do?postNumber=" + postNumber);
		view.setRedirect(true);

		return view;
	}

}
