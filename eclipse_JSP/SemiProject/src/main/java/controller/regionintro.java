package controller;

import java.io.IOException;
import java.util.List;

import dto.RegionDTO;
import service.RegionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class regionintro implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<RegionDTO> list = RegionService.getInstance().selectAllRegion();

		ModelAndView view = new ModelAndView();
		view.addObject("regionList", list);
		view.setPath("region.jsp");
		view.setRedirect(false);
		return view;
	}

}
