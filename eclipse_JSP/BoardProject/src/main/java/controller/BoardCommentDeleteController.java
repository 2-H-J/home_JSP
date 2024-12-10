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
		// 댓글 번호 / 게시글 번호 받기
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println("[BoardCommentDeleteController]  : "+cno+" / "+bno);
		
		// 댓글 삭제 처리
		BoardService.getInstance().boardCommentDelete(cno);
		
		
        // 댓글 삭제 후 해당 게시물 페이지
        ModelAndView view = new ModelAndView();
        view.setPath("./boardView.do?bno="+bno); // 해당 게시물 가는 방법 경로
        view.setRedirect(true);
        return view;
	}

}
