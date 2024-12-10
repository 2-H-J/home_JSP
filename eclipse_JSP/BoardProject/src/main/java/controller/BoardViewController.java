package controller;

import java.io.IOException;
import java.util.HashSet;

import dto.BoardDTO;
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
		// - 글번호 받아오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// - 글번호에 해당하는 게시글의 조회수 증가
		// 브라우저 session 적용하여 한사람으로 인해 조회수 중복 증가되지 않게 적용
		// Set 하나를 session에 저장해 놓고, 방문한적이 있는 게시글 번호를 추가
		// 추가 결과가 true일때만 조회수 증가처리 if문 ( 새 브라우저 기준 처음 들어갔을때만 증가)
		
		// 세션 추가
		// 브라우저 기준 session 적용되어 들어간적 있는 게시글 번호 등록 하여 조회수 증가 적용
		HttpSession session = request.getSession();		
		HashSet<Integer> history = (HashSet<Integer>) session.getAttribute("history");
		if(history == null) {
			history = new HashSet<Integer>();
			session.setAttribute("history", history);
		}
		System.out.println("[BoardViewController] history "+history); 
		if(history.add(bno))
		
		BoardService.getInstance().updateBoardCount(bno);
		
		// - 글번호에 게시글 조회 
		BoardDTO dto = BoardService.getInstance().selectBoard(bno);
		
		// - 해당 게시글의 댓글 목록 조회
		
		
		// - 해당 게시글의 첨부파일 목록 조회
		
		
		// 페이지 이동 
		ModelAndView view = new ModelAndView();
		view.setPath("board_view.jsp");
		view.addObject("board", dto);
		
		return view;
	}

}
