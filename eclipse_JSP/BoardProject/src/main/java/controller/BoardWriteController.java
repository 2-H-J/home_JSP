package controller;

import java.io.IOException;

//import com.azul.tooling.in.Model;

import dto.BoardDTO;
import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import view.ModelAndView;

public class BoardWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// title, content 파라미터 읽어옴
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 작성자 ID --> 세션 있는 BoardMemberDTO에서 읽어옴
//		HttpSession session = request.getSession();
//		BoardMemberDTO dto = (BoardMemberDTO) session.getAttribute("user");
//		id = dto.getId();
		
		// 위 코드를 한줄로 요약하여 작성
		String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();
		
		// 게시글 등록
		BoardDTO dto = new BoardDTO();
		dto.setId(id);
		dto.setTitle(title);
		dto.setContent(content);
		int count = BoardService.getInstance().insertBoard(dto);
		System.out.println("게시글 등록 결과 : " + count);
		
		// main.jsp로 이동(./boardMain.do)
		ModelAndView view = new ModelAndView();
		view.setPath("./boardMain.do");
		view.setRedirect(true);

		return view;
	}

}