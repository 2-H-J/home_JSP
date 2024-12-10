package controller;

import java.io.IOException;

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
		System.out.println("[BoardDeleteController]  : "+bno);
		BoardService.getInstance().deleteBoard(bno);
		
		
        // 게시글 삭제 후 메인 페이지로 리다이렉트
        ModelAndView view = new ModelAndView();
        view.setPath("./boardMain.do");
        view.setRedirect(true);
        return view;
	}

}
