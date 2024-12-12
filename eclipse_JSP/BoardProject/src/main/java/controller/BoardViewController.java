package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardFileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import view.ModelAndView;

public class BoardViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//글번호 받아오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		//글번호에 해당하는 게시글의 조회수 증가
		//Set 하나를 세션에 저장해놓고, 방문한적이 있는 게시글 번호를 추가
		//추가 결과가 true일때만 조회수 증가처리
		HttpSession session = request.getSession();
		HashSet<Integer> history = (HashSet<Integer>) session.getAttribute("history");
		if(history == null) {
			history = new HashSet<Integer>();
			session.setAttribute("history", history);
		}
		System.out.println(history);
		if(history.add(bno))
			BoardService.getInstance().updateBoardCount(bno);
		//글번호에 게시글 조회
		BoardDTO dto = BoardService.getInstance().selectBoard(bno);
		//해당 게시글의 댓글 목록 조회
		List<BoardCommentDTO> commentList = BoardService.getInstance().getCommentList(bno);
		//해당 게시글의 첨부파일 목록 조회
		List<BoardFileDTO> fileList = BoardService.getInstance().getBoardFileList(bno);
		
		//페이지 이동
		ModelAndView view = new ModelAndView();
		view.setPath("board_view.jsp");
		view.addObject("board", dto);
		view.addObject("commentList", commentList);
		view.addObject("fileList", fileList);
		return view;
	}

}