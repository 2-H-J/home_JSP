package controller;

import java.io.IOException;

import org.json.JSONObject;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class BoardCommentLikeController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
		int userNumber = ((UsersDTO) request.getSession().getAttribute("user")).getUserNumber();
		JSONObject json = new JSONObject();
		int commentUserNumber = BoardsService.getInstance().getCommentUserNumber(commentNumber);

		if (userNumber != commentUserNumber) {
			try {
				BoardsService.getInstance().insertCommentLike(commentNumber, userNumber);
				json.put("msg", "해당 댓글에 좋아요를 하셨습니다.");
			} catch (Exception e) {
				BoardsService.getInstance().deleteCommentLike(commentNumber, userNumber);
				json.put("msg", "해당 댓글에 좋아요를 취소 하셨습니다.");
			}
		}else {
			json.put("msg", "자신의 댓글에 좋아요를 할 수 없습니다.");
		}
		json.put("clike", BoardsService.getInstance().getCommentLike(commentNumber));
		response.getWriter().println(json.toString());
		return null;
	}

}
