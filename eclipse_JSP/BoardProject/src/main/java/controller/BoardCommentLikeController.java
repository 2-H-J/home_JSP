package controller;

import java.io.IOException;

import org.json.JSONObject;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentLikeController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//댓글 번호
		int cno = Integer.parseInt(request.getParameter("cno"));
		//사용자 아이디
		String id = ((BoardMemberDTO)request.getSession().getAttribute("user")).getId();
		//결과 저장할 JSON 생성
		JSONObject json = new JSONObject();
		
		try {
			BoardService.getInstance().insertBoardCommentLike(cno,id);
			json.put("msg", "해당 댓글에 좋아요를 하셨습니다.");
		}catch (Exception e) {
			BoardService.getInstance().deleteBoardCommentLike(cno,id);
			json.put("msg", "해당 댓글에 좋아요를 취소하셨습니다.");
		}
		response.getWriter().println(json.toString());
		return null;
	}

}







