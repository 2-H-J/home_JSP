package controller;

import dto.RegionDTO;
import dto.UsersDTO;
import service.RegionService;
import view.ModelAndView;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegionInsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 세션에서 사용자 정보 가져오기
		HttpSession session = request.getSession();
		UsersDTO user = (UsersDTO) session.getAttribute("user");

//	 로그인 상태가 아니면 로그인 페이지로 리다이렉트

        if (user == null) {
            response.sendRedirect("signin.jsp");
            return null;
        }

		// 사용자 정보
		int userNumber = user.getUserNumber();

		// 파라미터에서 지역 정보 가져오기
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		// RegionDTO 객체 생성
		RegionDTO region = new RegionDTO(title, description);

		// RegionService를 통해 데이터베이스에 지역 정보 삽입
		int count = RegionService.getInstance().insertRegion(region);

		ModelAndView view = new ModelAndView();
		if (count > 0) {
			view.setPath("./region.do"); // 지역 목록 페이지로 이동
		} else {
			view.setPath("./regionInsert.jsp"); // 실패 시 다시 작성 페이지로 돌아감
		}
		view.setRedirect(true);
		return view;
	}
}
