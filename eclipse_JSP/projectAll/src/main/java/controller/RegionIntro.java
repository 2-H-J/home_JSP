package controller;

import java.io.IOException;
import java.util.List;

import dto.RegionDTO;
import service.RegionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class RegionIntro implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int pageSize = 10;
		int totalCount = RegionService.getInstance().totalRegionCount();
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		System.out.println("totalCount : " + totalCount + " totalPage : " + totalPage);
		String pageStr = request.getParameter("page");
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		if (totalCount == 0) {
		    totalPage = 1;  // 지역이 없다면 페이지는 1
		}
		System.out.println(page + " " + pageSize + " " + totalCount + " " + totalPage);

		int offset = (page - 1) * pageSize;
		System.out.println(offset);
		List<RegionDTO> list = RegionService.getInstance().selectAllRegion(page, pageSize, offset);

		ModelAndView view = new ModelAndView();
		view.addObject("regionList", list);
		view.addObject("currentPage", page);
		view.addObject("totalPage", totalPage);
		view.setPath("region.jsp");
		view.setRedirect(false);
		return view;
	}

}
