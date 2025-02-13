package controller;

import java.io.IOException;

import dto.BoardCommentDTO;
import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentInsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("comment");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String id = ((BoardMemberDTO)request.getSession().getAttribute("user")).getId();
		
		BoardCommentDTO dto = new BoardCommentDTO(bno, id, content);
		
		BoardService.getInstance().insertBoardComment(dto);
		
		ModelAndView view = new ModelAndView();
		view.setPath("./boardView.do?bno="+bno);
		view.setRedirect(true);
		
		return view;
	}

}