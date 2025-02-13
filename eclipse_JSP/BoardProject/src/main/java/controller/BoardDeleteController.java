package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dto.BoardFileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println(bno);
		//-------------------------------------------------------
		//	1. 해당 게시글의 파일 경로들을 가져옴
		List<BoardFileDTO> list = BoardService.getInstance().getBoardFileList(bno);
		//	2. 경로에 해당하는 파일들을 물리적으로 삭제 - File 클래스 이용		
		list.forEach(item -> {
			File file = new File(item.getFpath());
			file.delete();//물리적으로 삭제
		});
		
		//-------------------------------------------------------
		BoardService.getInstance().deleteBoard(bno);
		
		ModelAndView view = new ModelAndView();
		view.setPath("./boardMain.do");
		view.setRedirect(true);
		
		return view;
	}

}
