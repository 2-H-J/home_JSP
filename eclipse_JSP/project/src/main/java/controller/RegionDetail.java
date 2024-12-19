package controller;

import java.io.IOException;
import java.util.List;

import dto.RegionDTO;
import service.RegionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class RegionDetail implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int regionNumber = Integer.parseInt(request.getParameter("regionNumber")); // 지역 번호 파라미터 받기
		RegionDTO regionDetail = RegionService.getInstance().selectRegionByRegionNumber(regionNumber); // 단일 지역 조회

		ModelAndView view = new ModelAndView();
		view.addObject("regionDetail", regionDetail); // 단일 지역 객체 전달
		view.setPath("regionDetail.jsp");
		view.setRedirect(false);
		return view;
	}
}
