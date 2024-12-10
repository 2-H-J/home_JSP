package controller;

import java.io.IOException;

import dto.BoardsDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardsService;
import view.ModelAndView;

public class InsertBoard implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션에서 UsersDTO 객체 가져오기
		HttpSession session = request.getSession();
        UsersDTO user = (UsersDTO) session.getAttribute("user");

        if (user == null) {
            // 세션에 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
            response.sendRedirect("signin.jsp");
            return null;
        }
        
        int userNumber = user.getUserNumber();
        System.out.println(userNumber);
		// 파라미터로 전달된 게시글 제목과 설명을 가져옴
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String tag = request.getParameter("tag");

		BoardsDTO dto = new BoardsDTO();
		dto.setUserNumber(userNumber);
		dto.setTag(tag);
		dto.setTitle(title);
		dto.setDescription(description);
		
		int count = BoardsService.getInstance().insertBoard(dto);

		ModelAndView view = new ModelAndView();
		view.setPath("./allBoard.do");
		view.setRedirect(true);

		return view;
	}
}