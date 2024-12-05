package controller;

import java.io.IOException;
import java.util.List;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

public class SelectAllUsers implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UsersDTO> list = UsersService.getInstance().selectAllUsers();
		
		ModelAndView view = new ModelAndView();
		view.addObject("list", list);
		view.setPath("users_list.jsp");
		view.setRedirect(false);
		return view;
	}

}
