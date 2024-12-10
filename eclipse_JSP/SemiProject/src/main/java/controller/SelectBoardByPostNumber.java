package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import dto.BoardsDTO;
import dto.CommentsDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardsService;
import view.ModelAndView;

public class SelectBoardByPostNumber implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//글 번호 가져오기
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		//유저 번호 가져오기
		HttpSession session = request.getSession();
		UsersDTO user = (UsersDTO) session.getAttribute("user");
		//조회수
		HashSet<Integer> history = (HashSet<Integer>) session.getAttribute("history");
		if(history == null) {
			history = new HashSet<Integer>();
			session.setAttribute("history", history);
		}
		if(history.add(postNumber))
			BoardsService.getInstance().updateBoardsCount(postNumber);
		
        
		// 게시글 상세 조회 서비스 호출
        BoardsDTO board = BoardsService.getInstance().selectBoardByPostNumber(postNumber);
        
        List<CommentsDTO> commentList = BoardsService.getInstance().getCommentList(postNumber);
		
		//유저 번호가 null이 아닌 경우
        boolean writer = false;
        if (user != null && board != null) {
        	writer = (user.getUserNumber() == board.getUserNumber());
        }
		ModelAndView view = new ModelAndView();
		view.addObject("board", board);
		view.addObject("writer", writer);
		view.addObject("commentList", commentList);
		view.setPath("boardDetail.jsp");
		view.setRedirect(false);
		return view;
	}

}
