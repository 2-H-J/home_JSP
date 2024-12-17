package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class CommentDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
		BoardsService.getInstance().deleteComment(commentNumber);
		
		ModelAndView view = new ModelAndView();
		view.setPath("./boardDetail.do?postNumber="+postNumber);
		view.setRedirect(true);
		return view;
	}

}
