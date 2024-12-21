package controller;

import java.io.IOException;

import org.json.JSONObject;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class BoardLikeController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		int userNumber = ((UsersDTO) request.getSession().getAttribute("user")).getUserNumber();
		int boardUserNumber = Integer.parseInt(request.getParameter("userNumber"));

		JSONObject json = new JSONObject();
		System.out.println(userNumber);
		System.out.println(postNumber);
		if (userNumber != boardUserNumber) {
			try {
				BoardsService.getInstance().insertBoardLike(postNumber, userNumber);
				json.put("msg", "해당 게시글에 좋아요를 하셨습니다.");
			} catch (Exception e) {
				BoardsService.getInstance().deleteBoardLike(postNumber, userNumber);
				json.put("msg", "해당 게시글에 좋아요를 취소 하셨습니다.");
			}
		}else {
			json.put("msg", "자신의 게시글에는 좋아요를 할 수 없습니다.");
		}
		json.put("blike", BoardsService.getInstance().getBoardLike(postNumber));

		response.getWriter().println(json.toString());
		return null;
	}

}
