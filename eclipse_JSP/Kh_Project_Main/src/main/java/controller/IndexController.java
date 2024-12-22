package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class IndexController implements Controller {

	 @Override
	    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        ModelAndView view = new ModelAndView();
	        view.setPath("index.jsp");
	        view.setRedirect(false);
	        return view;
	    }
	}