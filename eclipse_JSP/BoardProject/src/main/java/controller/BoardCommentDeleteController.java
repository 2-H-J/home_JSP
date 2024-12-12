package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//댓글 번호/게시글 번호 받기
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		//댓글 삭제 처리
		BoardService.getInstance().deleteBoardComment(cno);
		//페이지 이동 해당 게시글
		ModelAndView view = new ModelAndView();
		view.setPath("./boardView.do?bno="+bno);
		view.setRedirect(true);
		return view;
	}

}