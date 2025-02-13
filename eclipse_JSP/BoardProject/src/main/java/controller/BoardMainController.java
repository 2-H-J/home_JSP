package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;
import vo.PaggingVO;

public class BoardMainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//사용자가 클릭한 페이지 번호를 받아서 조회, 단 페이지 번호가 없으면 1로 지정
		String page = request.getParameter("page");
		int pageNo = (page== null ? 1 : Integer.parseInt(page)) ;
		//한페이지당 조회할 게시글 개수
		int pageContentEa = (request.getParameter("pageContentEa") == null ? 
				30 : Integer.parseInt(request.getParameter("pageContentEa")));
		
		//전체 게시글 개수 조회
		int count = BoardService.getInstance().selectBoardTotalCount();
		//페이지 번호를 보내서 해당 페이지의 게시글 목록만 조회하게끔 SQL문과 Mapper 수정
		List<BoardDTO> list = BoardService.getInstance().getBoardList(pageNo, pageContentEa);
		PaggingVO pagging = new PaggingVO(count, pageNo, pageContentEa);
		//ModelAndView에 데이터를 추가
		//이동페이지는 main.jsp로 이동
		ModelAndView view = new ModelAndView();
		view.addObject("boardList", list);
		view.addObject("pagging", pagging);
		view.setPath("main.jsp");
		
		return view;
	}

}







