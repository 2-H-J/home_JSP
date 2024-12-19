package controller;

import java.io.IOException;

import dto.RegionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RegionService;
import view.ModelAndView;

/**
 * RegionDeleteController 클래스는 지역 번호(regionNumber)를 기반으로 지역 정보를 삭제하는 요청을 처리하는
 * 컨트롤러입니다.
 */
public class RegionDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 클라이언트로부터 전달받은 지역 번호 (regionNumber) 가져오기
		int regionNumber = Integer.parseInt(request.getParameter("regionNumber"));
		System.out.println(regionNumber);

		// 지역 삭제 서비스 호출
		int result = RegionService.getInstance().deleteRegionByRegionNumber(regionNumber);

		// 반환할 ModelAndView 객체 생성
		ModelAndView view = new ModelAndView();

		if (result > 0) { // 삭제 성공
			System.out.println("지역 정보 삭제 성공");
			// 지역 목록으로 리다이렉트 설정
			view.setPath("./region.do");
			view.setRedirect(true);
		} else { // 삭제 실패
			// 실패 메시지 출력
			response.getWriter().println("지역 정보 삭제 실패. 존재하지 않는 지역 번호입니다.");
			// 에러 페이지로 포워딩 설정
			view.setPath("/errorPage.jsp");
			view.setRedirect(false);
		}

		return view;
	}
}
