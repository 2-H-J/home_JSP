package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;
import view.ModelAndView;

public class SelectAllMemberController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 회원정보  받아옴
		List<BoardMemberDTO> list = BoardMemberService.getInstance().selectAllMember();
		
		ModelAndView view = new ModelAndView();
		view.addObject("list", list);
		view.setPath("./member_list.jsp");
		view.setRedirect(false);
		
		return view;
	}

}
