package controller;

import java.io.IOException;

import dto.CommentsDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class CommentWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cDescription = request.getParameter("comment");
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		int userNumber = ((UsersDTO) request.getSession().getAttribute("user")).getUserNumber();
		
		CommentsDTO comment = new CommentsDTO(postNumber, userNumber, cDescription);
		BoardsService.getInstance().insertComment(comment);
		
		ModelAndView view = new ModelAndView();
		view.addObject("comment", comment);
		view.setPath("./boardDetail.do?postNumber=" + postNumber);
		view.setRedirect(true);
		
		return view;
	}

}
