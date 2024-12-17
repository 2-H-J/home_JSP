package controller;

import java.io.IOException;
import dto.RegionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RegionService;
import view.ModelAndView;

public class RegionUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 'postNumber' 대신 'regionNumber'를 사용
		int regionNumber = Integer.parseInt(request.getParameter("regionNumber"));

		// 'BoardsService' -> 'RegionService', DTO도 BoardsDTO -> RegionDTO
		RegionDTO region = RegionService.getInstance().selectRegionByRegionNumber(regionNumber);

		ModelAndView view = new ModelAndView();
		view.addObject("region", region);
		view.setPath("region_update.jsp"); // JSP 파일명도 변경
		view.setRedirect(false);
		return view;
	}
}
